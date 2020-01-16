USE tailor_shop;

CREATE TABLE product(
                             id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
                             price int NOT NULL,
                             comments Varchar(256),
                             execution_time int not null,
                             clothes_style_id int,
                             textile_id int,
                             dimensions_id int,
                             employee_id int,
                             product_type int,
#                              order_id int,
                             main_image_id int,
                             product_status_id int,
                             FOREIGN KEY (clothes_style_id) REFERENCES clothes_style(id),
                             FOREIGN KEY (textile_id) REFERENCES Textile(id),
                             FOREIGN KEY (dimensions_id) REFERENCES dimensions(id),
                             FOREIGN KEY (employee_id) REFERENCES employee(id),
                             FOREIGN KEY (product_type) REFERENCES product_type(id),
#                              FOREIGN KEY (order_id) REFERENCES `order`(id),
                             FOREIGN KEY (main_image_id) REFERENCES image(id),
                             FOREIGN KEY (product_status_id) REFERENCES product_status(id)
)