CREATE TABLE products
(
    id long AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name varchar(50),
    price float
);
CREATE UNIQUE INDEX products_id_uindex ON products (id);