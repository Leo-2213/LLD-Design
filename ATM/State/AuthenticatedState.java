package LLDDesigns.ATM.State;

import LLDDesigns.ATM.Enum.ATMStatus;
import LLDDesigns.ATM.Model.Card;
import LLDDesigns.ATM.Service.ATMMachine;

public class AuthenticatedState extends DefaultState {
    private final ATMMachine atmMachine;

    public AuthenticatedState(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void selectOption(String option) {
        System.out.println("Selected Option : " + option);
        atmMachine.setState(new DispenseCashState(atmMachine));
    }
    @Override
    public ATMStatus getStatus() {
        return null;
    }
}
