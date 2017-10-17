update T_E_note t0 
	set name = 'я-обновил' 
from T_E_link_user_note t1
where 
	t0.id = t1.idNote
	and
	t0.id = 137
	and
	t1.idUser = 1





update T_E_note_detail е0
	set 
		t0.text = ?, 
		t0.link = ? 
from T_E_link_user_note t1
where 
	t0.id = t1.idNote
	and
	t0.id = 137
	and
	t1.idUser = 1







update T_E_note_detail t0
	set 
		text = 'иййййя', 
		link = '?' 
from T_E_link_user_note t1
where 
	t0.id = t1.idNote
	and
	t0.id = 137
	and
	t1.idUser = 1	







select id, name, text, link, dateAdd, dateEdit from V_E_note_detail where id = 32

select id, name, text, link, dateAdd, dateEdit from V_E_note_detail where id = 162










update T_E_note t0 
	set name = ? 
from T_E_link_user_note t1
where 
	t0.id = t1.idNote
	and
	t0.id = ?
	and
	t1.idUser = ?


update T_E_note_detail t0
	set 
		text = ?, 
		link = ? 
from T_E_link_user_note t1
where 
	t0.id = t1.idNote
	and
	t0.id = ?
	and
	t1.idUser = ?	



update T_E_note_timestamp t0
	set 
		dateEdit = ?
from T_E_link_user_note t1
where 
	t0.id = t1.idNote
	and
	t0.id = ?
	and
	t1.idUser = ?	
	
	