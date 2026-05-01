package LLDDesigns.ParkingLot;

import LLDDesigns.ParkingLot.Enums.SpotType;
import LLDDesigns.ParkingLot.Models.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ParkingFloor f1 = new  ParkingFloor.Builder(1).addSpot(SpotType.SMALL, 5).addSpot(SpotType.MEDIUM, 5).build();
        ParkingFloor f2 = new  ParkingFloor.Builder(2).addSpot(SpotType.SMALL, 2).addSpot(SpotType.MEDIUM, 8).build();

        ParkingService parkingService = new ParkingService(List.of(f1, f2), new NearestParkingStrategy());

        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.setParkingController(new ParkingController(parkingService));
        ParkingController parkingController = parkingLot.getParkingController();

        Ticket t1 = parkingController.parkVehicle(new Car("MH-01-AB-1234"));
        Ticket t2 = parkingController.parkVehicle(new Bike("MH-02-CD-5678"));
        Ticket t3 = parkingController.parkVehicle(new Car("MH-03-EF-9999"));

        parkingController.unparkVehicle(t1.getTicketId(), new UPIMethod());
        parkingController.unparkVehicle(t2.getTicketId(), new CardMethod());

    }
}
