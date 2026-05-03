package LLDDesigns.ATM;

import LLDDesigns.ATM.Model.ATM;
import LLDDesigns.ATM.Model.BankAccount;
import LLDDesigns.ATM.Model.Card;
import LLDDesigns.ATM.Repository.ATMRepository;
import LLDDesigns.ATM.Service.ATMMachine;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM.ATMBuilder().id(1).oneHundredCount(50).twoHundredCount(20).fiveHundredCount(10).cashAvailable(1400).build();
        ATMRepository atmRepository = new ATMRepository();
        atmRepository.save("1", atm);

        Card visaCard = new Card("1010","0909","Abhijeet", "223","8989",
                                    new BankAccount("11111111", 458899, "Abhijeet"));

        ATMMachine machine = new ATMMachine(atm, atmRepository);
        machine.insertCard(visaCard);
        machine.enterPin("8989");
        machine.selectOption("Withdraw");
        machine.dispenseCash(1200);

    }
}
