import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

//Assignment 2: Bank Account Synchronization
//Background:
//Simulate a multi-threaded banking system that supports concurrent deposits and withdrawals from shared accounts.
//Requirements:
//Implement a BankAccount class with:
//Methods: deposit(double amount), withdraw(double amount)
//Thread-safe balance modification
//Create multiple threads representing users performing deposits and withdrawals
//Ensure no overdrawing and correct final balance
//Bonus Requirements:
//Add transaction logging
//Add per-account lock to prevent locking the entire bank

class BankAccount {
    private int accountNumber;
    private String accountHolderName;
    private double accountBalance;

    private final ReentrantLock lock = new ReentrantLock(); // Lock specific to each account

    // Static map to store all bank accounts by account number
    static Map<Integer, BankAccount> accountMap = new HashMap<>();
    static Set<String> transactionLog = new HashSet<>();

    // Constructor to initialize the bank account
    BankAccount(int accountNumber, String accountHolderName, double accountBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountBalance = accountBalance;
    }

    // Deposit method with thread-safe balance modification
    void deposit(double amount) {
        lock.lock(); // Lock the account to make this operation thread-safe
        try {
            accountBalance += amount;
            logger("Deposit request for accountNumber: " + accountNumber + " with amount: " + amount + " completed successfully! New balance: " + accountBalance);
        } finally {
            lock.unlock(); // Unlock the account
        }
    }

    // Withdraw method with thread-safe balance modification and overdrawing check
    void withdraw(double amount) {
        lock.lock(); // Lock the account to make this operation thread-safe
        try {
            if (amount <= accountBalance) {
                accountBalance -= amount;
                logger("Withdraw request for accountNumber: " + accountNumber + " with amount: " + amount + " completed successfully! New balance: " + accountBalance);
            } else {
                logger("Insufficient funds for accountNumber: " + accountNumber + " withdrawal request for amount: " + amount);
                System.out.println("Insufficient funds for accountNumber: " + accountNumber);
            }
        } finally {
            lock.unlock(); // Unlock the account
        }
    }

    // Logger method to log transactions
    private void logger(String message) {
        synchronized (transactionLog) {
            transactionLog.add(message); // Add the transaction message to the log
        }
    }

    // Get the account balance (for display or checking)
    public double getAccountBalance() {
        return accountBalance;
    }

    // Static method to get an account by account number
    static BankAccount getAccountByNumber(int accountNumber) {
        return accountMap.get(accountNumber);
    }

    // Static method to add accounts to the bank (for initialization)
    static void addAccount(BankAccount account) {
        accountMap.put(account.accountNumber, account);
    }
}

class BankTransactionTask implements Runnable {
    private int accountNumber;
    private String transactionType;
    private double amount;

    // Constructor for each transaction (deposit or withdraw)
    public BankTransactionTask(int accountNumber, String transactionType, double amount) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    @Override
    public void run() {
        BankAccount account = BankAccount.getAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("Account not found for accountNumber: " + accountNumber);
            return;
        }

        if (transactionType.equalsIgnoreCase("deposit")) {
            account.deposit(amount);  // Perform deposit
        } else if (transactionType.equalsIgnoreCase("withdraw")) {
            account.withdraw(amount);  // Perform withdrawal
        }
    }
}

public class q1 {
    public static void main(String[] args) {
        // Initialize bank accounts
        BankAccount acc1 = new BankAccount(1234, "Joe", 10000);
        BankAccount acc2 = new BankAccount(4321, "John", 20000);

        // Add accounts to the bank system (static map)
        BankAccount.addAccount(acc1);
        BankAccount.addAccount(acc2);

        // Create multiple threads representing users performing deposits and withdrawals
        Thread t1 = new Thread(new BankTransactionTask(1234, "deposit", 5000));   // User Joe deposits
        Thread t2 = new Thread(new BankTransactionTask(4321, "withdraw", 10000));  // User John withdraws
        Thread t3 = new Thread(new BankTransactionTask(1234, "withdraw", 2000));   // User Joe withdraws
        Thread t4 = new Thread(new BankTransactionTask(4321, "deposit", 7000));    // User John deposits

        // Start threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Wait for threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print transaction log
        System.out.println("\nTransaction Log:");
        for (String log : BankAccount.transactionLog) {
            System.out.println(log);
        }

        // Print final balances
        System.out.println("\nFinal Account Balances:");
        System.out.println("Joe's account balance: " + acc1.getAccountBalance());
        System.out.println("John's account balance: " + acc2.getAccountBalance());
    }
}
