USE tailor_shop;

CREATE TABLE textile
(
    id                   int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name                 varchar(32)     NOT NULL,
    price_ratio          float           NOT NULL,
    execution_time_ratio float           NOT NULL,
    main_image_id        int             NOT NULL,
    textile_type_id      int             NOT NULL,
    FOREIGN KEY (main_image_id) REFERENCES Image (id),
    FOREIGN KEY (textile_type_id) REFERENCES textile_type (id)
)