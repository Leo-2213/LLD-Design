package LLDDesigns.ATM.COR;

import LLDDesigns.ATM.Model.ATM;

public class OneHundredDispenser implements CashDispenser{
    private CashDispenser next;
    @Override
    public void setNextDispenser(CashDispenser nextDispenser) {
        this.next = nextDispenser;
    }

    @Override
    public boolean canDispense(ATM atm, int amount) {
        int count = atm.getOneHundredCount();
        int notes = Math.min(amount/100, count);
        int reminder = amount - notes * 100;
        return reminder == 0 || (next != null  && next.canDispense(atm, reminder) );
    }

    @Override
    public void dispense(ATM atm, int amount) {

    }
}
