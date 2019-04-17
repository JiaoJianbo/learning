-- H2 DB Functions: http://www.h2database.com/html/functions.html
-- 
insert into user (id, username, password, locked, gender, age, birthday, create_date, update_date) 
values 
('001', 'admin', '111111', NULL, 'M', 28, PARSEDATETIME('1991-09-25', 'yyyy-MM-dd'), now(), now()), 
('002', 'user', '111111', '1','F', 18, PARSEDATETIME('2001-09-25', 'yyyy-MM-dd'), now(), now());
