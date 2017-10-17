create table T_E_link_user_note
(
	 idUser int not null
	,idNote int not null
)

select * from T_E_link_user_note



/*
insert into T_E_link_user_note
select
	t0.id
	,t1.id
from T_E_users t0
cross join T_E_note t1
*/


/*


select
	t0.id
	,t1.id
from T_E_users t0
cross join T_E_note t1


select * from T_E_note

*/


select * from T_E_link_user_note