package q8;

public class BankAccount {
    // Instance variables
    private String name;
    private double balance;
    private String type;

    // Constructor using 'this' to resolve variable shadowing
    public BankAccount(String name, double balance, String type) {
        this.name = name;       // 'this.name' refers to the instance variable
        this.balance = balance;
        this.type = type;
    }

    // Method to deposit amount using 'this'
    public void deposit(double balance) {
        if (balance > 0) {
            this.balance += balance; // 'this.balance' is the instance variable
            System.out.println("Deposited: " + balance);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    // Method to display account details
    public void displayAccount() {
        System.out.println("Account Holder: " + this.name);
        System.out.println("Account Type: " + this.type);
        System.out.println("Balance: $" + this.balance);
    }

    // Main method to test
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("John Doe", 1000.0, "Savings");
        acc.displayAccount();

        System.out.println("\nDepositing $500...");
        acc.deposit(500);

        System.out.println("\nUpdated Account Details:");
        acc.displayAccount();
    }
}
