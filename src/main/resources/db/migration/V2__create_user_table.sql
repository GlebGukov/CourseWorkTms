CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
create table users
(
    id         UUID primary key,
    login      varchar(20) unique,
    password   varchar(100) not null,
    first_name varchar(30),
    last_name  varchar(30),
    email      varchar(40),
    status     boolean default 'true',
    role       varchar(10) default 'ROLE_USER'
);