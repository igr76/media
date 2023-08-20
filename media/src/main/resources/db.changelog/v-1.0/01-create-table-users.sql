CREATE TABLE users
(
    id  serial PRIMARY KEY ,
    name TEXT,
    email TEXT,
    password TEXT,
    data TIMESTAMP,
    role TEXT
);