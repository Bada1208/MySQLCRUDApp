create database MySQlCRUDApp;
use MySQlCRUDApp;
drop database MySQlCRUDApp;
create table accounts
(
    id            bigint  PRIMARY KEY ,
    account_status enum ('ACTIVE', 'BANNED', 'DELETED') DEFAULT 'ACTIVE'
);
insert into accounts values (1,'ACTIVE'),(2,'BANNED'),(3,'DELETED');
TRUNCATE accounts;
drop table accounts;
SELECT * FROM accounts;