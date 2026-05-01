package LLDDesigns.ParkingLot;

import LLDDesigns.ParkingLot.Enums.SpotType;
import LLDDesigns.ParkingLot.Exceptions.NoSpotAvailable;
import LLDDesigns.ParkingLot.Models.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ParkingService {
    private final List<ParkingFloor> parkingFloors;
    private final ParkingStrategy parkingStrategy;
    private final TicketManager ticketManager = TicketManager.getInstance();

    public ParkingService(List<ParkingFloor> parkingFloors, ParkingStrategy parkingStrategy) {
        this.parkingFloors = parkingFloors;
        this.parkingStrategy = parkingStrategy;
    }

    public Ticket parkVehicle(Vehicle vehicle){
        ParkingSpot parkedSpot = parkingStrategy.findParkingSpot(vehicle, parkingFloors).orElseThrow(() -> new NoSpotAvailable("No spot Available"));

        Ticket ticket = new Ticket(parkedSpot,vehicle);
        ticketManager.addTicket(ticket);
        System.out.println("Vehicle " + vehicle.getNumberPlate()+" is parked, here is your TicketId : " + ticket.getTicketId());
        return ticket;
    }

    public void  unParkVehicle(String ticketId, PaymentStrategy paymentStrategy){
        Ticket ticket = ticketManager.getTicket(ticketId);
        ParkingSpot parkedSpot = ticket.getParkingSpot();

        PaymentCalculator paymentCalculator = PaymentCalculatorFactory.getPaymentCalculator(ticket.getVehicle().getVehicleType());
        double bill = paymentCalculator.calculatePayment(ticket);
        paymentStrategy.makePayment(bill);

        ticketManager.removeTicket(ticketId);
        parkedSpot.removeVehicle();
        System.out.println("Vehicle " + ticket.getVehicle().getNumberPlate() + " is out of parking area, with total amount : " + bill);
    }
}
