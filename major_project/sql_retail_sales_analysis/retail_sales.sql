-- Create Tables
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    Name VARCHAR(100),
    Email VARCHAR(100),
    LastOrderDate DATE
);

CREATE TABLE Products (
    ProductID INT PRIMARY KEY,
    ProductName VARCHAR(100),
    Category VARCHAR(50),
    Price DECIMAL(10, 2)
);

CREATE TABLE Orders (
    OrderID INT PRIMARY KEY,
    CustomerID INT,
    OrderDate DATE,
    TotalAmount DECIMAL(10, 2),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Order_Items (
    OrderItemID INT PRIMARY KEY,
    OrderID INT,
    ProductID INT,
    Quantity INT,
    SubTotal DECIMAL(10, 2),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Insert Dummy Data
INSERT INTO Customers (CustomerID, Name, Email, LastOrderDate) VALUES
(1, 'Alice Smith', 'alice@test.com', '2023-11-01'),
(2, 'Bob Johnson', 'bob@test.com', '2022-05-15'), -- Inactive
(3, 'Charlie Brown', 'charlie@test.com', '2023-10-25'),
(4, 'Diana Prince', 'diana@test.com', '2023-11-10');

INSERT INTO Products (ProductID, ProductName, Category, Price) VALUES
(101, 'MacBook Pro', 'Electronics', 1999.99),
(102, 'Coffee Mug', 'Home', 12.50),
(103, 'Office Chair', 'Furniture', 150.00),
(104, 'Python Crash Course', 'Books', 35.00),
(105, 'Wireless Mouse', 'Electronics', 25.00);

-- Note: Order amounts are derived from order items conceptually
INSERT INTO Orders (OrderID, CustomerID, OrderDate, TotalAmount) VALUES
(1001, 1, '2023-10-15', 2024.99),
(1002, 3, '2023-10-25', 150.00),
(1003, 1, '2023-11-01', 35.00),
(1004, 4, '2023-11-10', 49.00);

-- Orders for Alice (1001, 1003)
INSERT INTO Order_Items (OrderItemID, OrderID, ProductID, Quantity, SubTotal) VALUES
(501, 1001, 101, 1, 1999.99),
(502, 1001, 105, 1, 25.00),
(503, 1002, 103, 1, 150.00),
(504, 1003, 104, 1, 35.00),
(505, 1004, 102, 2, 25.00),
(506, 1004, 105, 1, 24.00);

-- 1. Top-selling products
SELECT p.ProductName, SUM(oi.Quantity) AS TotalSold
FROM Products p
JOIN Order_Items oi ON p.ProductID = oi.ProductID
GROUP BY p.ProductName
ORDER BY TotalSold DESC
LIMIT 5;

-- 2. Most valuable customers
SELECT c.Name, SUM(o.TotalAmount) AS TotalSpent
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
GROUP BY c.Name
ORDER BY TotalSpent DESC
LIMIT 3;

-- 3. Monthly revenue (Grouping order dates by month/year)
SELECT TO_CHAR(OrderDate, 'YYYY-MM') AS RevenueMonth, SUM(TotalAmount) AS Revenue
FROM Orders
GROUP BY TO_CHAR(OrderDate, 'YYYY-MM')
ORDER BY RevenueMonth DESC;
-- Note: TO_CHAR works in Postgres. For SQL Server use FORMAT(), for MySQL use DATE_FORMAT().

-- 4. Category-wise sales
SELECT p.Category, SUM(oi.SubTotal) AS CategoryRevenue
FROM Products p
JOIN Order_Items oi ON p.ProductID = oi.ProductID
GROUP BY p.Category
ORDER BY CategoryRevenue DESC;

-- 5. Inactive customers (No orders in the last 6 months)
SELECT c.CustomerID, c.Name, c.LastOrderDate
FROM Customers c
WHERE c.LastOrderDate < CURRENT_DATE - INTERVAL '6 months';
