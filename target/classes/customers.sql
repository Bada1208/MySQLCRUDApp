use mysqlcrudapp;
create table if not exists Customers
(
    Id            bigint primary key,
    Name          varchar(155),
    Surname       varchar(155),
    SpecialtiesId bigint,
    AccountId     bigint,
    foreign key (SpecialtiesId) references Specialties (id),
    foreign key (AccountId) references accounts (id)
);
