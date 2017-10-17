select tag from V_E_tag_cloud


drop view V_E_tag_cloud;
create or replace view V_E_tag_cloud as 
(
	select
		distinct
		tag
	from
	(
		select
			 t0.id
			,t0.name 
			,t0.pos
			,t0.tag
		from
		(
		select 
			id
			,name 
			,position(':' in name) as pos
			,substring(name from 1 for position(':' in name) - 1) as tag
		from T_E_note
		) t0
		where
			t0.pos > 0
	) t0
	order by tag
)