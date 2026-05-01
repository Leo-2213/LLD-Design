package LLDDesigns.ParkingLot.Models;

public class Cash implements PaymentStrategy{
    @Override
    public void makePayment(double amount) {
        System.out.println("Payment done by Using Cash " + amount);
    }
}
