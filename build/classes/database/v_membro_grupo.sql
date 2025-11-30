create view v_membro_grupo as 
select mg.membro_grupo_id, cl.cliente_id, cl.nome as nome_cliente, 
    ob.objetivo_id, ob.nome as nome_objetivo, mg.gestor
from membro_grupo mg
inner join cliente cl on cl.cliente_id = mg.cliente_id
inner join objetivo ob on ob.objetivo_id = mg.objetivo_id
order by mg.membro_grupo_id desc;