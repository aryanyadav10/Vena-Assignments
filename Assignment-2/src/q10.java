import java.util.*;

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
        if (!transactionLog.containsKey(accountNumber)) {
            transactionLog.put(accountNumber, new ArrayList<>());
        }
        transactionLog.get(accountNumber).add(transaction);
    }

    // Generate the statement for an account, sorted by transaction time
    public void generateStatement(String accountNumber) {
        List<Transaction> transactions = transactionLog.get(accountNumber);

        if (transactions == null || transactions.isEmpty()) {
            System.out.println("No transactions found for account " + accountNumber);
            return;
        }

        // Sort using traditional Comparator (ascending order of time)
        Collections.sort(transactions, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                return t1.getTime().compareTo(t2.getTime());
            }
        });

        // Print the sorted transactions
        System.out.println("Statement for Account: " + accountNumber);
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public static void main(String[] args) {
        q10 bank = new q10();

        // Add transactions for account 12345
        bank.addTransaction("12345", new Transaction("Deposit", 500.0, createDate(2025, Calendar.MARCH, 12, 9, 0)));
        bank.addTransaction("12345", new Transaction("Withdrawal", 200.0, createDate(2025, Calendar.MARCH, 12, 10, 30)));
        bank.addTransaction("12345", new Transaction("Transfer", 300.0, createDate(2025, Calendar.MARCH, 12, 8, 0)));

        // Add transaction for account 67890
        bank.addTransaction("67890", new Transaction("Deposit", 1000.0, createDate(2025, Calendar.MARCH, 11, 14, 0)));

        // Generate statements
        bank.generateStatement("12345");
        bank.generateStatement("67890");
    }

    // Static method accessible inside main
    private static Date createDate(int year, int month, int day, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute);
        return cal.getTime();
    }
}
