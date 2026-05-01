package LLDDesigns.BookMyShow.Strategy;

public class CardPayment implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Payment of " + amount +" is completed Using Card");
    }
}
