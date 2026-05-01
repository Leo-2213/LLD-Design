package LLDDesigns.BookMyShow.Repository;

import LLDDesigns.BookMyShow.model.Booking;

import java.util.HashMap;
import java.util.Map;

public class BookingRepository {
    private Map<String, Booking> bookingMap = new HashMap<>();

    public void addBooking( Booking booking){
        bookingMap.put(booking.getBookingId(), booking);
    }
    public Booking getBooking(String bookingId){
        return bookingMap.get(bookingId);
    }
}
