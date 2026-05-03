package LLDDesigns.BookMyShow.Services;

import LLDDesigns.BookMyShow.Enum.BookingStage;
import LLDDesigns.BookMyShow.Repository.BookingRepository;
import LLDDesigns.BookMyShow.Repository.ShowRepository;
import LLDDesigns.BookMyShow.Strategy.PaymentCalculator;
import LLDDesigns.BookMyShow.Strategy.PaymentStrategy;
import LLDDesigns.BookMyShow.model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class BookMyShowService {
    private final ShowRepository showRepository;
    private final BookingRepository bookingRepository;
    /**
     * Seat-hold duration (TTL) for a booking.
     * In real systems this is usually 5-10 minutes, configurable per city/theater.
     */
    private static final long LOCK_TTL_MS = 2 * 60 * 1000; // 2 minutes for demo

    public BookMyShowService(ShowRepository showRepository, BookingRepository bookingRepository) {
        this.showRepository = showRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Show> getAllAvailableShows(String movieName){
        return showRepository.getAllShowsByMovieName(movieName);
    }

    private List<ShowSeatKey> sortKeysForLocking(List<ShowSeatKey> seatKeys) {
        List<ShowSeatKey> sorted = new ArrayList<>(seatKeys);
        sorted.sort(Comparator
                .comparing((ShowSeatKey k) -> k.getSeatType().name())
                .thenComparingInt(ShowSeatKey::getSeatId));
        return sorted;
    }

    private List<ReentrantLock> lockSeatsInOrder(Show show, List<ShowSeatKey> seatKeys) {
        List<ReentrantLock> locks = new ArrayList<>(seatKeys.size());
        for (ShowSeatKey key : seatKeys) {
            ReentrantLock lock = show.getSeatLock(key);
            lock.lock();
            locks.add(lock);
        }
        return locks;
    }

    private void unlockAll(List<ReentrantLock> locks) {
        for (int i = locks.size() - 1; i >= 0; i--) {
            locks.get(i).unlock();
        }
    }

    public Booking createBooking(Show show, List<ShowSeatKey> seatKeys) {
        if (show == null || seatKeys == null || seatKeys.isEmpty()) {
            System.out.println("Invalid show or seat selection");
            return null;
        }
        Set<ShowSeatKey> unique = new HashSet<>();
        for (ShowSeatKey key : seatKeys) {
            if (!unique.add(key)) {
                System.out.println("Duplicate seat in selection: " + key);
                return null;
            }
        }

        List<ShowSeatKey> sortedKeys = sortKeysForLocking(seatKeys);
        List<ReentrantLock> locks = new ArrayList<>();
        try {
            locks = lockSeatsInOrder(show, sortedKeys);
            long now = System.currentTimeMillis();
            long lockUntil = now + LOCK_TTL_MS;

            List<Seat> resolvedSeats = new ArrayList<>();
            for (ShowSeatKey key : sortedKeys) {
                Seat seat = show.findSeat(key).orElse(null);
                if (seat == null) {
                    System.out.println("No such seat for this show: " + key);
                    return null;
                }
                // Lazy expiry: if some previous hold timed out, release it now.
                seat.expireLockIfNeeded(now);
                if (seat.getBookingStage() != BookingStage.Available) {
                    System.out.println("Seat not available: " + key);
                    return null;
                }
                resolvedSeats.add(seat);
            }

            Booking booking = new Booking.Builder()
                    .show(show)
                    .theater(show.getTheater())
                    .movie(show.getMovie())
                    .screen(show.getScreen())
                    .seats(resolvedSeats)
                    .lockUntilEpochMs(lockUntil)
                    .build();
            booking.setBookingStage(BookingStage.Locked);
            for (Seat seat : resolvedSeats) {
                seat.lockForBooking(booking.getBookingId(), lockUntil);
            }
            bookingRepository.addBooking(booking);
            return booking;
        } finally {
            unlockAll(locks);
        }
    }

    public Ticket confirmBooking(String bookingId, PaymentStrategy paymentStrategy) {
        Booking booking = bookingRepository.getBooking(bookingId);
        if (booking == null) {
            System.out.println("Booking not found for id: " + bookingId);
            return null;
        }
        Show show = booking.getShow();
        if (show == null) {
            System.out.println("Booking has no associated show");
            return null;
        }

        List<Seat> seats = booking.getSeats();
        List<ShowSeatKey> seatKeys = new ArrayList<>(seats.size());
        for (Seat seat : seats) {
            seatKeys.add(new ShowSeatKey(seat.getSeatType(), seat.getSeatId()));
        }
        List<ShowSeatKey> sortedKeys = sortKeysForLocking(seatKeys);

        List<ReentrantLock> locks = new ArrayList<>();
        try {
            locks = lockSeatsInOrder(show, sortedKeys);
            long now = System.currentTimeMillis();
            if (booking.getBookingStage() == BookingStage.Locked && now > booking.getLockUntilEpochMs()) {
                // Booking TTL expired; release any seats still held by this booking.
                for (Seat seat : seats) {
                    seat.expireLockIfNeeded(now);
                    if (bookingId.equals(seat.getLockedByBookingId())) {
                        seat.releaseLock();
                    }
                }
                booking.setBookingStage(BookingStage.Available);
                System.out.println("Booking hold expired. Please retry seat selection.");
                return null;
            }
            if(booking.getBookingStage() != BookingStage.Locked){
                System.out.println("Booking can not be completed because of some technical error");
                return  null;
            }
            // Ensure seats are still held by this booking (not expired or stolen).
            for (Seat seat : seats) {
                seat.expireLockIfNeeded(now);
                if (seat.getBookingStage() != BookingStage.Locked || !bookingId.equals(seat.getLockedByBookingId())) {
                    System.out.println("Seats are no longer held for this booking. Please retry.");
                    booking.setBookingStage(BookingStage.Available);
                    return null;
                }
            }
            double totalCost = PaymentCalculator.calculate(seats);

            Ticket ticket = new Ticket(booking.getMovie(), booking.getTheater(), booking.getScreen(), seats, totalCost);

            paymentStrategy.pay(totalCost);

            booking.setBookingStage(BookingStage.Booked);
            for (Seat seat : seats) {
                seat.markBooked();
            }

            System.out.println("Payment is done Here is the Ticket " + ticket.toString());

            return ticket;
        } finally {
            unlockAll(locks);
        }
    }
}
