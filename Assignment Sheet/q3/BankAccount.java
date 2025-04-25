package q3;

public class BankAccount {

    // Private fields
    private String accountHolder;
    private double balance;
    private String accountNumber;

    // Constructor
    public BankAccount(String accountHolder, double balance, String accountNumber) {
        this.accountHolder = accountHolder;
        setBalance(balance); // Use setter for validation
        this.accountNumber = accountNumber;
    }

    // Getter and Setter for balance with validation
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Balance cannot be negative. Balance not updated.");
        }
    }

    // Display account details
    public void showDetails() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
    }

    // Main method to test the class
    public static void main(String[] args) {
        // Create a new bank account
        BankAccount account = new BankAccount("John Doe", 1000.0, "ACC123456");

        // Show initial details
        account.showDetails();

        // Try setting a negative balance
        account.setBalance(-500.0);

        // Update with valid balance
        account.setBalance(1500.0);

        // Show updated details
        System.out.println("\nAfter update:");
        account.showDetails();
    }
}

