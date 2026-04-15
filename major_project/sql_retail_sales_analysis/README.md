# Online Retail Sales Analysis

An extensive database schema and analytics suite mimicking an online retail operation. 

## Structure
- `Customers`: Details on platform users.
- `Products`: Details, categories, and prices of inventory.
- `Orders`: The summary of a transactional cart.
- `Order_Items`: The distinct items and quantities attached to every specific `Order`.

## Analytics Metrics Provided
This package answers actual business intelligence questions:
1. **Top-Selling Products**: Identifies the highest moving inventory based on quantity.
2. **Most Valuable Customers**: Identifies VIP spenders by aggregating their total purchase values.
3. **Monthly Revenue**: Generates financial trend reports grouping transactions per calendar month.
4. **Category-wise Sales**: Uncovers which segment (e.g., Electronics vs Books) yields highest revenue.
5. **Inactive Customers**: A retention query finding customers falling off active radar (e.g.,>6 months).

## Setup
Simply load the `.sql` script into your database (logic built broadly around Postgres ANSI compliances) to instantiate the demo schemas and execute the reporting views.
