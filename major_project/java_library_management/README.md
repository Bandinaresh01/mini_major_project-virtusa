# Java Library Management System

An Object-Oriented console application to track catalogs, user allocations, and book states without needing an attached SQL database (it utilizes runtime Data Collections).

## Capabilities
- **Collections Framework**: Manages dynamic Arrays of `Book` and `User` objects.
- **Issuing Logic**: Issues an inventory item out to users and stamps it with an active `LocalDate` constraint. 
- **Time/Fine Algorithms**: Calculates day differentials between due-dates and current-dates upon book returns using native Java 8+ `java.time` methods. Accumulates an automatic fine penalty of $2 per overdue day.

## How To Run

1. Open a terminal.
2. Compile:
```bash
javac LibraryApp.java
```
3. Run:
```bash
java LibraryApp
```
Follow the interactive CLI. There are pre-seeded books to begin issuing and returning immediately.
