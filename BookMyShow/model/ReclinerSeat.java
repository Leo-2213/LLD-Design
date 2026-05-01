package LLDDesigns.BookMyShow.model;

import LLDDesigns.BookMyShow.Enum.SeatType;

public class ReclinerSeat extends Seat{
    public ReclinerSeat(int seatId) {
        super(seatId, SeatType.Recliner);
    }
}
