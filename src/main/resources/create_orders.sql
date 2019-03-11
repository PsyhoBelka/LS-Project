create table orders
(
  id        bigint auto_increment,
  status    varchar(20),
  client_id bigint not null,
  primary key (id),
  foreign key (client_id) references clients (id)
  on DELETE SET null
  on UPDATE CASCADE
);