package LLDDesigns.ParkingLot.Exceptions;

public class NoSpotAvailable extends RuntimeException {
    public NoSpotAvailable(String message) {
        super(message);
    }
}
