# SQL Retail Insights

This project demonstrates simple SQL analytics to uncover key business metrics for a retail store.

## Database Schema
- **Categories**: Product category grouping.
- **Products**: Information about store inventory including expiration dates.
- **SalesTransactions**: Log of all items sold, including quantities and prices.

## Queries Implemented
1. **Expiring Products**: Identifies products that are already expired or are going to expire within the next 7 days.
2. **Dead Stock Detection**: Highlights products that have not generated a single sale within the last 60 days.
3. **Revenue Per Category**: Groups sales by product category and sums up the total revenue generated.

## Usage
Simply load the `retail_insights.sql` script into your database (PostgreSQL is recommended for standard date syntax) to create tables, load sample dummy data, and execute the analytical queries.
