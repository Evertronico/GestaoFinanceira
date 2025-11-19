delimiter $$

drop procedure if exists p_delete_membro_grupo $$

create procedure p_delete_membro_grupo(
    in pMembroGrupoId int
)
proc: begin

    declare exc smallint default 0;
    declare vTemPagamento int default 0;
    declare continue handler for sqlexception set exc = 1;

    start transaction;

    select count(*) into vTemPagamento
    from parcela p
    inner join transacao t on t.parcela_id = p.parcela_id
    where p.membro_grupo_id = pMembroGrupoId;

    if vTemPagamento > 0 then
        select 'erro' as type, 'Não é possível remover: O membro possui parcelas pagas (transações registradas).' as msg;
        rollback;
        leave proc;
    end if;

    delete from parcela where membro_grupo_id = pMembroGrupoId;

    delete from membro_grupo where membro_grupo_id = pMembroGrupoId;

    if exc = 1 then
        rollback;
        select 'erro' as type, 'Não foi possível remover o membro do grupo.' as msg;
    else 
        commit;
        select 'sucesso' as type, 'Membro e parcelas pendentes removidos.' as msg;
    end if;

end $$

delimiter ;