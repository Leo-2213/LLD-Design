package LLDDesigns.ATM.Model;

public class BankAccount {
    private final String accountNumber;
    private  int accountBalance;
    private final String accountHolderName;
    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public BankAccount(String accountNumber, int accountBalance, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountHolderName = accountHolderName;
    }
}
