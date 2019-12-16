USE tailor_shop;


CREATE TABLE dimensions (
                               id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
                               collar_size int NULL,
                               chest_size int NULL,
                               sleeve_length int NULL,
                               waist_size int NULL,
                               shoulder_width int NULL,
                               leg_length int NULL,
                               leg_width int NULL,
                               customer_id int NOT NULL,
                               FOREIGN KEY (customer_id) REFERENCES customer(id)
)


