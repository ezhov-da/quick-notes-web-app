select id, name, text, link, dateAdd, dateEdit from V_E_note_detail

drop view V_E_note_detail;
create view V_E_note_detail as
(
	select 
		t0.id, 
		t0.name,  
		t1.text, 
		t1.link,
		t2.dateAdd,
		t2.dateEdit
	from T_E_note t0
        inner join T_E_note_detail t1 on		
		t0.id = t1.id 
	inner join T_E_note_timestamp t2 on
		t0.id = t2.id
)


select * from T_E_note


select * from T_E_note_detail


select * from T_E_note_timestamp