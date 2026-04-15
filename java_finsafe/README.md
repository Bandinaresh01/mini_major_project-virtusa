# FinSafe Transaction Validator

A robust Java application simulating a bank account object that validates deposits and withdrawals.

## Features
- **Encapsulation**: The `Account` class securely manages the balance and account details using private fields.
- **Custom Exception Handling**: Throws an `InsufficientFundsException` if a user attempts to withdraw more than their available balance.
- **Transaction History**: Automatically tracks and stores only the last 5 transactions.
- **Mini Statement**: Generates a formatted log showing the account status and the most recent events.

## Setup and Execution
1. Compile the Java script:
   ```bash
   javac FinSafe.java
   ```
2. Run the application:
   ```bash
   java FinSafe
   ```

## Example Output
```
Welcome to FinSafe Transaction Validator
Deposited: $200.0 | New Balance: $700.0
Withdrew: $100.0 | New Balance: $600.0
Withdrew: $50.0 | New Balance: $550.0
Deposited: $300.0 | New Balance: $850.0

[ERROR] Transaction Failed: Insufficient funds! Cannot withdraw $1000.0 with balance $850.0

--- MINI STATEMENT ---
Account: 123-456-789
Current Balance: $850.00
Last Transactions:
 - INITIAL BALANCE: $500.00
 - DEPOSIT: $200.00
 - WITHDRAWAL: $100.00
 - WITHDRAWAL: $50.00
 - DEPOSIT: $300.00
----------------------
```
