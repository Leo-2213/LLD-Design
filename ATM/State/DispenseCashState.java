package LLDDesigns.ATM.State;

import LLDDesigns.ATM.COR.CashDispenser;
import LLDDesigns.ATM.COR.CashDispenserChainBuilder;
import LLDDesigns.ATM.Enum.ATMStatus;
import LLDDesigns.ATM.Model.Card;
import LLDDesigns.ATM.Service.ATMMachine;

public class DispenseCashState implements ATMState{
    private final ATMMachine atmMachine;

    public DispenseCashState(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Card is already in");
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("Authentication already completed");
    }

    @Override
    public void selectOption(String option) {
        System.out.println("Option already selected");
    }

    @Override
    public void dispenseCash(int cash) {
        int atmBalance = atmMachine.getAtm().getCashAvailable();
        if(cash > atmBalance ){
            System.out.println("Insufficient balance in atm");
            ejectCard();
        }
        int balanceInAccount = atmMachine.getCard().getBankAccount().getAccountBalance();
        if(cash > balanceInAccount){
            System.out.println("Insufficient balance in Account");
            ejectCard();
        }

        CashDispenser cashDispenser =  CashDispenserChainBuilder.buildChain();
        if(cashDispenser.canDispense(atmMachine.getAtm(), cash)){
            cashDispenser.dispense(atmMachine.getAtm(), cash);
            atmMachine.getAtm().setCashAvailable(atmBalance - cash);
            atmMachine.getCard().getBankAccount().setAccountBalance(balanceInAccount - cash);
            System.out.println("Cash Dispensed : " + cash);
        }else{
            System.out.println("Cash can not be Dispensed. Invalid amount");
            ejectCard();
        }
    }

    @Override
    public void ejectCard() {
        atmMachine.setCard(null);
        System.out.println("Card Ejected");
        atmMachine.setState( new IdleState(atmMachine));
    }

    @Override
    public ATMStatus getStatus() {
        return null;
    }
}
