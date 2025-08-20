-- Sequence for user_id
CREATE SEQUENCE user_id_seq START WITH 1 INCREMENT BY 1;

-- Main user table
CREATE TABLE users (
    user_id BIGINT PRIMARY KEY DEFAULT nextval('user_id_seq'),
    email VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    birthday TIMESTAMP WITH TIME ZONE,
    weight NUMERIC,
    height NUMERIC,
    CONSTRAINT email_format CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);

-- Sequence for interest_id
CREATE SEQUENCE interest_id_seq START WITH 1 INCREMENT BY 1;

-- Interest table
CREATE TABLE interests (
    interest_id BIGINT PRIMARY KEY DEFAULT nextval('interest_id_seq'),
    user_id BIGINT NOT NULL,
    interest_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
