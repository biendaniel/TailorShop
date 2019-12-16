USE tailor_shop;

CREATE TABLE image_product
(
    product_id int,
    image_id   int,
    FOREIGN KEY (product_id) REFERENCES Product (id),
    FOREIGN KEY (image_id) REFERENCES Image (id)
)