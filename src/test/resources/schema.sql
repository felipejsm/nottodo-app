DROP TABLE IF EXISTS nottodo;
DROP TABLE IF EXISTS user;


CREATE TABLE nottodo(
    id IDENTITY,
    item_name VARCHAR,
    description VARCHAR,
    date VARCHAR,
    enabled bool
);

CREATE TABLE user(
    id IDENTITY ,
    name VARCHAR,
    email VARCHAR,
    nick VARCHAR,
    enabled BOOL
);