# SQL Logistics Tracker

A SQL schema and analytical suite to evaluate shipment statuses, destinations, and logistics partner performance.

## Database Schema
- **Partners**: Companies responsible for deliveries.
- **Shipments**: Data on packages and their expected arrival dates.
- **DeliveryLogs**: Logs mapping actual completion dates of shipments to partners.

## Queries Implemented
1. **Delayed Shipments**: Highlights all packages that were delivered after their expected delivery date.
2. **Partner Performance Ranking**: Evaluates delivery partners based on their successful (on-time or early) deliveries and ranks them.
3. **Most Frequent Destination City**: Identifies the most common delivery destination city.

## Usage
Simply load the `logistics.sql` script into your database (PostgreSQL is recommended, but logic is ANSI compliant) to instantiate the dummy data and run the metric queries.
