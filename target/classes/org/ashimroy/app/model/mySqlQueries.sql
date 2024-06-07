

SELECT 
    TABLE_NAME,
    COLUMN_NAME,
    REFERENCED_TABLE_NAME,
    REFERENCED_COLUMN_NAME
FROM
    INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE
    CONSTRAINT_NAME = 'fk_film_language';

/*
ALTER TABLE film
ADD CONSTRAINT fk_film_language
FOREIGN KEY (language_id)
REFERENCES language(language_id);

/*
ALTER TABLE film
MODIFY COLUMN original_language_id SMALLINT;

ALTER TABLE language
MODIFY COLUMN language_id SMALLINT;

ALTER TABLE film
ADD CONSTRAINT fk_film_language_original
FOREIGN KEY (original_language_id)
REFERENCES language(language_id);


/*
SELECT 
    TABLE_NAME,
    CONSTRAINT_NAME
FROM
    INFORMATION_SCHEMA.TABLE_CONSTRAINTS
WHERE
    CONSTRAINT_NAME = 'fk_film_language_original';

/*
ALTER TABLE film
DROP FOREIGN KEY fk_film_language_original;

ALTER TABLE film
MODIFY COLUMN original_language_id SMALLINT;

ALTER TABLE film
MODIFY COLUMN language_id SMALLINT;

ALTER TABLE language
MODIFY COLUMN language_id SMALLINT;

SELECT 
    TABLE_NAME,
    CONSTRAINT_NAME
FROM
    INFORMATION_SCHEMA.TABLE_CONSTRAINTS
WHERE
    CONSTRAINT_NAME = 'fk_film_language';

ALTER TABLE film
DROP FOREIGN KEY fk_film_language;

ALTER TABLE film
MODIFY COLUMN language_id SMALLINT;

SELECT film.*
FROM film
LEFT JOIN language ON film.language_id = language.language_id
WHERE language.language_id IS NULL;


ALTER TABLE language
   MODIFY COLUMN language_id SMALLINT;

ALTER TABLE film
   MODIFY COLUMN language_id SMALLINT;


SELECT 
    TABLE_NAME,
    COLUMN_NAME,
    REFERENCED_TABLE_NAME,
    REFERENCED_COLUMN_NAME
FROM
    INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE
    CONSTRAINT_NAME = 'fk_film_language';
    

ALTER TABLE film
   MODIFY COLUMN language_id SMALLINT,
   MODIFY COLUMN original_language_id SMALLINT;
*/
