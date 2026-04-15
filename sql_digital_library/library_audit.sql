-- Create Tables
CREATE TABLE Books (
    BookID INT PRIMARY KEY,
    Title VARCHAR(100),
    Category VARCHAR(50),
    Author VARCHAR(100)
);

CREATE TABLE Students (
    StudentID INT PRIMARY KEY,
    Name VARCHAR(100),
    LastActiveDate DATE
);

CREATE TABLE IssuedBooks (
    IssueID INT PRIMARY KEY,
    BookID INT,
    StudentID INT,
    IssueDate DATE,
    ReturnDate DATE,
    FOREIGN KEY (BookID) REFERENCES Books(BookID),
    FOREIGN KEY (StudentID) REFERENCES Students(StudentID)
);

-- Insert Dummy Data
INSERT INTO Books (BookID, Title, Category, Author) VALUES
(1, 'The Great Gatsby', 'Fiction', 'F. Scott Fitzgerald'),
(2, 'A Brief History of Time', 'Science', 'Stephen Hawking'),
(3, '1984', 'Fiction', 'George Orwell'),
(4, 'Sapiens', 'History', 'Yuval Noah Harari'),
(5, 'Introduction to Algorithms', 'Computer Science', 'Thomas H. Cormen');

INSERT INTO Students (StudentID, Name, LastActiveDate) VALUES
(101, 'Alice Smith', '2023-10-01'),
(102, 'Bob Johnson', '2019-05-15'),  -- Inactive for > 3 years
(103, 'Charlie Brown', '2020-01-20'), -- Inactive for > 3 years
(104, 'Diana Prince', '2023-11-05');

INSERT INTO IssuedBooks (IssueID, BookID, StudentID, IssueDate, ReturnDate) VALUES
(1001, 1, 101, '2023-10-01', '2023-10-10'),
(1002, 2, 104, '2023-10-20', NULL),           -- Overdue (assuming current date is later)
(1003, 3, 101, '2023-11-01', NULL),           -- Not overdue if within 14 days of current
(1004, 1, 102, '2019-05-01', '2019-05-10'),
(1005, 5, 104, '2023-09-01', '2023-09-20');

-- 1. Query overdue books (>14 days, ReturnDate NULL)
-- Uses CURRENT_DATE (or GETDATE() for SQL Server, adapting for general SQL standard)
SELECT b.Title, s.Name, i.IssueDate, CURRENT_DATE - i.IssueDate AS DaysOverdue
FROM IssuedBooks i
JOIN Books b ON i.BookID = b.BookID
JOIN Students s ON i.StudentID = s.StudentID
WHERE i.ReturnDate IS NULL 
  AND i.IssueDate < CURRENT_DATE - INTERVAL '14 days';

-- 2. Find most popular category (GROUP BY)
SELECT b.Category, COUNT(i.IssueID) AS IssueCount
FROM Books b
JOIN IssuedBooks i ON b.BookID = i.BookID
GROUP BY b.Category
ORDER BY IssueCount DESC
LIMIT 1;

-- 3. Remove inactive students (>3 years)
-- First delete from IssuedBooks matching those students due to foreign key constraints
DELETE FROM IssuedBooks
WHERE StudentID IN (
    SELECT StudentID FROM Students 
    WHERE LastActiveDate < CURRENT_DATE - INTERVAL '3 years'
);

DELETE FROM Students 
WHERE LastActiveDate < CURRENT_DATE - INTERVAL '3 years';
