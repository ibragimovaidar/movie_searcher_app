DROP TABLE IF EXISTS movie_commentary;

CREATE TABLE movie_commentary (
    id SERIAL PRIMARY KEY,
    text TEXT,
    rating INTEGER DEFAULT 0,
    created_at TIMESTAMP,
    msa_user_id INTEGER REFERENCES msa_user(id),
    movie_id INTEGER REFERENCES movie(id)
);