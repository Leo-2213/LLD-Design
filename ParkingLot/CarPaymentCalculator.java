package LLDDesigns.ParkingLot;

import LLDDesigns.ParkingLot.Models.Ticket;

public class CarPaymentCalculator extends BasePaymentCalculator{
    private static final double CHARGE_PER_HOUR = 7.0;

    @Override
    public double calculatePayment(Ticket ticket) {
        return computeHours(ticket) * CHARGE_PER_HOUR;
    }
}
