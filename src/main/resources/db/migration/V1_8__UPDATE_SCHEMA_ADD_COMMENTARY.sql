DROP TABLE IF EXISTS commentary;

CREATE TABLE commentary (
    id SERIAL PRIMARY KEY,
    text TEXT,
    rating INTEGER DEFAULT 0,
    created_at TIMESTAMP,
    msa_user_id INTEGER REFERENCES msa_user(id),
    movie_id INTEGER REFERENCES movie(id)
);