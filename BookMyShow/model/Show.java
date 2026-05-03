package LLDDesigns.BookMyShow.model;

import LLDDesigns.BookMyShow.Enum.SeatType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Show {
    private final int showId;
    private final Theater theater;
    private final Screen screen;
    private final double startTime;
    private final double endTime;
    private Movie movie;
    /**
     * Per-showtime inventory: same physical layout as {@link #screen}, but seat state is independent per show.
     */
    private final Map<ShowSeatKey, Seat> seatInventory = new HashMap<>();
    /**
     * Fine-grained per-seat locks for concurrent bookings on different seats.
     */
    private final Map<ShowSeatKey, ReentrantLock> seatLocks = new ConcurrentHashMap<>();

    public Show(int showId, Theater theater, Screen screen, double startTime, double endTime, Movie movie) {
        this.showId = showId;
        this.theater = theater;
        this.screen = screen;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
        copySeatInventoryFromScreen(screen);
    }

    private void copySeatInventoryFromScreen(Screen screen) {
        for (Map.Entry<SeatType, List<Seat>> entry : screen.getSeatTypeListMap().entrySet()) {
            for (Seat layoutSeat : entry.getValue()) {
                Seat showSeat = SeatFactory.createSeat(layoutSeat.getSeatId(), layoutSeat.getSeatType());
                ShowSeatKey key = new ShowSeatKey(layoutSeat.getSeatType(), layoutSeat.getSeatId());
                seatInventory.put(key, showSeat);
                seatLocks.putIfAbsent(key, new ReentrantLock(true));
            }
        }
    }

    public Optional<Seat> findSeat(ShowSeatKey key) {
        return Optional.ofNullable(seatInventory.get(key));
    }

    public ReentrantLock getSeatLock(ShowSeatKey key) {
        ReentrantLock lock = seatLocks.get(key);
        if (lock == null) {
            throw new IllegalArgumentException("No lock exists for seat key: " + key);
        }
        return lock;
    }
    public int getShowId(){
        return this.showId;
    }
    public Theater getTheater() {
        return this.theater;
    }

    public Screen getScreen() {
        return this.screen;
    }

    public double getStartTime() {
        return this.startTime;
    }

    public double getEndTime() {
        return this.endTime;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
