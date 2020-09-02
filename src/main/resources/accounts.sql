create database MySQlCRUDApp;
use MySQlCRUDApp;
create table Accounts
(
    Id            int,
    AccountStatus enum ('ACTIVE', 'BANNED', 'DELETED') DEFAULT 'ACTIVE'
);
TRUNCATE accounts;
SELECT * FROM accounts;