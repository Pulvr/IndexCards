DROP TABLE IF EXISTS USERS;
CREATE TABLE IF NOT EXISTS USERS
(
    id          LONG PRIMARY KEY AUTO_INCREMENT,
    username    VARCHAR(100) NOT NULL,
    firstName   VARCHAR(100) NOT NULL,
    lastName    VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL,
    password    VARCHAR(100) NOT NULL
);
DROP TABLE IF EXISTS DECK;
CREATE TABLE IF NOT EXISTS DECK
(
    id      LONG PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(100) NOT NULL,
    user_id LONG NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS(id)
);

INSERT INTO USERS(username, firstName,lastName,email,password)
VALUES  ('elondestroyer','Elon', 'Mah', 'something@bla', 'password123'),
        ('LisanAlGaib','Paul', 'Atreides', 'spice@stuff.com', 'brummsibumm');

INSERT INTO DECK(name, user_id) VALUES ( 'Japanese' , 1 ),('fremen',2);
