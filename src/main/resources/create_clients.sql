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