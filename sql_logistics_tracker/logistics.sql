-- Create Tables
CREATE TABLE Partners (
    PartnerID INT PRIMARY KEY,
    PartnerName VARCHAR(100),
    Rating DECIMAL(3, 2)
);

CREATE TABLE Shipments (
    ShipmentID INT PRIMARY KEY,
    DestinationCity VARCHAR(100),
    ExpectedDelivery DATE
);

CREATE TABLE DeliveryLogs (
    LogID INT PRIMARY KEY,
    ShipmentID INT,
    PartnerID INT,
    ActualDelivery DATE,
    FOREIGN KEY (ShipmentID) REFERENCES Shipments(ShipmentID),
    FOREIGN KEY (PartnerID) REFERENCES Partners(PartnerID)
);

-- Insert Dummy Data
INSERT INTO Partners (PartnerID, PartnerName, Rating) VALUES
(1, 'FastTrack Express', 4.8),
(2, 'Global Freight', 3.5),
(3, 'City Couriers', 4.2);

INSERT INTO Shipments (ShipmentID, DestinationCity, ExpectedDelivery) VALUES
(1001, 'New York', '2023-11-01'),
(1002, 'Los Angeles', '2023-11-02'),
(1003, 'Chicago', '2023-11-03'),
(1004, 'New York', '2023-11-04'),
(1005, 'Houston', '2023-11-05');

INSERT INTO DeliveryLogs (LogID, ShipmentID, PartnerID, ActualDelivery) VALUES
(501, 1001, 1, '2023-10-30'),  -- On time
(502, 1002, 2, '2023-11-04'),  -- Delayed
(503, 1003, 3, '2023-11-03'),  -- On time
(504, 1004, 1, '2023-11-05'),  -- Delayed
(505, 1005, 2, '2023-11-08');  -- Delayed

-- 1. Delayed shipment query
-- Items where ActualDelivery > ExpectedDelivery
SELECT s.ShipmentID, s.DestinationCity, s.ExpectedDelivery, d.ActualDelivery, 
       p.PartnerName
FROM Shipments s
JOIN DeliveryLogs d ON s.ShipmentID = d.ShipmentID
JOIN Partners p ON d.PartnerID = p.PartnerID
WHERE d.ActualDelivery > s.ExpectedDelivery;

-- 2. Partner performance ranking
-- Rank partners by the number of successful (on-time or early) deliveries.
SELECT p.PartnerName, COUNT(d.LogID) AS OnTimeDeliveries
FROM Partners p
JOIN DeliveryLogs d ON p.PartnerID = d.PartnerID
JOIN Shipments s ON d.ShipmentID = s.ShipmentID
WHERE d.ActualDelivery <= s.ExpectedDelivery
GROUP BY p.PartnerName
ORDER BY OnTimeDeliveries DESC, p.PartnerName ASC;

-- 3. Most frequent destination city
SELECT DestinationCity, COUNT(ShipmentID) AS ShipmentCount
FROM Shipments
GROUP BY DestinationCity
ORDER BY ShipmentCount DESC
LIMIT 1;
