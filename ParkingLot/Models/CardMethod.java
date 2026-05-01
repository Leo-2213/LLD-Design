package LLDDesigns.ParkingLot.Models;

public class CardMethod implements PaymentStrategy{
    @Override
    public void makePayment(double amount) {
        System.out.println("Payment done using  Card :" + amount);
    }
}
