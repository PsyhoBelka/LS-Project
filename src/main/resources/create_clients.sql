create table CLIENTS
(
  ID          BIGINT auto_increment,
  NAME        VARCHAR(30),
  SURNAME     VARCHAR(30),
  AGE         INTEGER,
  PHONENUMBER VARCHAR(10),
  EMAIL       VARCHAR
);

create unique index CLIENTS_ID_UINDEX
  on CLIENTS (ID);

create unique index PRIMARY_KEY
  on CLIENTS (ID);

alter table CLIENTS
  add constraint CLIENTS_PK
    primary key (ID);

