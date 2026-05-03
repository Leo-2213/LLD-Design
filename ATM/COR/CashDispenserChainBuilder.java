package LLDDesigns.ATM.COR;

public class CashDispenserChainBuilder {
    public static CashDispenser buildChain(){
        CashDispenser d1 = new FiveHundredDispenser();
        CashDispenser d2 = new TwoHundredDispenser();
        CashDispenser d3 = new OneHundredDispenser();

        d1.setNextDispenser(d2);
        d2.setNextDispenser(d3);

        return d1;
    }
}
