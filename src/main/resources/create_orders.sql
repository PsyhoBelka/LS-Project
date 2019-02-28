CREATE TABLE orders
(
    id long AUTO_INCREMENT PRIMARY KEY NOT NULL,
    client_id long NOT NULL,
    order_status varchar(20)
);
CREATE UNIQUE INDEX orders_id_uindex ON orders (id);