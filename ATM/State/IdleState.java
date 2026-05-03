package LLDDesigns.ATM.State;

import LLDDesigns.ATM.Enum.ATMStatus;
import LLDDesigns.ATM.Model.Card;
import LLDDesigns.ATM.Service.ATMMachine;


public class IdleState implements ATMState{
    private final ATMMachine atmMachine;

    public IdleState(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void insertCard(Card card){
        atmMachine.insertCard(card);
        System.out.println("Card Inserted.");
        atmMachine.setState(new CardInsertedState(atmMachine));
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("No Card inserted");
    }

    @Override
    public void selectOption(String option) {
        System.out.println("No Card inserted");
    }

    @Override
    public void dispenseCash(int cash) {
        System.out.println("No Card inserted");
    }

    @Override
    public void ejectCard() {
        System.out.println("No Card inserted");
    }

    @Override
    public ATMStatus getStatus() {
        return null;
    }
}
