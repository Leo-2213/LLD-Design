package LLDDesigns.ATM.State;

import LLDDesigns.ATM.Enum.ATMStatus;
import LLDDesigns.ATM.Model.Card;
import LLDDesigns.ATM.Service.ATMMachine;

public class CardInsertedState extends DefaultState {
    private final ATMMachine atmMachine;
    public CardInsertedState(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void enterPin(String pin) {
        if(atmMachine.getCard().getPin().equals(pin)){
            System.out.println("PIN Successfully Verified");
            atmMachine.setState(new AuthenticatedState(atmMachine));
        }else{
            System.out.println("Entered wrong PIN, Please enter it again");
            atmMachine.setState(new CardInsertedState(atmMachine));
        }
    }
}
