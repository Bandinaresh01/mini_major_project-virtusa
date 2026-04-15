# FareCalc Travel Optimizer

A Python application designed to estimate ride-share fares based on distance, vehicle type, and current time (implementing surge pricing).

## Features
- **Flexible Pricing**: Prices are determined using a predefined dictionary based on vehicle category (Auto, Mini, Sedan, SUV).
- **Surge Pricing**: Automatically applies a 1.5x surge multiplier between 5 PM and 8 PM (17:00 to 20:00).
- **Validation**: Checks for invalid vehicle types and negative distances.
- **Detailed Receipt**: Prints a formatted summary of the trip cost.

## Setup and Execution
1. Make sure Python 3.x is installed.
2. Run the program directly from the terminal:
   ```bash
   python fare_calculator.py
   ```

## Output Example
```
Welcome to FareCalc Travel Optimizer
Enter distance in km: 12
Enter vehicle type (Auto/Mini/Sedan/SUV): Sedan
Enter hour of travel (0-23): 18

==============================
      FARECALC RECEIPT
==============================
Distance:     12.00 km
Vehicle:      Sedan
Time:         18:00
Note:         Surge Pricing Applied (1.5x)
------------------------------
TOTAL FARE:   ₹450.00
==============================
```
