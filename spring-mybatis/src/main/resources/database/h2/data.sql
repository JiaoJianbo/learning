-- H2 DB Functions: http://www.h2database.com/html/functions.html
-- 
insert into user (id, username, password, gender, age, birthday, create_date, update_date) 
values ('001', 'admin', '111111', 'M', 28, PARSEDATETIME('1991-09-25', 'yyyy-MM-dd'), now(), now());
insert into user (id, username, password, gender, age, birthday, create_date, update_date) 
values ('002', 'user', '111111', 'F', 18, PARSEDATETIME('2001-09-25', 'yyyy-MM-dd'), now(), now());
