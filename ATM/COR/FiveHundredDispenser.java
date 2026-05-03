package LLDDesigns.ATM.COR;

import LLDDesigns.ATM.Model.ATM;

public class FiveHundredDispenser implements CashDispenser{
    private  CashDispenser next;
    @Override
    public void setNextDispenser(CashDispenser nextDispenser) {
        this.next = nextDispenser;
    }

    @Override
    public boolean canDispense(ATM atm, int amount) {
         int count = atm.getFiveHundredCount();
         int notes = Math.min(amount/500, count);
         int reminder = amount - notes * 500;
         return reminder == 0 || (next != null  && next.canDispense(atm, reminder) );
    }

    @Override
    public void dispense(ATM atm, int amount) {
        int count = atm.getFiveHundredCount();
        int notes = Math.min(count, amount/500 );
        atm.setFiveHundredCount( count - notes);

        int reminder = amount - notes*500;
        if(notes > 0) System.out.println("Dispensed " + notes + " notes from ATM");

        if(reminder > 0 && next != null){
            next.dispense(atm, reminder);
        }

    }
}
