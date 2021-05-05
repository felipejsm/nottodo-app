SET MODE MYSQL;

DROP TABLE IF EXISTS nottodo;
DROP TABLE IF EXISTS user;


CREATE TABLE nottodo(
                        id INT NOT NULL AUTO_INCREMENT,
                        item_name VARCHAR,
                        description VARCHAR,
                        date VARCHAR,
                        enabled bool,
                        update_date VARCHAR
);
CREATE TABLE user(
                     id INT NOT NULL AUTO_INCREMENT,
                     name VARCHAR,
                     email VARCHAR,
                     nick VARCHAR,
                     enabled BOOL,
                     nottodo_id INT,
                     CONSTRAINT FK_USER_NOTTODO FOREIGN KEY (nottodo_id) REFERENCES nottodo(id)
);