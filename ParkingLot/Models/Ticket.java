package LLDDesigns.ParkingLot.Models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class Ticket {
    private final String ticketId;
    private final  Vehicle vehicle;
    private final LocalDateTime entryTime;
    private final  ParkingSpot parkingSpot;

    public Ticket( ParkingSpot parkingSpot, Vehicle vehicle) {
        this.parkingSpot = parkingSpot;
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.entryTime = LocalDateTime.now();
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}
