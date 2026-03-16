DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       birth_date DATE NOT NULL,
                       description TEXT
);

ALTER TABLE users ALTER COLUMN description SET COMPRESSION lz4;


-- big index not fit in shared mem
-- CREATE INDEX idx_users_birth_desc ON users(birth_date, name);