create schema IF NOT EXISTS SHOP.PUBLIC;

create table clients
(
  id           bigint auto_increment,
  name         varchar(40),
  surname      varchar(40),
  age          int,
  phone_number varchar(10),
  email        varchar,
  primary key (id)
);

create table orders
(
  id        bigint auto_increment,
  status    varchar(20),
  client_id bigint not null,
  primary key (id),
  foreign key (client_id) references clients (id)
);

create table products
(
  id    bigint auto_increment,
  name  varchar(50),
  price float,
  primary key (id)
);

create table orders_products
(
  order_id   bigint not null,
  product_id bigint not null,
  primary key (order_id, product_id),
  foreign key (order_id) references orders (id)
  on UPDATE CASCADE
  on DELETE CASCADE,
  foreign key (product_id) references products (id)
  on UPDATE CASCADE
  on DELETE CASCADE
);