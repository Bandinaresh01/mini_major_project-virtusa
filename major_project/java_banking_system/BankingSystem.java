import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// OOP Abstraction
abstract class Account {
    protected int accountNumber;
    protected String accountName;
    protected double balance;
    protected List<String> transactions = new ArrayList<>();
    // Basic pseudo-auth pin (In reality, use hashing)
    protected String pin;

    public Account(int accountNumber, String accountName, double initialDeposit, String pin) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = initialDeposit;
        this.pin = pin;
        addTransaction("Initial Deposit: $" + initialDeposit);
    }

    public boolean authenticate(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposited: $" + amount + " | Balance: $" + balance);
            System.out.println("Deposit Successful!");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void addTransaction(String event) {
        transactions.add(event);
    }

    public void checkBalance() {
        System.out.println("Current Balance for Account [" + accountNumber + "]: $" + balance);
    }

    public void printHistory() {
        System.out.println("\n--- Transaction History [" + accountName + "] ---");
        for (String t : transactions) {
            System.out.println(" • " + t);
        }
        System.out.println("---------------------------------");
    }

    // Force subclasses to define how withdrawals uniquely operate 
    public abstract void withdraw(double amount);
}

class SavingsAccount extends Account {
    public SavingsAccount(int accNum, String name, double initDep, String pin) {
        super(accNum, name, initDep, pin);
    }

    @Override
    public void withdraw(double amount) {
        // Savings logic limits drawing past balance
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            addTransaction("Withdrawal: $" + amount + " | Balance: $" + balance);
            System.out.println("Withdrawal Successful!");
        } else {
            System.out.println("Withdrawal Failed: Insufficient funds in Savings.");
        }
    }
}

class CurrentAccount extends Account {
    // Current might allow overdraft conceptually
    private double overdraftLimit = 500.00;

    public CurrentAccount(int accNum, String name, double initDep, String pin) {
        super(accNum, name, initDep, pin);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance + overdraftLimit) >= amount) {
            balance -= amount;
            addTransaction("Withdrawal: $" + amount + " | Balance: $" + balance);
            System.out.println("Withdrawal Successful! (Overdraft protected)");
        } else {
            System.out.println("Withdrawal Failed: Exceeds overdraft limit.");
        }
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Simulation database
        List<Account> database = new ArrayList<>();
        
        // Seed some data
        database.add(new SavingsAccount(1001, "Alice", 1000.00, "1234"));
        database.add(new CurrentAccount(1002, "Bob", 500.00, "9999"));

        System.out.println("Welcome to SecureBank Console");

        while (true) {
            System.out.println("\n1. Login to Account");
            System.out.println("2. System Exit");
            System.out.print("Choice: ");
            String mainChoice = scanner.nextLine();

            if (mainChoice.equals("2")) break;
            
            if (mainChoice.equals("1")) {
                System.out.print("Enter Account Number: ");
                int targetAcc = Integer.parseInt(scanner.nextLine());
                
                Account activeAccount = null;
                for (Account acc : database) {
                    if (acc.accountNumber == targetAcc) {
                        activeAccount = acc;
                        break;
                    }
                }

                if (activeAccount == null) {
                    System.out.println("Account not found.");
                    continue;
                }

                System.out.print("Enter PIN: ");
                String inputPin = scanner.nextLine();
                
                if (!activeAccount.authenticate(inputPin)) {
                    System.out.println("ACCESS DENIED: Invalid PIN.");
                    continue;
                }

                System.out.println("ACCESS GRANTED. Welcome " + activeAccount.accountName);

                while (true) {
                    System.out.println("\n[MENU]");
                    System.out.println("1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Check Balance");
                    System.out.println("4. View History");
                    System.out.println("5. Logout");
                    System.out.print("Action: ");
                    String action = scanner.nextLine();

                    if (action.equals("1")) {
                        System.out.print("Amount to deposit: ");
                        double amt = Double.parseDouble(scanner.nextLine());
                        activeAccount.deposit(amt);
                    } else if (action.equals("2")) {
                        System.out.print("Amount to withdraw: ");
                        double amt = Double.parseDouble(scanner.nextLine());
                        activeAccount.withdraw(amt);
                    } else if (action.equals("3")) {
                        activeAccount.checkBalance();
                    } else if (action.equals("4")) {
                        activeAccount.printHistory();
                    } else if (action.equals("5")) {
                        System.out.println("Logged out securely.");
                        break;
                    }
                }
            }
        }
    }
}
