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