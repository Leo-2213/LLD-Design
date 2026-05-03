package LLDDesigns.ATM.State;

import LLDDesigns.ATM.Enum.ATMStatus;
import LLDDesigns.ATM.Model.Card;

public interface ATMState {
    public void insertCard(Card card);
    public void enterPin(String pin);
    public void  selectOption( String option);
    public void dispenseCash(int cash);
    public void ejectCard();
    public ATMStatus getStatus();
}
