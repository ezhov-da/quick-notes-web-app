drop table T_E_note_timestamp

create table T_E_note_timestamp
(
	id integer not null
	,dateAdd timestamp not null 
	,dateEdit timestamp
)

-- drop table T_E_note_timestamp

insert into T_E_note_timestamp(id,dateAdd) values(?, ?)


insert into T_E_note_timestamp
(
	id
	,dateAdd
	,dateEdit
)
select 
	t0.id
	--,t1.id
	,now()
	,now()
from T_E_note t0
full join T_E_note_timestamp t1 on
	t0.id = t1.id
where
	t1.id is null






select * from T_E_note_timestamp

select CURRENT_TIMESTAMP