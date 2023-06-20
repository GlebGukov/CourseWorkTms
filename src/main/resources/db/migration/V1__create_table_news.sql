CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";
create table news
(
    id           UUID primary key,
    anons        varchar,
    archived     boolean default false,
    full_text    varchar(1000),
    title        varchar(1000),
    views        BIGINT,
    type_of_news varchar,
    approved     boolean default false
);
