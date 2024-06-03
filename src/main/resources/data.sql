DROP TABLE IF EXISTS USERS;
CREATE TABLE IF NOT EXISTS USERS
(
    ID          LONG PRIMARY KEY AUTO_INCREMENT,
    USERNAME    VARCHAR(100) NOT NULL UNIQUE ,
    FIRSTNAME   VARCHAR(100) NOT NULL,
    LASTNAME    VARCHAR(100) NOT NULL,
    EMAIL       VARCHAR(100) NOT NULL UNIQUE ,
    PASSWORD    VARCHAR(100) NOT NULL,
    CURRENTDECK INTEGER DEFAULT 0
);
DROP TABLE IF EXISTS DECK;
CREATE TABLE IF NOT EXISTS DECK
(
    ID          LONG PRIMARY KEY AUTO_INCREMENT,
    NAME        VARCHAR(100) NOT NULL,
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
        ('Amelia','Vicar', 'Amelia', 'GoatDeer@Yharnham.com', 'screaming'),
        ('john','fu', 'fara', 'john@doe.com', 'mypw');


INSERT INTO DECK(NAME, USER_ID)
VALUES ( 'Japanese' , 1 ),( 'English' , 1 ),('fremen',1),('screaming',2),('testing',3),('testingSomeMOre',4);

INSERT INTO CARDS(FRONT, BACK, DECK_ID)
VALUES ( 'Konnichiwa','Hallo', 1 ),( 'fara','fu', 1 ),('Hello','Hallo',2),('I dare you' , 'lol ' , 3)