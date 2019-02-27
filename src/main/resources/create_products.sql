create table PRODUCTS
(
  ID    BIGINT auto_increment,
  NAME  VARCHAR(100),
  PRICE DOUBLE
);

create unique index PRODUCTS_ID_UINDEX
  on PRODUCTS (ID);

create unique index PRIMARY_KEY
  on PRODUCTS (ID);

alter table PRODUCTS
  add constraint PRODUCTS_PK
    primary key (ID);