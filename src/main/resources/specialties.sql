use mysqlcrudapp;
create table if not exists Specialties
(
    Id        bigint,
    Specialty varchar(100)
);
truncate Specialties;
select * from specialties;