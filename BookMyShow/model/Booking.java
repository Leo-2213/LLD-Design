package LLDDesigns.BookMyShow.model;

import LLDDesigns.BookMyShow.Enum.BookingStage;

import java.util.List;
import java.util.UUID;

public class Booking {
    private final String bookingId;
    private final Show show;
    private final Movie movie;
    private final Screen screen;
    private final Theater theater;
    private final List<Seat> seats;
    private BookingStage bookingStage = BookingStage.Available;
    /**
     * TTL for the whole booking hold; should match seat-level holds.
     * If current time exceeds this, the booking can no longer be confirmed.
     */
    private final long lockUntilEpochMs;

    public Booking( Builder bookingBuilder) {
        this.bookingId = bookingBuilder.bookingId;
        this.show = bookingBuilder.show;
        this.theater = bookingBuilder.theater;
        this.movie = bookingBuilder.movie;
        this.screen = bookingBuilder.screen;
        this.seats = bookingBuilder.seats;
        this.lockUntilEpochMs = bookingBuilder.lockUntilEpochMs;
    }

    public Show getShow() {
        return show;
    }

    public Theater getTheater(){
        return this.theater;
    }

    public String getBookingId() {
        return this.bookingId;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public List<Seat> getSeats() {
        return this.seats;
    }

    public long getLockUntilEpochMs() {
        return lockUntilEpochMs;
    }

    public BookingStage getBookingStage() {
        return this.bookingStage;
    }

    public void setBookingStage(BookingStage bookingStage) {
        this.bookingStage = bookingStage;
    }

    public static class Builder{
        private final String bookingId;
        private Show show;
        private Movie movie;
        private Screen screen;
        private  List<Seat> seats;
        private Theater theater;
        private long lockUntilEpochMs = 0L;

        public Builder(){
            this.bookingId = UUID.randomUUID().toString();
        }
        public Builder show(Show show) {
            this.show = show;
            return this;
        }
        public Builder theater(Theater theater){
            this.theater = theater;
            return this;
        }
        public Builder movie(Movie movie){
           this.movie = movie;
            return this;
        }
        public Builder screen(Screen screen){
            this.screen = screen;
            return this;
        }
        public Builder seats(List<Seat> seats){
            this.seats = seats;
            return this;
        }
        public Builder lockUntilEpochMs(long lockUntilEpochMs) {
            this.lockUntilEpochMs = lockUntilEpochMs;
            return this;
        }

        public Booking build(){
            if (show == null) {
                throw new IllegalStateException("Booking requires a Show");
            }
            return new Booking(this);
        }
    }
}
