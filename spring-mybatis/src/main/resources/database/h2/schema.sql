-- Table structure for table `user`
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
create table persistent_logins (
    username varchar(64) not null,
    series varchar(64) primary key,
    token varchar(64) not null,
    last_used timestamp not null
);


-- 注意：直接写“id int(11) PRIMARY KEY auto_increment comment '自增主键' ” 会报错。PRIMARY KEY (id) 定义要单独写在下面
create table sys_role(
	id int(11) not null auto_increment comment '自增主键',
	name varchar(64) not null,
	status varchar(10),
	PRIMARY KEY (id)
);

-- Table structure for table `sys_role_user`
create table sys_role_user(
	id int(11) not null auto_increment comment '自增主键',
	rid int(11) not null,
	uid varchar(36) not null,
	PRIMARY KEY (id)
);

