# Java Banking System Simulation

An advanced Object-Oriented console interface that mimics secure authentication and polymorphic financial accounts.

## Features Let Loose
- **Abstraction**: Uses an abstract base class `Account`, forcing unique execution of logic for specific overrides down the chain.
- **Polymorphism**: Two concrete implementations `SavingsAccount` (rigidly stops at $0) and `CurrentAccount` (business-style rules with a dynamically factored $500 Overdraft leniency).
- **Security Check Mechanism**: Implements active session "Login" states requesting `PIN` comparisons to allow execution inside an authenticated sub-loop menu locking down commands like deposits and withdrawals.
- **Java Collections Framework**: Employs `ArrayList` objects functioning as a live operational memory database to retrieve and act upon distinct profiles concurrently.

## Run The Simulation
Compile and launch natively:
```bash
javac BankingSystem.java
java BankingSystem
```
There are two dummy accounts pre-seeded for testing:
- **Account 1001**: Savings (Alice) | PIN: `1234`
- **Account 1002**: Current (Bob)   | PIN: `9999`
