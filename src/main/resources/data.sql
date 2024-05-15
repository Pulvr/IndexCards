DROP TABLE IF EXISTS Student; -- Optional. May be useful if the H2 database is permanently stored on the hard disk and not in memory.
CREATE TABLE IF NOT EXISTS Student (
                                       id LONG PRIMARY KEY AUTO_INCREMENT,
                                       name VARCHAR(100) NOT NULL,
                                       semester INT NOT NULL
);
INSERT INTO Student(name, semester)
VALUES ('Elon', 1),
       ('Bill', 2),
       ('Sara', 3),
       ('Kira', 2);