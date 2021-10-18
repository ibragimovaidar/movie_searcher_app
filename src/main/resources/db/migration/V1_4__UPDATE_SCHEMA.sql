CREATE TABLE image_metadata (
    id SERIAL PRIMARY KEY,
    height INTEGER,
    weight INTEGER,
    filename TEXT
);

CREATE TYPE person_type AS ENUM ('Actor', 'Director', 'Screenwriter');

CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    middle_name VARCHAR(64),
    description TEXT,
    person_type person_type[],
    image_metadata_id INTEGER REFERENCES image_metadata(id)
);

CREATE TABLE movie_genre (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE movie (
                       id SERIAL PRIMARY KEY,
                       name TEXT NOT NULL,
                       date_of_release DATE,
                       country TEXT,
                       average_rating INTEGER,
                       description TEXT,
                       movie_genre_id INTEGER REFERENCES movie_genre(id),
                       image_metadata_id INTEGER REFERENCES image_metadata(id)
);

CREATE TABLE movies_participators (
        person_id INTEGER REFERENCES person(id),
        movie_id INTEGER REFERENCES movie(id)
);


ALTER TABLE msa_user ADD COLUMN image_metadata_id INTEGER REFERENCES image_metadata(id);


CREATE TABLE review (
    id SERIAL PRIMARY KEY,
    rating INTEGER,
    description TEXT,
    msa_user_id INTEGER REFERENCES msa_user(id),
    movie_id INTEGER REFERENCES movie(id),
    created_at TIMESTAMP
);


