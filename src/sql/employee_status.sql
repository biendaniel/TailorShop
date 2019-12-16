USE tailor_shop;

CREATE TABLE employee_status (
                              id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
                              current_status_name Varchar(64) NOT NULL,
                              current_status_start_date DATE NOT NULL,
                              next_status_name Varchar(64),
                              next_status_start_date DATE
)