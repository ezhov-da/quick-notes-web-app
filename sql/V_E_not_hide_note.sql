select id, name from V_E_not_hide_note

drop view V_E_not_hide_note;
create view V_E_not_hide_note as
(
	select 
		t0.idUser
		,t0.idNote
		,t1.name
		,
	from T_E_link_user_note t0
	inner join T_E_note  t1 on
		t0.idNote = t1.id
	where 
		t1.isHide = cast(0 as bit) 
	order 
		by name
)

select idNote, name, idUser from V_E_not_hide_note where idUser = 1