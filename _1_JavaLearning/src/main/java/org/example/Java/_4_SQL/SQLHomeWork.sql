CREATE SCHEMA IF NOT EXISTS EstablishmentHomeWork;
USE EstablishmentHomeWork;
CREATE TABLE IF NOT EXISTS Establishment
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name     VARCHAR(100)                      NOT NULL,
    is_chain BOOLEAN default false,
    chain_id BIGINT,
    FOREIGN KEY (chain_id)
        REFERENCES Establishment (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS Review
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    establishment_id BIGINT                            NOT NULL,
    FOREIGN KEY (establishment_id)
        REFERENCES Establishment (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS Rate
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    rate      DOUBLE,
    type      VARCHAR(100)                      NOT NULL,
    review_id BIGINT                            NOT NULL,
    FOREIGN KEY (review_id)
        REFERENCES Review (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);


INSERT
INTO Establishment(name, is_chain, chain_id)
VALUES ('Starbucks', true, null),
       ('Starbucks New York1', false, 1),
       ('Starbucks Philadelphia', false, 1),
       ('Starbucks New York2', false, 1),
       ('Starbucks Los Angeles1', false, 1),
       ('Starbucks Los Angeles2', false, 1),
       ('Masa', false, null),
       ('Beard Papas', false, null),
       ('Joanne', false, null),
       ('Brooklyn Fare', false, null),
       ('KFC', true, null),
       ('KFC Los Angeles', false, 11),
       ('KFC New York', false, 11),
       ('KFC Philadelphia', false, 11),
       ('McDonald''s', true, null),
       ('McDonald''s Philadelphia', false, 15),
       ('McDonald''s Los Angeles', false, 15),
       ('McDonald''s New York', false, 15);
INSERT INTO Review(establishment_id)
VALUES (2),
       (2),
       (3),
       (3),
       (4),
       (5),
       (7),
       (8),
       (8),
       (8),
       (9),
       (10),
       (12),
       (13),
       (14),
       (16),
       (16),
       (16),
       (17),
       (17),
       (18),
       (18),
       (18),
       (18);
INSERT INTO Rate(rate,type,review_id)
VALUES (9.2,'FOOD',1),
       (9.1,'SERVICE',1),
       (7.1,'PRICES',1),
       (10,'INTERIOR',1),
       (7.8,'AMBIENCE',1),
       (3.9,'FOOD',2),
       (5.1,'SERVICE',2),
       (3.5,'PRICES',2),
       (4.9,'INTERIOR',2),
       (6.9,'AMBIENCE',2),

       (7,'FOOD',3),
       (8.5,'SERVICE',3),
       (5.4,'PRICES',3),
       (6.2,'INTERIOR',3),
       (5.4,'AMBIENCE',3),
       (7.6,'FOOD',4),
       (6.1,'SERVICE',4),
       (4.3,'PRICES',4),
       (7.8,'INTERIOR',4),
       (4.6,'AMBIENCE',4),

       (9.6,'FOOD',5),
       (9,'SERVICE',5),
       (7.4,'PRICES',5),
       (8.7,'INTERIOR',5),
       (8.3,'AMBIENCE',5),

       (0,'FOOD',6),
       (0,'SERVICE',6),
       (0,'PRICES',6),
       (0,'INTERIOR',6),
       (10,'AMBIENCE',6),

       (2,'FOOD',7),
       (9.1,'SERVICE',7),
       (8.8,'PRICES',7),
       (9,'INTERIOR',7),
       (8.9,'AMBIENCE',7),

       (9.1,'FOOD',8),
       (9.1,'SERVICE',8),
       (7.3,'PRICES',8),
       (9,'INTERIOR',8),
       (8.1,'AMBIENCE',8),
       (8.2,'FOOD',9),
       (8.1,'SERVICE',9),
       (7.5,'PRICES',9),
       (5.7,'INTERIOR',9),
       (8.4,'AMBIENCE',9),
       (4.9,'FOOD',10),
       (5.2,'SERVICE',10),
       (0,'PRICES',10),
       (8.2,'INTERIOR',10),
       (3.3,'AMBIENCE',10),

       (6.6,'FOOD',11),
       (5.1,'SERVICE',11),
       (5.3,'PRICES',11),
       (5.8,'INTERIOR',11),
       (6.4,'AMBIENCE',11),

       (9.4,'FOOD',12),
       (1.6,'SERVICE',12),
       (2.4,'PRICES',12),
       (2.3,'INTERIOR',12),
       (1.7,'AMBIENCE',12),

       (8.7,'FOOD',13),
       (8.4,'SERVICE',13),
       (7.1,'PRICES',13),
       (7.8,'INTERIOR',13),
       (6.8,'AMBIENCE',13),

       (5.3,'FOOD',14),
       (5.3,'SERVICE',14),
       (3.9,'PRICES',14),
       (7.4,'INTERIOR',14),
       (6.2,'AMBIENCE',14),

       (6.1,'FOOD',15),
       (5.6,'SERVICE',15),
       (6.3,'PRICES',15),
       (4,'INTERIOR',15),
       (5.3,'AMBIENCE',15),

       (10,'FOOD',16),
       (6,'SERVICE',16),
       (6.8,'PRICES',16),
       (9.7,'INTERIOR',16),
       (5,'AMBIENCE',16),
       (9.1,'FOOD',17),
       (5.1,'SERVICE',17),
       (8.2,'PRICES',17),
       (10,'INTERIOR',17),
       (8.2,'AMBIENCE',17),
       (8.8,'FOOD',18),
       (8.3,'SERVICE',18),
       (7.7,'PRICES',18),
       (7.8,'INTERIOR',18),
       (6.5,'AMBIENCE',18),

       (10,'FOOD',19),
       (10,'SERVICE',19),
       (10,'PRICES',19),
       (10,'INTERIOR',19),
       (10,'AMBIENCE',19),
       (9.4,'FOOD',20),
       (10,'SERVICE',20),
       (6.3,'PRICES',20),
       (7.8,'INTERIOR',20),
       (9.3,'AMBIENCE',20),

       (6.9,'FOOD',21),
       (6.2,'SERVICE',21),
       (4.2,'PRICES',21),
       (4.8,'INTERIOR',21),
       (9,'AMBIENCE',21),
       (0,'FOOD',22),
       (0,'SERVICE',22),
       (0,'PRICES',22),
       (0,'INTERIOR',22),
       (0,'AMBIENCE',22),
       (9.3,'FOOD',23),
       (7.9,'SERVICE',23),
       (6.6,'PRICES',23),
       (6.9,'INTERIOR',23),
       (6.4,'AMBIENCE',23),
       (4.4,'FOOD',24),
       (4.9,'SERVICE',24),
       (7.1,'PRICES',24),
       (3.4,'INTERIOR',24),
       (3.5,'AMBIENCE',24);
