-- Table structure for table `user`
DROP TABLE IF EXISTS `user`;

create table user (
	id varchar(36) primary key,
	username varchar(100) not null,
	password varchar(100) not null,
	locked char,
	gender char, 
	age tinyint, 
	birthday date, 
	create_date timestamp, 
	last_logon_time timestamp, 
	update_date timestamp,
	UNIQUE KEY `user_username` (`username`) 
); 

-- Table structure for table `persistent_logins`. Spring Security Remember-Me function
DROP TABLE IF EXISTS `persistent_logins`;

create table persistent_logins (
    username varchar(64) not null,
    series varchar(64) primary key,
    token varchar(64) not null,
    last_used timestamp not null
);


