create table ORDERS
(
  ID          BIGINT auto_increment,
  ORDERSTATUS VARCHAR
);

create unique index ORDERS_ID_UINDEX
  on ORDERS (ID);

create unique index PRIMARY_KEY
  on ORDERS (ID);

alter table ORDERS
  add constraint ORDERS_PK
    primary key (ID);

