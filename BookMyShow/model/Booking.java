package LLDDesigns.BookMyShow.model;

import LLDDesigns.BookMyShow.Enum.BookingStage;

import java.util.List;
import java.util.UUID;

public class Booking {
    private final String bookingId;
    private final Movie movie;
    private final Screen screen;
    private final Theater theater;
    private final List<Seat> seats;
    private BookingStage bookingStage = BookingStage.Available;

    public Booking( Builder bookingBuilder) {
        this.bookingId = bookingBuilder.bookingId;
        this.theater = bookingBuilder.theater;
        this.movie = bookingBuilder.movie;
        this.screen = bookingBuilder.screen;
        this.seats = bookingBuilder.seats;
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

    public BookingStage getBookingStage() {
        return this.bookingStage;
    }

    public static class Builder{
        private final String bookingId;
        private Movie movie;
        private Screen screen;
        private  List<Seat> seats;
        private Theater theater;

        public Builder(){
            this.bookingId = UUID.randomUUID().toString();
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

        public Booking build(){
            return new Booking(this);
        }
    }
}
