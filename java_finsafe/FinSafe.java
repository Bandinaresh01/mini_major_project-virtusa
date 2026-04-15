import java.util.ArrayList;
import java.util.List;

// Custom Exception
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// Transaction Record
class Transaction {
    String type;
    double amount;
    long timestamp;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return type + ": $" + String.format("%.2f", amount);
    }
}

// Encapsulated Account Class
class Account {
    private String accountNumber;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        addTransaction(new Transaction("INITIAL BALANCE", initialBalance));
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        balance += amount;
        addTransaction(new Transaction("DEPOSIT", amount));
        System.out.println("Deposited: $" + amount + " | New Balance: $" + balance);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds! Cannot withdraw $" + amount + " with balance $" + balance);
        }
        balance -= amount;
        addTransaction(new Transaction("WITHDRAWAL", amount));
        System.out.println("Withdrew: $" + amount + " | New Balance: $" + balance);
    }

    private void addTransaction(Transaction t) {
        transactionHistory.add(t);
        // Keep only the last 5 transactions
        if (transactionHistory.size() > 5) {
            transactionHistory.remove(0);
        }
    }

    public void printMiniStatement() {
        System.out.println("\n--- MINI STATEMENT ---");
        System.out.println("Account: " + accountNumber);
        System.out.println("Current Balance: $" + String.format("%.2f", balance));
        System.out.println("Last Transactions:");
        for (Transaction t : transactionHistory) {
            System.out.println(" - " + t);
        }
        System.out.println("----------------------\n");
    }
}

// Main Driver Class
public class FinSafe {
    public static void main(String[] args) {
        System.out.println("Welcome to FinSafe Transaction Validator");

        Account myAccount = new Account("123-456-789", 500.00);

        try {
            myAccount.deposit(200.00);
            myAccount.withdraw(100.00);
            myAccount.withdraw(50.00);
            myAccount.deposit(300.00);
            myAccount.withdraw(1000.00); // This should throw the custom exception
        } catch (InsufficientFundsException e) {
            System.out.println("\n[ERROR] Transaction Failed: " + e.getMessage());
        }

        myAccount.printMiniStatement();
    }
}
