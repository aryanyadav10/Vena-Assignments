import java.util.*;

// Interface defining transaction behavior
interface Transaction {
    void deposit(double amount);
    void withdraw(double amount);
}

// Abstract class representing a generic bank account
abstract class BankAccount implements Transaction {
    protected String accountNumber;
    protected String accountHolder;
    protected double balance;

    public BankAccount(String accountNumber, String accountHolder) {
        this(accountNumber, accountHolder, 0.0);
    }

    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public abstract void showAccountType();

    public void showBalance() {
        System.out.println(accountHolder + "'s Balance: $" + balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}

// Concrete subclass: SavingsAccount
class SavingsAccount extends BankAccount {
    public SavingsAccount(String accountNumber, String accountHolder) {
        super(accountNumber, accountHolder);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Savings: Deposited $" + amount);
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Savings: Withdrew $" + amount);
        } else {
            System.out.println("Savings: Insufficient balance!");
        }
    }

    @Override
    public void showAccountType() {
        System.out.println("Account Type: Savings Account");
    }
}

// Concrete subclass: CheckingAccount
class CheckingAccount extends BankAccount {
    private final double overdraftLimit = 500;

    public CheckingAccount(String accountNumber, String accountHolder) {
        super(accountNumber, accountHolder);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Checking: Deposited $" + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance + overdraftLimit) >= amount) {
            balance -= amount;
            System.out.println("Checking: Withdrew $" + amount);
        } else {
            System.out.println("Checking: Overdraft limit exceeded!");
        }
    }

    @Override
    public void showAccountType() {
        System.out.println("Account Type: Checking Account");
    }
}

// Main class to run the program
public class BankSystem {
    static Scanner sc = new Scanner(System.in);
    static Map<String, BankAccount> accounts = new HashMap<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nWelcome to Java Bank!");
            System.out.println("1. New Account");
            System.out.println("2. Existing Account Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    loginAccount();
                    break;
                case 3:
                    running = false;
                    System.out.println("Thank you for using Java Bank!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void createAccount() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Choose account type (1. Savings, 2. Checking): ");
        int type = sc.nextInt();
        sc.nextLine();

        String accountNumber = UUID.randomUUID().toString().substring(0, 8); // simple unique ID

        BankAccount account;
        if (type == 1) {
            account = new SavingsAccount(accountNumber, name);
        } else {
            account = new CheckingAccount(accountNumber, name);
        }

        accounts.put(accountNumber, account);
        System.out.println("Account created! Your account number is: " + accountNumber);
    }

    static void loginAccount() {
        System.out.print("Enter your account number: ");
        String accNo = sc.nextLine();

        BankAccount account = accounts.get(accNo);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("Welcome back, " + account.getAccountHolder() + "!");
        account.showAccountType();

        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int opt = sc.nextInt();

            switch (opt) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double dep = sc.nextDouble();
                    account.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double wd = sc.nextDouble();
                    account.withdraw(wd);
                    break;
                case 3:
                    account.showBalance();
                    break;
                case 4:
                    loggedIn = false;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
