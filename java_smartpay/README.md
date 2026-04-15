# SmartPay Utility Billing

A Java console application that calculates customer utility bills using a stepped (slab-based) pricing system.

## Features
- **Interface Driven**: Uses a `Billable` interface to standardise billing implementations.
- **Slab-Based Logic**:
  - `0 - 100` units at **$1.50** / unit
  - `101 - 300` units at **$2.50** / unit
  - `> 300` units at **$4.00** / unit
- **Input Validation**: Rejects negative units and non-numerical inputs gracefully.

## Setup and Execution
1. Compile the script:
   ```bash
   javac SmartPay.java
   ```
2. Run the application:
   ```bash
   java SmartPay
   ```

## Example Output
```
Welcome to SmartPay Utility Billing
Enter Customer Name: Alice
Enter Units Consumed: 350

=================================
        SMARTPAY RECEIPT
=================================
Customer Name  : Alice
Units Consumed : 350.00
---------------------------------
TOTAL AMOUNT   : $850.00
=================================
```
