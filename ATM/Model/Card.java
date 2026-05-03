package LLDDesigns.ATM.Model;

public class Card {
    private final String cardId;
    private final String dateOfExpiry;
    private final String nameOnCard;
    private final String cvv;
    private final String pin;
    private final BankAccount bankAccount;

    public String getPin() {
        return pin;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public String getCardId() {
        return cardId;
    }

    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public String getCvv() {
        return cvv;
    }

    public Card(String cardId, String dateOfExpiry, String nameOnCard, String cvv, String pin, BankAccount bankAccount) {
        this.cardId = cardId;
        this.dateOfExpiry = dateOfExpiry;
        this.nameOnCard = nameOnCard;
        this.cvv = cvv;
        this.pin = pin;
        this.bankAccount = bankAccount;
    }
}
