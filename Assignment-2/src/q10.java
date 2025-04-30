import java.util.*;

//Problem 10: Bank Transaction Log (Map, List)
//Track transactions using Map<String, List<Transaction>> keyed by account number.
//        •	Generate statements, sort by transaction time.

class Transaction {
    private String description;
    private double amount;
    private Date time;

    public Transaction(String description, double amount, Date time) {
        this.description = description;
        this.amount = amount;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Transaction: " + description + ", Amount: " + amount + ", Time: " + time;
    }
}

public class q10 {
    // Map to store transactions by account number
    private Map<String, List<Transaction>> transactionLog;

    public q10() {
        transactionLog = new HashMap<>();
    }

    // Add a transaction to the account's transaction log
    public void addTransaction(String accountNumber, Transaction transaction) {
        // Check if the account already has transactions
        if (!transactionLog.containsKey(accountNumber)) {
            transactionLog.put(accountNumber, new ArrayList<>()); // Create a new list if the account doesn't exist
        }

        // Add the transaction to the account's list
        List<Transaction> transactions = transactionLog.get(accountNumber);
        transactions.add(transaction);
    }

    // Generate the statement for an account, sorted by transaction time
    public void generateStatement(String accountNumber) {
        List<Transaction> transactions = transactionLog.get(accountNumber);

        if (transactions == null || transactions.isEmpty()) {
            System.out.println("No transactions found for account " + accountNumber);
            return;
        }

        // Sorting transactions manually using bubble sort for simplicity (ascending order of time)
        for (int i = 0; i < transactions.size() - 1; i++) {
            for (int j = 0; j < transactions.size() - 1 - i; j++) {
                if (transactions.get(j).getTime().after(transactions.get(j + 1).getTime())) {
                    // Swap transactions[j] and transactions[j+1]
                    Transaction temp = transactions.get(j);
                    transactions.set(j, transactions.get(j + 1));
                    transactions.set(j + 1, temp);
                }
            }
        }

        // Print the transactions in sorted order
        System.out.println("Statement for Account: " + accountNumber);
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public static void main(String[] args) {
        q10 bank = new q10();

        // Create some sample transactions
        bank.addTransaction("12345", new Transaction("Deposit", 500.0, new Date(2025, Calendar.MARCH, 12, 9, 0)));
        bank.addTransaction("12345", new Transaction("Withdrawal", 200.0, new Date(2025, Calendar.MARCH, 12, 10, 30)));
        bank.addTransaction("67890", new Transaction("Deposit", 1000.0, new Date(2025, Calendar.MARCH, 11, 14, 0)));
        bank.addTransaction("12345", new Transaction("Transfer", 300.0, new Date(2025, Calendar.MARCH, 12, 8, 0)));

        // Generate the statement for account "12345"
        bank.generateStatement("12345");

        // Generate the statement for account "67890"
        bank.generateStatement("67890");
    }
}
