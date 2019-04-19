-- H2 DB Functions: http://www.h2database.com/html/functions.html
/* Data for table user */
insert into user (id, username, password, locked, gender, age, birthday, create_date, update_date) 
values 
('001', 'admin', '111', NULL, 'M', 28, PARSEDATETIME('1991-09-25', 'yyyy-MM-dd'), now(), now()), 
('002', 'user', '111', '1','F', 18, PARSEDATETIME('2001-09-25', 'yyyy-MM-dd'), now(), now()),
('003', 'guest', '111', '0','F', 22, PARSEDATETIME('1999-02-25', 'yyyy-MM-dd'), now(), now());
