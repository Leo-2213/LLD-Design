package LLDDesigns.BookMyShow;

import LLDDesigns.BookMyShow.Controller.BookMyShowController;
import LLDDesigns.BookMyShow.Enum.SeatType;
import LLDDesigns.BookMyShow.Repository.BookingRepository;
import LLDDesigns.BookMyShow.Repository.ShowRepository;
import LLDDesigns.BookMyShow.Strategy.CardPayment;
import LLDDesigns.BookMyShow.Strategy.PaymentStrategy;
import LLDDesigns.BookMyShow.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookMyShow {
    public static void main(String[] args) {

        ShowRepository showRepository = new ShowRepository();
        BookingRepository bookingRepository = new BookingRepository();

        Screen screen1A = new Screen.Builder("Screen1").addSeats(SeatType.Recliner, 10).addSeats(SeatType.Prime, 50).addSeats(SeatType.Regular, 100).build();
        Screen screen2A = new Screen.Builder("Screen2").addSeats(SeatType.Recliner, 10).addSeats(SeatType.Prime, 50).addSeats(SeatType.Regular, 100).build();
        Theater theater1 =  new Theater("MG Road", List.of(screen1A, screen2A));

        Screen screen1B = new Screen.Builder("Screen1").addSeats(SeatType.Recliner, 10).addSeats(SeatType.Prime, 50).addSeats(SeatType.Regular, 100).build();
        Screen screen2B= new Screen.Builder("Screen2").addSeats(SeatType.Recliner, 10).addSeats(SeatType.Prime, 50).addSeats(SeatType.Regular, 100).build();
        Theater theater2 =  new Theater("Singasandra", List.of(screen1B, screen2B));

        Screen screen1C = new Screen.Builder("Screen1").addSeats(SeatType.Recliner, 10).addSeats(SeatType.Prime, 50).addSeats(SeatType.Regular, 100).build();
        Screen screen2C = new Screen.Builder("Screen2").addSeats(SeatType.Recliner, 10).addSeats(SeatType.Prime, 50).addSeats(SeatType.Regular, 100).build();
        Theater theater3 =  new Theater("JP Nagar", List.of(screen1C, screen2C));

        List<Show> bastardsShow = new ArrayList<>();
        bastardsShow.add(new Show(1,theater1, screen1A, 11.30, 2.20, new Movie("Bastards", 140)));
        bastardsShow.add(new Show(2,theater2, screen1B, 1.30, 3.20, new Movie("Bastards", 140)));
        bastardsShow.add(new Show(3,theater3, screen1C, 2.30, 4.20, new Movie("Bastards", 140)));

        List<Show> badGuyShow = new ArrayList<>();
        bastardsShow.add(new Show(1,theater1, screen2A, 11.30, 2.20, new Movie("Bad guy", 140)));
        bastardsShow.add(new Show(2,theater2, screen2B, 1.30, 3.20, new Movie("Bad guy ", 140)));
        bastardsShow.add(new Show(3,theater3, screen2C, 2.30, 4.20, new Movie("Bad guy", 140)));

        showRepository.addShow("Bastards", bastardsShow );
        showRepository.addShow("badGuyShow", badGuyShow);

        BookMyShowController bookMyShowController = new BookMyShowController(showRepository, bookingRepository);

        List<Show> listOfShows = bookMyShowController.getAllAvailableShows("Bastards");

        List<Seat> selectedSeat = new ArrayList<>();
        selectedSeat.add(new ReclinerSeat(1));
        selectedSeat.add(new ReclinerSeat(2));
        selectedSeat.add(new PrimeSeat(3));
        selectedSeat.add(new PrimeSeat(4));

        Booking booking = bookMyShowController.createBooking(selectedSeat, showRepository.getShowByMovieNameAndId("Bastards", 1));

        Ticket ticket = bookMyShowController.confirmBooking(booking.getBookingId(), new CardPayment());
    }
}
