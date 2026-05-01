package LLDDesigns.BookMyShow.model;

import LLDDesigns.BookMyShow.Enum.SeatType;

public class PrimeSeat extends Seat{
    public PrimeSeat(int seatId) {
        super(seatId, SeatType.Prime);
    }
}
