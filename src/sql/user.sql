USE tailor_shop;

CREATE TABLE user (
                          id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
                          email varchar(255) NOT NULL UNIQUE,
                          firstname varchar(255) NOT NULL,
                          lastname varchar(255) NULL,
                          phone_number varchar(255) NOT NULL,
                          password varchar(255) NOT NULL
)

