create table users (
    id serial primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    status boolean default true
);