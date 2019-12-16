USE tailor_shop;

CREATE TABLE product_type_textile
(
    textile_id      int not null,
    product_type_id int not null,
    FOREIGN KEY (textile_id) REFERENCES Textile (id),
    FOREIGN KEY (product_type_id) REFERENCES Product_type (id)
)