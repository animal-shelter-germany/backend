DROP SCHEMA tiere CASCADE;
CREATE SCHEMA tiere;

CREATE TABLE listing (
    id BIGINT,
    type VARCHAR(64),
    CONSTRAINT pk_listing PRIMARY KEY (id)
);

CREATE TABLE birthday (
    id BIGINT,
    year INTEGER,
    month INTEGER,
    day INTEGER,
    CONSTRAINT pk_birthday PRIMARY KEY (id)
);

CREATE TABLE animal (
    id BIGINT,
    animal_name VARCHAR(256),
    birthday_id BIGINT,
    listing_id BIGINT,
    CONSTRAINT pk_animal PRIMARY KEY (id),
    CONSTRAINT fk_listing FOREIGN KEY (listing_id) REFERENCES listing(id)
);

/*Demo Data*/
INSERT INTO listing(id, type)
VALUES (1, 'CAT'),
    (2, 'DOG');

INSERT INTO birthday(id, "year", "month", "day")
VALUES (1, 2020, 9, 3),
    (2, 2021, 2, 14),
    (3, 2022, 12, 26);

INSERT INTO animal(id, animal_name, birthday_id, listing_id)
VALUES (1, 'Süße Katze', 1, 1),
    (2, 'Graue Katze', 2, 2);