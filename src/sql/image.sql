USE tailor_shop;

CREATE TABLE image
(
    id               int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    img_url          varchar(128)    NOT NULL,
    alternative_text varchar(64)     NOT NULL
)