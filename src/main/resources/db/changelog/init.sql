--changeset author:kkochel init DB

CREATE SCHEMA  WAREHOUSE ;

--changeset author:kkochel add table products
create table WAREHOUSE.PRODUCTS
(
    id  bigint       not null primary key,
    name varchar(250)  not null,
    description varchar(2500),
    enabled  boolean not null
);

