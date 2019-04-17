--
create table user (
	id varchar(32) primary key,
	username varchar(100) not null,
	password varchar(100) not null,
	locked char,
	gender char, 
	age tinyint, 
	birthday date, 
	create_date timestamp, 
	last_logon_time timestamp, 
	update_date timestamp
); 