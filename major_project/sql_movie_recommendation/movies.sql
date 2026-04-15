-- Schemas Creation
CREATE TABLE Users (
    UserID INT PRIMARY KEY,
    Username VARCHAR(100),
    Age INT
);

CREATE TABLE Movies (
    MovieID INT PRIMARY KEY,
    Title VARCHAR(150),
    Genre VARCHAR(50),
    ReleaseYear INT
);

CREATE TABLE Watch_History (
    HistoryID INT PRIMARY KEY,
    UserID INT,
    MovieID INT,
    WatchDate DATE,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (MovieID) REFERENCES Movies(MovieID)
);

CREATE TABLE Ratings (
    RatingID INT PRIMARY KEY,
    UserID INT,
    MovieID INT,
    Score INT CHECK (Score BETWEEN 1 AND 5),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (MovieID) REFERENCES Movies(MovieID)
);

-- Seeding Data
INSERT INTO Users (UserID, Username, Age) VALUES
(1, 'movieBuff99', 24),
(2, 'cinema_lover', 30),
(3, 'casual_watcher', 45);

INSERT INTO Movies (MovieID, Title, Genre, ReleaseYear) VALUES
(101, 'Inception', 'Sci-Fi', 2010),
(102, 'The Dark Knight', 'Action', 2008),
(103, 'A Beautiful Mind', 'Drama', 2001),
(104, 'Interstellar', 'Sci-Fi', 2014),
(105, 'Toy Story', 'Animation', 1995);

INSERT INTO Watch_History (HistoryID, UserID, MovieID, WatchDate) VALUES
(501, 1, 101, '2023-11-01'),
(502, 1, 104, '2023-11-02'),
(503, 2, 102, '2023-11-05'),
(504, 3, 103, '2023-11-06'),
(505, 2, 101, '2023-11-07');

INSERT INTO Ratings (RatingID, UserID, MovieID, Score) VALUES
(701, 1, 101, 5),
(702, 1, 104, 5),
(703, 2, 102, 4),
(704, 3, 103, 3),
(705, 2, 101, 4);

-- Analytics Queries

-- 1. Top-rated movies (Average score generally > 4)
SELECT m.Title, AVG(r.Score) AS AvgScore
FROM Movies m
JOIN Ratings r ON m.MovieID = r.MovieID
GROUP BY m.Title
ORDER BY AvgScore DESC;

-- 2. Popular genres (Based on Watch History volume)
SELECT m.Genre, COUNT(w.HistoryID) AS Views
FROM Movies m
JOIN Watch_History w ON m.MovieID = w.MovieID
GROUP BY m.Genre
ORDER BY Views DESC;

-- 3. Trending movies (Most watched overall)
SELECT m.Title, COUNT(w.HistoryID) AS TimesWatched
FROM Movies m
JOIN Watch_History w ON m.MovieID = w.MovieID
GROUP BY m.Title
ORDER BY TimesWatched DESC;

-- 4. Behavior analysis (Which user watched the most movies)
SELECT u.Username, COUNT(w.HistoryID) AS MoviesWatched
FROM Users u
JOIN Watch_History w ON u.UserID = w.UserID
GROUP BY u.Username
ORDER BY MoviesWatched DESC;

-- 5. User-based recommendation 
-- Suggestion to UserID '3' based on the highest rated movie across the entire platform
-- which they haven't seen yet.
SELECT m.Title, AVG(r.Score) as GlobalRating
FROM Movies m
JOIN Ratings r ON m.MovieID = r.MovieID
WHERE m.MovieID NOT IN (
    SELECT MovieID FROM Watch_History WHERE UserID = 3
)
GROUP BY m.Title
ORDER BY GlobalRating DESC
LIMIT 1;
