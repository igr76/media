CREATE TABLE post_images
(
    id  integer generated always as identity primary key ,
    post_id int,
    path TEXT,
    constraint fk_post_id foreign key (post_id) references posts (id)
    on delete cascade
);

