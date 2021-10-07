CREATE TABLE msa_user (
    id SERIAL PRIMARY KEY,
    username VARCHAR(64) UNIQUE NOT NULL,
    password_hash CHAR(128) NOT NULL,
    email VARCHAR(64) UNIQUE NOT NULL,
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    middle_name VARCHAR(64),
    date_of_birth DATE,
    description TEXT
);