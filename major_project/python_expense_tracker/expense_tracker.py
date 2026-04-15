import csv
import os
from collections import defaultdict
import matplotlib.pyplot as plt

FILE_NAME = "expenses.csv"

def init_file():
    if not os.path.exists(FILE_NAME):
        with open(FILE_NAME, mode='w', newline='') as file:
            writer = csv.writer(file)
            writer.writerow(["Date", "Category", "Amount", "Description"])

def add_expense(date, category, amount, description):
    try:
        amount = float(amount)
        with open(FILE_NAME, mode='a', newline='') as file:
            writer = csv.writer(file)
            writer.writerow([date, category, amount, description])
        print("Expense added successfully!")
    except ValueError:
        print("Invalid amount. Please enter a number.")

def view_expenses():
    if not os.path.exists(FILE_NAME):
        print("No expenses recorded.")
        return

    print("\n--- Expense Records ---")
    with open(FILE_NAME, mode='r') as file:
        reader = csv.reader(file)
        for row in reader:
            print(f"{row[0]:<12} | {row[1]:<15} | ${row[2]:<10} | {row[3]}")
    print("-----------------------\n")

def generate_summary():
    if not os.path.exists(FILE_NAME):
        print("No expenses recorded.")
        return

    category_totals = defaultdict(float)
    total_spent = 0.0

    with open(FILE_NAME, mode='r') as file:
        reader = csv.DictReader(file)
        for row in reader:
            amount = float(row["Amount"])
            category_totals[row["Category"]] += amount
            total_spent += amount

    if total_spent == 0:
        print("Total spending is $0. No summary to generate.")
        return

    print(f"\n--- Monthly Summary ---")
    print(f"Total Spent: ${total_spent:.2f}")
    
    highest_category = max(category_totals, key=category_totals.get)
    print(f"Highest Spending Category: {highest_category} (${category_totals[highest_category]:.2f})\n")

    # Generate pie chart
    categories = list(category_totals.keys())
    amounts = list(category_totals.values())

    plt.figure(figsize=(8, 6))
    plt.pie(amounts, labels=categories, autopct='%1.1f%%', startangle=140)
    plt.title('Expense Breakdown by Category')
    
    # Save the chart so it doesn't block the UI
    chart_filename = 'expense_chart.png'
    plt.savefig(chart_filename)
    plt.close()
    
    print(f"Expense chart successfully saved as '{chart_filename}'.")

if __name__ == "__main__":
    init_file()
    print("Welcome to Smart Expense Tracker")
    while True:
        print("\n1. Add Expense")
        print("2. View Expenses")
        print("3. Generate Summary & Chart")
        print("4. Exit")
        choice = input("Enter choice: ")

        if choice == '1':
            date = input("Enter Date (YYYY-MM-DD): ")
            category = input("Enter Category (e.g., Food, Travel, Utilities): ")
            amount = input("Enter Amount: ")
            desc = input("Enter Description: ")
            add_expense(date, category, amount, desc)
        elif choice == '2':
            view_expenses()
        elif choice == '3':
            generate_summary()
        elif choice == '4':
            print("Exiting...")
            break
        else:
            print("Invalid choice.")
