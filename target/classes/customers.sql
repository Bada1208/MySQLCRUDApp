use mysqlcrudapp;
create table if not exists customers
(
    id         bigint primary key,
    name       varchar(155),
    surname    varchar(155),
    account_id bigint,
    foreign key (account_id) references accounts (id)
);
create table if not exists customer_specialties
(
    customer_id  bigint not null,
    specialty_id bigint not null,
    UNIQUE (customer_id, specialty_id),
    foreign key (customer_id) references customers (id) ,
    foreign key (specialty_id) references specialties (id)

);
drop table customers;
drop table customer_specialties;
insert into customers
values (1, 'ivan', 'ivanov', 2),(2, 'vasiliy', 'vasev', 3);
insert into customer_specialties values (1, 3), (1, 2), (2, 4);
/*select * from developer_specialties ds LEFT JOIN developers d on ds.developer_id =d.id WHERE ds.developer_id = 1
select d.name, s.name from developer_specialties ds LEFT JOIN developers d on ds.developer_id =d.id
LEFT JOIN specialties s
ON ds.specialty_id = s.id
 WHERE ds.developer_id = 1*/
select *
from customers;
delete from customers where id=3;
select *
from customer_specialties;

select customers.id, customers.name, customers.surname, customer_specialties.specialty_id, customers.account_id
from customers,
     customer_specialties
where customers.id = customer_specialties.customer_id;

select customers.id, customers.name, customers.surname, customer_specialties.specialty_id, customers.account_id
from customers
         join customer_specialties on
         customer_specialties.customer_id = customers.id where customers.id=1;