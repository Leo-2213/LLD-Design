package LLDDesigns.BookMyShow.model;

import LLDDesigns.BookMyShow.Enum.BookingStage;
import LLDDesigns.BookMyShow.Enum.SeatType;

public abstract class Seat {
    private final int seatId;
    private final SeatType seatType;
    private BookingStage bookingStage;

    public int getSeatId() {
        return this.seatId;
    }

    public void setBookingStage(BookingStage bookingStage) {
        this.bookingStage = bookingStage;
    }

    public SeatType getSeatType() {
        return this.seatType;
    }
    public BookingStage getBookingStage(){
        return this.bookingStage;
    }

    public Seat(int seatId, SeatType seatType) {
        this.seatId = seatId;
        this.seatType = seatType;
        this.bookingStage = BookingStage.Available;
    }

    @Override
    public String toString() {
        return seatType + "-" + seatId;
    }

}
