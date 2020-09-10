use mysqlcrudapp;
create table if not exists specialties
(
    id        bigint PRIMARY KEY,
    specialty varchar(100)
);
insert into specialties values (1,'cooker'),(2,'coder'),(3,'driver'),(4,'miner');
drop table Specialties;
truncate Specialties;
select * from specialties;