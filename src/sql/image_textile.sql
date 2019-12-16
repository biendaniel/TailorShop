USE tailor_shop;

CREATE TABLE image_textile
(
    textile_id int not null,
    image_id   int not null,
    FOREIGN KEY (textile_id) REFERENCES textile (id),
    FOREIGN KEY (image_id) REFERENCES Image (id)
)