USE tailor_shop;

CREATE TABLE `order`
(
    id             int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    total_price    float           NOT NULL,
    start_date     DATE            NOT NULL,
    execution_time int             NOT NULL,
    comments       Varchar(256),
    status_id      int             not null,
    customer_id    int             not null,
    FOREIGN KEY (status_id) REFERENCES order_status (id),
    FOREIGN KEY (customer_id) REFERENCES customer (id)

)