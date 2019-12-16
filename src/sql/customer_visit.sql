USE tailor_shop;

CREATE TABLE visit
(
    id          int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    visit_date  date            not null,
    customer_id int,
    FOREIGN KEY (customer_id) REFERENCES Customer (id)
)