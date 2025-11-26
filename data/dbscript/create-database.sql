-- Set the default DB
USE master;
GO
IF EXISTS(SELECT * FROM sys.databases WHERE name = '<YOUR-DB-NAME>')
BEGIN
  DROP DATABASE <YOUR-DB-NAME>;
END

CREATE DATABASE <YOUR-DB-NAME>;
GO
USE <YOUR-DB-NAME>;
GO
CREATE TABLE Movie (
                       Id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
                       Title VARCHAR(255) NOT NULL,
                       Year INT NOT NULL,
);
CREATE TABLE [User] (
                        Id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    Name VARCHAR(255) NOT NULL
    );
CREATE TABLE Rating (
                        RatingId INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
                        MovieId INT NOT NULL FOREIGN KEY REFERENCES Movie(Id),
                        UserId INT NOT NULL FOREIGN KEY REFERENCES [User](Id),
                        Score INT NOT NULL
);

-- import data files or create mock data


