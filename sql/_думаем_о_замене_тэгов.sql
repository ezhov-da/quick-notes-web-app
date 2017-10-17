select
	 t0.id
	,t0.text 
	,t0.pos
	,t0.pos1
	,t0.pos2
from
(
select 
	id
	,text 
	,position('[code:]' in text) as pos
	,position('[:code]' in text) as pos1
	,position('[/code]' in text) as pos2
from T_E_note_detail
) t0
where
	t0.pos > 0 and pos1 > 0 and pos2 > 0