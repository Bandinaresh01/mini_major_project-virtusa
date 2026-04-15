# SQL Digital Library Audit

This project contains SQL scripts to manage and audit a digital library system.

## Setup
You can run this script in any SQL-compliant database like PostgreSQL, MySQL, or SQLite (with minor syntax adjustments if required). The provided syntax is ANSI SQL compliant where possible, using standard `INTERVAL` syntax for date calculations.

## Database Schema
- **Books**: Contains information about books available in the library.
- **Students**: Stores records of students.
- **IssuedBooks**: Tracks the checkout and return of books.

## Queries Implemented
1. **Overdue Books**: Queries books that have been checked out for more than 14 days and have not been returned yet.
2. **Most Popular Category**: Uses `GROUP BY` to determine which category of books is checked out the most.
3. **Remove Inactive Students**: Identifies and removes students who haven't had any activity in more than 3 years. Note: It deletes related checkout history to maintain referential integrity.

## Usage
Simply load the `library_audit.sql` script into your database client to create the tables, seed the mock data, and run the audit queries.
