package LLDDesigns.ATM.Service;

import LLDDesigns.ATM.COR.ATMStateFactory;
import LLDDesigns.ATM.Model.ATM;
import LLDDesigns.ATM.Model.Card;
import LLDDesigns.ATM.Repository.ATMRepository;
import LLDDesigns.ATM.State.ATMState;
import LLDDesigns.ATM.State.IdleState;

public class ATMMachine {
    private final ATM atm;
    private ATMState state;
    private final ATMRepository atmRepository;
    private Card card;


    public ATMMachine(ATM atm, ATMRepository atmRepository){
        this.atmRepository = atmRepository;
        this.atm = atm;
        this.state = ATMStateFactory.getState(atm.getAtmStatus(), this);
    }
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public ATMRepository getAtmRepository() {
        return atmRepository;
    }

    public ATMState getState() {
        return state;
    }

    public void setState(ATMState state) {
        this.state = state;
    }

    public ATM getAtm() {
        return atm;
    }

    public void insertCard(Card card){state.insertCard(card);}
    public void enterPin(String pin){state.enterPin(pin);}
    public void  selectOption( String option){state.selectOption(option);}
    public void dispenseCash(int cash){state.dispenseCash(cash);}
    public void ejectCard(){state.ejectCard();}

}
