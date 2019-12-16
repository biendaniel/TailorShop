USE tailor_shop;

CREATE TABLE customer (
                          id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
                          user_id INT NOT NULL UNIQUE,
                          FOREIGN KEY (user_id) REFERENCES user(id)
)

