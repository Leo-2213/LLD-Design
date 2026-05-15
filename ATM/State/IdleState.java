package LLDDesigns.ATM.State;

import LLDDesigns.ATM.Enum.ATMStatus;
import LLDDesigns.ATM.Model.Card;
import LLDDesigns.ATM.Service.ATMMachine;


public class IdleState extends DefaultState{
    private final ATMMachine atmMachine;

    public IdleState(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void insertCard(Card card){
        atmMachine.setCard(card);
        System.out.println("Card Inserted.");
        atmMachine.setState(new CardInsertedState(atmMachine));
    }
    @Override
    public ATMStatus getStatus() {
        return null;
    }
}
