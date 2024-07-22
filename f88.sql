create database f88;
use f88;
insert into accounts(username,password) values('admin','adminadmin'),('user','user');
insert into roles (code,name) values ('ADMIN','admin'),('STUDENT','student');
insert into roles_accounts(roles_id,accounts_id) values (1,1),(2,2);
