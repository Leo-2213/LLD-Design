package LLDDesigns.BookMyShow.Controller;

import LLDDesigns.BookMyShow.Repository.BookingRepository;
import LLDDesigns.BookMyShow.Repository.ShowRepository;
import LLDDesigns.BookMyShow.Services.BookMyShowService;
import LLDDesigns.BookMyShow.Strategy.PaymentStrategy;
import LLDDesigns.BookMyShow.model.*;

import java.util.List;

public class BookMyShowController {
    private final BookMyShowService bookMyShowService;

    public BookMyShowController(ShowRepository showRepository, BookingRepository bookingRepository){
        this.bookMyShowService = new BookMyShowService(showRepository, bookingRepository);
    }
    public List<Show> getAllAvailableShows(String movieName){
        return bookMyShowService.getAllAvailableShows(movieName);
    }

    public Booking createBooking(List<Seat> seatList, Show show){
        return bookMyShowService.createBooking(seatList, show);

    }

    public Ticket confirmBooking(String bookingId, PaymentStrategy paymentStrategy){
        return bookMyShowService.confirmBooking(bookingId, paymentStrategy);
    }

}
