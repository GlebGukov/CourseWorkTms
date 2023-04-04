create table news (
    id BIGINT generated ALWAYS AS IDENTITY primary key,
    anons varchar,
    archived boolean,
    full_text varchar(1000),
    title varchar(1000),
    views BIGINT,
    type_of_news varchar
);
