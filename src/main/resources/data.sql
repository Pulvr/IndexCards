DROP TABLE IF EXISTS USERS;
CREATE TABLE IF NOT EXISTS USERS
(
    ID          LONG PRIMARY KEY AUTO_INCREMENT,
    USERNAME    VARCHAR(100) NOT NULL,
    FIRSTNAME   VARCHAR(100) NOT NULL,
    LASTNAME    VARCHAR(100) NOT NULL,
    EMAIL       VARCHAR(100) NOT NULL,
    PASSWORD    VARCHAR(100) NOT NULL
);
DROP TABLE IF EXISTS DECK;
CREATE TABLE IF NOT EXISTS DECK
(
    ID          LONG PRIMARY KEY AUTO_INCREMENT,
    DECKNAME    VARCHAR(100) NOT NULL,
    USER_ID     LONG NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
);

DROP TABLE IF EXISTS CARDS;
CREATE TABLE IF NOT EXISTS CARDS(
    ID          LONG PRIMARY KEY AUTO_INCREMENT,
    FRONT       VARCHAR(200) NOT NULL,
    BACK        VARCHAR(200) NOT NULL,
    DECK_ID     LONG NOT NULL,
    FOREIGN KEY (DECK_ID) REFERENCES DECK(ID)
);

INSERT INTO USERS(USERNAME, FIRSTNAME,LASTNAME,EMAIL,PASSWORD)
VALUES  ('elondestroyer','Elon', 'Mah', 'something@bla', 'password123'),
        ('LisanAlGaib','Paul', 'Atreides', 'spice@stuff.com', 'brummsibumm'),
        ('elondestroyer','Elon', 'Mah', 'something@bla', 'password123'),
        ('Amelia','Vicar', 'Amelia', 'GoatDeer@Yharnham.com', 'screaming');


INSERT INTO DECK(DECKNAME, USER_ID)
VALUES ( 'Japanese' , 1 ),( 'English' , 1 ),('fremen',2),('screaming',3),('testing',4);

INSERT INTO CARDS(FRONT, BACK, DECK_ID)
VALUES ( 'Konnichiwa','Hallo', 1 ),('Hello','Hallo',2)