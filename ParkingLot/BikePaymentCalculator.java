package LLDDesigns.ParkingLot;

import LLDDesigns.ParkingLot.Models.Ticket;

public class BikePaymentCalculator extends BasePaymentCalculator{
    private static final double PER_HOUR_CHARGE = 15.0;
    @Override
    public double calculatePayment(Ticket ticket) {
        return computeHours(ticket) * PER_HOUR_CHARGE;
    }
}
