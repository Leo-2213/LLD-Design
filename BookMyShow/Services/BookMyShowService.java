package LLDDesigns.BookMyShow.Services;

import LLDDesigns.BookMyShow.Enum.BookingStage;
import LLDDesigns.BookMyShow.Enum.SeatType;
import LLDDesigns.BookMyShow.Repository.BookingRepository;
import LLDDesigns.BookMyShow.Repository.ShowRepository;
import LLDDesigns.BookMyShow.Strategy.PaymentCalculator;
import LLDDesigns.BookMyShow.Strategy.PaymentStrategy;
import LLDDesigns.BookMyShow.model.*;

import java.util.List;
import java.util.Map;

public class BookMyShowService {
    private final ShowRepository showRepository;
    private PaymentStrategy paymentStrategy;
    private BookingRepository bookingRepository;
    public BookMyShowService(ShowRepository showRepository, BookingRepository bookingRepository) {
        this.showRepository = showRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Show> getAllAvailableShows(String movieName){
        return showRepository.getAllShowsByMovieName(movieName);
    }

    public Booking createBooking(List<Seat> seatList, Show show) {
        for(Seat seat : seatList){
            if(seat.getBookingStage() != BookingStage.Available){
                System.out.println("The seat is already booked or in booking stage");
                return null;
            }
        }
        Booking booking = new Booking.Builder().theater(show.getTheater()).movie(show.getMovie()).screen(show.getScreen()).seats(seatList).build();
        for(Seat seat : seatList){
            seat.setBookingStage(BookingStage.Locked);
        }
        return booking;
    }

    public Ticket confirmBooking(String bookingId, PaymentStrategy paymentStrategy) {
        Booking booking = bookingRepository.getBooking(bookingId);
        if(booking.getBookingStage() != BookingStage.Locked){
            System.out.println("Booking can not be completed because of some technical error");
            return  null;
        }
        List<Seat> seats = booking.getSeats();
        double totalCost = PaymentCalculator.calculate(seats);

        Ticket ticket = new Ticket(booking.getMovie(), booking.getTheater(), booking.getScreen(), seats, totalCost);

        paymentStrategy.pay(totalCost);

        System.out.println("Payment is done Here is the Ticket " + ticket.toString());

        return ticket;
    }
}
