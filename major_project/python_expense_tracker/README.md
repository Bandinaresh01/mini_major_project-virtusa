# Smart Expense Tracker

A Python-based CLI application that allows users to record, review, and visually analyze their personal expenses.

## Features
- **CSV Storage**: Securely reads and writes expense entries using standard Python file I/O to a `expenses.csv` file.
- **Categorization**: Groups spending logically by categories.
- **Summary Intelligence**: Computes the sum of all spending and detects the most expensive category.
- **Data Visualization**: Leverages `matplotlib` to generate and save a pie chart (`expense_chart.png`) representing categorical breakdowns.

## Dependencies
This project requires `matplotlib`.
```bash
pip install matplotlib
```

## Usage
Run the script interactively:
```bash
python expense_tracker.py
```
Follow the interactive CLI menu to add dummy data. Afterward, select **Generate Summary & Chart** to see the insights and generate your `.png` visualization chart!
