package LLDDesigns.ATM.State;

import LLDDesigns.ATM.Enum.ATMStatus;
import LLDDesigns.ATM.Model.Card;

public class DefaultState implements ATMState{
    @Override
    public void insertCard(Card card) throws IllegalAccessException {
        throw new IllegalAccessException("Not valid Choice");
    }

    @Override
    public void enterPin(String pin) throws IllegalAccessException {
        throw new IllegalAccessException("Not valid Choice");
    }

    @Override
    public void selectOption(String option) throws IllegalAccessException {
        throw new IllegalAccessException("Not valid Choice");
    }

    @Override
    public void dispenseCash(int cash) throws IllegalAccessException {
        throw new IllegalAccessException("Not valid Choice");
    }

    @Override
    public void ejectCard() throws IllegalAccessException {
        throw new IllegalAccessException("Not valid Choice");
    }

    @Override
    public ATMStatus getStatus() {
        return null;
    }
}
