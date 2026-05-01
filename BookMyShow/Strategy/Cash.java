package LLDDesigns.BookMyShow.Strategy;

public class Cash implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Payment of " + amount +" is completed Using Cash");
    }
}
