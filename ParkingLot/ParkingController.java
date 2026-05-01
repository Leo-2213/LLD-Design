package LLDDesigns.ParkingLot;

import LLDDesigns.ParkingLot.Models.PaymentStrategy;
import LLDDesigns.ParkingLot.Models.Ticket;
import LLDDesigns.ParkingLot.Models.Vehicle;

public class ParkingController {
    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public Ticket parkVehicle(Vehicle vehicle){
        return parkingService.parkVehicle(vehicle);
    }
    public void unparkVehicle(String ticketId, PaymentStrategy paymentStrategy){
        parkingService.unParkVehicle(ticketId, paymentStrategy);
    }
}
