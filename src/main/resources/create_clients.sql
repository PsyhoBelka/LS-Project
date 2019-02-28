CREATE TABLE clients
(
    id long AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name varchar(30),
    surname varchar(30),
    age int,
    phone_number varchar(10),
    email varchar
);
CREATE UNIQUE INDEX clients_id_uindex ON clients (id);