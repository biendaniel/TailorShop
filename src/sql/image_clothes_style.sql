USE tailor_shop;

CREATE TABLE image_clothes_style
(
    clothes_style_id int not null,
    image_id         int not null,
    FOREIGN KEY (clothes_style_id) REFERENCES clothes_style (id),
    FOREIGN KEY (image_id) REFERENCES Image (id)
)