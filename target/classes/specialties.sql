use mysqlcrudapp;
create table if not exists Specialties
(
    Id        bigint PRIMARY KEY,
    Specialty varchar(100)
);
drop table Specialties;
truncate Specialties;
select * from specialties;