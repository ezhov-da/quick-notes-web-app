select * from T_E_note

create table T_E_note
(
	id integer not null
	,name varchar(256) not null
	,isHide bit not null default 0
)