-- H2 DB Functions: http://www.h2database.com/html/functions.html
-- 
insert into user (id, username, password, gender, age, birthday, create_date, update_date) 
values ('001', 'admin', '111111', 'M', 18, PARSEDATETIME('2008-09-25', 'yyyy-MM-dd'), now(), now());

