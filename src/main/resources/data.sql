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
VALUES  ('elondestroyer','Elon', 'isNotAGenius', 'justHadMoneyAtTheRightTime@bla', 'password123'),
        ('LisanAlGaib','Paul', 'Atreides', 'spice@stuff.com', 'brummsibumm'),
        ('Amelia','Vicar', 'Amelia', 'GoatDeer@Yharnham.com', 'screaming'),
        ('john','fu', 'fara', 'john@doe.com', 'mypw');


INSERT INTO DECK(NAME, USER_ID)
VALUES ( 'Japanese' , 1 ),( 'English' , 1 ),('Spanish',1),('screaming',2),('testing',3),('testingSomeMOre',4);

INSERT INTO CARDS(FRONT, BACK, DECK_ID)
VALUES ( 'konnichiwa','hallo', 1 ),( 'Nani??','Was??', 1 ),( 'kuso','verdammt', 1 ),('hello','hallo',2),('house','Haus',2),('arbitrary','willkürlich',2),('juice','Saft',2),('mouse','Maus',2),('cat','Katze',2),('dog','Hund',2),('wife','Ehefrau',2),('husband','Ehemann',2),('flour','Mehl',2),('sugar','Zucker',2),('shark','Hai',2),('flower','Blume',2),('bread','Brot',2),('Hola' , 'Hallo ' , 3),('Si','Ja',3)