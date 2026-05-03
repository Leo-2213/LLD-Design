package LLDDesigns.ATM.COR;

import LLDDesigns.ATM.Enum.ATMStatus;
import LLDDesigns.ATM.Service.ATMMachine;
import LLDDesigns.ATM.State.*;

public class ATMStateFactory {

    public static ATMState getState(ATMStatus atmStatus, ATMMachine atmMachine) {
       return switch (atmStatus){
           case IDLE -> new IdleState(atmMachine);
           case CARD_INSERTED -> new CardInsertedState(atmMachine);
           case AUTHENTICATED -> new AuthenticatedState(atmMachine);
           case DISPENSE_CASH -> new DispenseCashState(atmMachine);
       };
    }
}
