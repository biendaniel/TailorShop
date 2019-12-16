USE tailor_shop;

CREATE TABLE order_status (
                                id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                name Varchar(64) NOT NULL,
                                change_date DATE
)