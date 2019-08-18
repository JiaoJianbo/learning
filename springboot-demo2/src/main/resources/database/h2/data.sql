-- 'script' can not be null or empty
select 1 from dual;

-- data of table department
insert into department (`name`) values ('开发部');

-- data of table employee
insert into employee (age, email, `name`, dept_id) values (20, 'bobby1@test.com', 'Bobby1', 1);
insert into employee (age, email, `name`, dept_id) values (21, 'bobby2@123.com', 'Bobby2', 1);
insert into employee (age, email, `name`, dept_id) values (22, 'bob1@test.com', 'Bob1', 1);
insert into employee (age, email, `name`, dept_id) values (23, 'bob2@123.com', 'Bob2', 1);
