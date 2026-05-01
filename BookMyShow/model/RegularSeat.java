package LLDDesigns.BookMyShow.model;

import LLDDesigns.BookMyShow.Enum.SeatType;

public class RegularSeat extends Seat{
    public RegularSeat(int seatId) {
        super(seatId, SeatType.Regular);
    }
}
