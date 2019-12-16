USE tailor_shop;

CREATE TABLE textile_type(
                               id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
                               name varchar(32) NOT NULL,
                               is_natural bit not null
)