package LLDDesigns.BookMyShow.model;

import LLDDesigns.BookMyShow.Enum.SeatType;

public class SeatFactory {
    public static Seat createSeat(int seatId, SeatType seatType){
        return switch (seatType) {
            case Prime -> new PrimeSeat(seatId);
            case Regular -> new RegularSeat(seatId);
            case Recliner -> new ReclinerSeat(seatId);
            default -> null;
        };
    }
}
