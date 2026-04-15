-- Create Tables
CREATE TABLE Categories (
    CategoryID INT PRIMARY KEY,
    CategoryName VARCHAR(100)
);

CREATE TABLE Products (
    ProductID INT PRIMARY KEY,
    ProductName VARCHAR(100),
    CategoryID INT,
    ExpiryDate DATE,
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

CREATE TABLE SalesTransactions (
    TransactionID INT PRIMARY KEY,
    ProductID INT,
    Quantity INT,
    SalePrice DECIMAL(10, 2),
    SaleDate DATE,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Insert Dummy Data
INSERT INTO Categories (CategoryID, CategoryName) VALUES
(1, 'Groceries'),
(2, 'Electronics'),
(3, 'Clothing');

INSERT INTO Products (ProductID, ProductName, CategoryID, ExpiryDate) VALUES
(101, 'Milk 1L', 1, CURRENT_DATE + INTERVAL '5 days'),  -- Expiring soon
(102, 'Bread', 1, CURRENT_DATE - INTERVAL '1 days'),   -- Already expired
(103, 'Laptop', 2, NULL),
(104, 'T-Shirt', 3, NULL),
(105, 'Smartphone', 2, NULL),
(106, 'Yogurt', 1, CURRENT_DATE + INTERVAL '10 days');

-- Dummy Data: Current Date assumption is the day you run this script.
INSERT INTO SalesTransactions (TransactionID, ProductID, Quantity, SalePrice, SaleDate) VALUES
(1001, 101, 2, 2.50, CURRENT_DATE - INTERVAL '2 days'),
(1002, 103, 1, 899.99, CURRENT_DATE - INTERVAL '70 days'),
(1003, 104, 5, 19.99, CURRENT_DATE - INTERVAL '10 days'),
(1004, 101, 1, 2.50, CURRENT_DATE - INTERVAL '15 days'),
(1005, 102, 3, 1.50, CURRENT_DATE - INTERVAL '40 days');

-- Note: Product 105 (Smartphone) and 106 (Yogurt) have NO sales.
-- Product 103 (Laptop) has no sales in the last 60 days.

-- 1. Expiring products query (Expiring within the next 7 days, or already expired)
SELECT p.ProductName, c.CategoryName, p.ExpiryDate, 
       (p.ExpiryDate - CURRENT_DATE) AS DaysToExpiry
FROM Products p
JOIN Categories c ON p.CategoryID = c.CategoryID
WHERE p.ExpiryDate IS NOT NULL 
  AND p.ExpiryDate <= CURRENT_DATE + INTERVAL '7 days'
ORDER BY p.ExpiryDate ASC;

-- 2. Dead stock detection (no sales in the last 60 days)
-- We check products that do NOT exist in the SalesTransactions table within the past 60 days
SELECT p.ProductID, p.ProductName
FROM Products p
WHERE p.ProductID NOT IN (
    SELECT ProductID 
    FROM SalesTransactions 
    WHERE SaleDate >= CURRENT_DATE - INTERVAL '60 days'
);

-- 3. Revenue per category
SELECT c.CategoryName, SUM(s.Quantity * s.SalePrice) AS TotalRevenue
FROM Categories c
JOIN Products p ON c.CategoryID = p.CategoryID
JOIN SalesTransactions s ON p.ProductID = s.ProductID
GROUP BY c.CategoryName
ORDER BY TotalRevenue DESC;
