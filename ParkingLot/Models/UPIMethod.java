package LLDDesigns.ParkingLot.Models;

public class UPIMethod implements PaymentStrategy{

    @Override
    public void makePayment(double amount) {
        System.out.println("Payment done Using UPI :" + amount);
    }
}
