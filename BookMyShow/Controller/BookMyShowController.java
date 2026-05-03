package LLDDesigns.BookMyShow.Controller;

import LLDDesigns.BookMyShow.Repository.BookingRepository;
import LLDDesigns.BookMyShow.Repository.ShowRepository;
import LLDDesigns.BookMyShow.Services.BookMyShowService;
import LLDDesigns.BookMyShow.Strategy.PaymentStrategy;
import LLDDesigns.BookMyShow.model.Booking;
import LLDDesigns.BookMyShow.model.Show;
import LLDDesigns.BookMyShow.model.ShowSeatKey;
import LLDDesigns.BookMyShow.model.Ticket;

import java.util.List;

public class BookMyShowController {
    private final BookMyShowService bookMyShowService;

    public BookMyShowController(ShowRepository showRepository, BookingRepository bookingRepository){
        this.bookMyShowService = new BookMyShowService(showRepository, bookingRepository);
    }
    public List<Show> getAllAvailableShows(String movieName){
        return bookMyShowService.getAllAvailableShows(movieName);
    }

    public Booking createBooking(Show show, List<ShowSeatKey> seatKeys){
        return bookMyShowService.createBooking(show, seatKeys);

    }

    public Ticket confirmBooking(String bookingId, PaymentStrategy paymentStrategy){
        return bookMyShowService.confirmBooking(bookingId, paymentStrategy);
    }

}
