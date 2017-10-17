select * from T_E_note
select * from T_E_note_detail

select 
	t0.id, 
	t0.name,
	t1.text,
	t1.link	
from T_E_note t0
inner join T_E_note_detail t1 on
	t0.id = t1.id
order by 
	name	

--delete from T_E_note where id in (19, 11,12,37);
--delete from T_E_note_detail where id in (19, 11,12,37)