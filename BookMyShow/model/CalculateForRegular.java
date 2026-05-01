package LLDDesigns.BookMyShow.model;

public class CalculateForRegular {
    private static final double PRICE_FOR_REGULAR = 250;
    public static double calculate(int val){
        return PRICE_FOR_REGULAR * val;
    }
}
