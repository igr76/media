CREATE TABLE posts
(
    id  integer generated always as identity primary key,
    title TEXT,
    content TEXT,
    data TIMESTAMP,
    name TEXT,
    image BYTEA,
    constraint fk_name foreign key (name) references users (id)
);