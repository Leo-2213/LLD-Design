package LLDDesigns.BookMyShow.Strategy;


public class UPIPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Payment of " + amount +" is completed Using UPI");
    }
}
