package LLDDesigns.ATM.State;

import LLDDesigns.ATM.Enum.ATMStatus;
import LLDDesigns.ATM.Model.Card;

public interface ATMState {
    public void insertCard(Card card) throws IllegalAccessException;
    public void enterPin(String pin) throws IllegalAccessException;
    public void  selectOption( String option) throws IllegalAccessException;
    public void dispenseCash(int cash) throws IllegalAccessException;
    public void ejectCard() throws IllegalAccessException;
    public ATMStatus getStatus();
}
