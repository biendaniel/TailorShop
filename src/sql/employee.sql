USE tailor_shop;

CREATE TABLE employee (
                               id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
                               is_manager BIT NOT NULL,
                               employee_status INT NOT NULL,
                               user_id INT NOT NULL UNIQUE,
                               FOREIGN KEY (employee_status) REFERENCES employee_status(id),
                               FOREIGN KEY (user_id) REFERENCES user(id)
)

