

ALTER TABLE film
MODIFY COLUMN title VARCHAR(255);


ALTER TABLE film
MODIFY COLUMN special_features SET('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes');

ALTER TABLE film
MODIFY COLUMN replacement_cost DECIMAL(38,2);

ALTER TABLE film
MODIFY COLUMN rental_rate DECIMAL(4,2);

ALTER TABLE film
MODIFY COLUMN rating ENUM('G', 'PG', 'PG-13', 'R', 'NC-17');

ALTER TABLE film
MODIFY COLUMN last_update DATETIME(6);

ALTER TABLE film
MODIFY COLUMN description VARCHAR(255);

ALTER TABLE actor
MODIFY COLUMN last_update DATETIME(6);

ALTER TABLE actor
MODIFY COLUMN first_name VARCHAR(255);

ALTER TABLE film
MODIFY COLUMN rental_duration SMALLINT;

-- Identify the referenced table in the foreign key constraint 'fk_film_language'
-- Let's assume it's the 'language' table

-- Modify the 'language_id' column in the 'language' table
ALTER TABLE language
   MODIFY COLUMN language_id SMALLINT;

-- Now modify the 'language_id' column in the 'film' table
ALTER TABLE film
   MODIFY COLUMN language_id SMALLINT;

-- Do the same for 'original_language_id' if necessary


SELECT film.*
FROM film
LEFT JOIN language ON film.language_id = language.language_id
WHERE language.language_id IS NULL;