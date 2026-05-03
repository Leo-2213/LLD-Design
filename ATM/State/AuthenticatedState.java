package LLDDesigns.ATM.State;

import LLDDesigns.ATM.Enum.ATMStatus;
import LLDDesigns.ATM.Model.Card;
import LLDDesigns.ATM.Service.ATMMachine;

public class AuthenticatedState implements ATMState {
    private final ATMMachine atmMachine;

    public AuthenticatedState(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Card already inserted");
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("Authentication already completed");
    }

    @Override
    public void selectOption(String option) {
        System.out.println("Selected Option : " + option);
        atmMachine.setState(new DispenseCashState(atmMachine));
    }

    @Override
    public void dispenseCash(int cash) {
        System.out.println("Option not selected");
    }

    @Override
    public void ejectCard() {
        System.out.println("Invalid Move");
    }

    @Override
    public ATMStatus getStatus() {
        return null;
    }
}
