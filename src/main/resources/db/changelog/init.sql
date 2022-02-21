--changeset author:kkochel init DB

CREATE SCHEMA WHOLESALER;

--changeset author:kkochel add table products
create table WHOLESALER.STOCKS
(
    id      bigint not null primary key,
    product bigint,
    version integer

);
create table WHOLESALER.PRODUCTS
(
    id       bigint       not null primary key,
    name     varchar(250) not null,
    number   varchar(250),
    version  integer
);

create table WHOLESALER.STOCK_ITEMS
(
    id       bigint not null primary key,
    stock_id bigint,
    uuid     UUID,
    created  timestamp,
    lot_id   varchar(250)
);

create sequence WHOLESALER.PRODUCTS_SEQ START WITH 1 INCREMENT BY 1;
create sequence WHOLESALER.STOCK_SEQ START WITH 1 INCREMENT BY 1;
create sequence WHOLESALER.STOCK_ITEM_SEQ START WITH 1 INCREMENT BY 1;
