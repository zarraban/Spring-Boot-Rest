CREATE TABLE IF NOT EXISTS contacts(
        id bigint GENERATED ALWAYS AS IDENTITY,
        first_name varchar(32),
        last_name varchar(32)
);