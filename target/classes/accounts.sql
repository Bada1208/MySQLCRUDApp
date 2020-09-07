create database MySQlCRUDApp;
use MySQlCRUDApp;
create table Accounts
(
    Id            bigint  PRIMARY KEY ,
    AccountStatus enum ('ACTIVE', 'BANNED', 'DELETED') DEFAULT 'ACTIVE'
);
TRUNCATE accounts;
drop table accounts;
SELECT * FROM accounts;