package LLDDesigns.ParkingLot;

import LLDDesigns.ParkingLot.Models.Ticket;

public interface PaymentCalculator {
    public double calculatePayment(Ticket ticket);
}
