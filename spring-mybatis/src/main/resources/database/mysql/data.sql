
/* Data for table user */
insert into user (id, username, password, locked, gender, age, birthday, create_date, update_date,last_logon_time) 
values 
('001', 'admin', '111', NULL, 'M', 28, STR_TO_DATE('1991-09-25', '%Y-%m-%d'), now(), now(), NULL), 
('002', 'user', '111', '1','F', 18, STR_TO_DATE('2001-09-25', '%Y-%m-%d'), now(), now(), NULL),
('003', 'guest', '111', '0','F', 22, STR_TO_DATE('1999-02-25', '%Y-%m-%d'), now(), now(), NULL);
