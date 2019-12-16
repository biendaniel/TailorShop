USE tailor_shop;

CREATE TABLE clothes_style
(
    id                   int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name                 varchar(32)     NOT NULL,
    price_ratio          float           NOT NULL,
    execution_time_ratio float           NOT NULL,
    main_image_id        int,
    FOREIGN KEY (main_image_id) REFERENCES Image (id)
)