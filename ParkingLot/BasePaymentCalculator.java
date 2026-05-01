package LLDDesigns.ParkingLot;

import LLDDesigns.ParkingLot.Models.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;


public abstract class BasePaymentCalculator implements PaymentCalculator{
    protected long computeHours(Ticket ticket){
        long minutes = Duration.between(ticket.getEntryTime(), LocalDateTime.now()).toMinutes();
        return Math.max(1, (long) Math.ceil(minutes/60.0));
    }
}
