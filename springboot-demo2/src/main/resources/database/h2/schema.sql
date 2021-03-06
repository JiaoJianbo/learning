-- 来源于自动生成的建表脚本
drop table attachment if exists;

drop table department if exists;

drop table employee if exists;

drop table person if exists;

create table attachment (
  id bigint generated by default as identity,
  create_by varchar(255),
  create_date timestamp not null,
  modify_by varchar(255),
  modify_date timestamp not null,
  name varchar(200) not null,
  url varchar(500),
  primary key (id)
);

create table department (
  id bigint generated by default as identity,
  name varchar(255),
  primary key (id)
);

create table employee (
  id bigint generated by default as identity,
  age integer,
  email varchar(255),
  name varchar(255),
  dept_id bigint,
  primary key (id)
);

create table person (
  id_card_num varchar(18) not null,
  name varchar(32) not null,
  address varchar(255),
  age integer,
  primary key (id_card_num, name)
);

alter table employee
  add constraint FKaqchbcb8i6nvtl9g6c72yba0p
    foreign key (dept_id)
      references department;
