select id, name, pass from T_E_users

create table T_E_users
(
	 id  SERIAL
	,name varchar(200) unique
	,pass varchar(100)
)

select * from t_e_users_id_seq


insert into T_E_users(name, pass) values ('test', '123')
insert into T_E_users(name, pass) values ('1@mail.ru', '1')


update T_E_users set name = 'test@mail.ru' where id = 1