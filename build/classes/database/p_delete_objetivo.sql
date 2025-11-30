delimiter $$

-- apaga procedure caso já exista
drop procedure if exists p_delete_objetivo $$
create procedure p_delete_objetivo(
    in pObjetivoId int
)
proc: begin

    declare exc smallint default 0;
    declare v_count int;

    -- handler para exceções
    declare continue handler for sqlexception set exc = 1;

    -- valida ID
    if pObjetivoId is null or pObjetivoId <= 0 then
        select 'erro' as type, 'ID do objetivo inválido.' as msg;
        leave proc;
    end if;

    start transaction;

    -- verifica se existem registros dependentes
    select count(*) into v_count
    from membro_grupo
    where objetivo_id = pObjetivoId;

    if v_count > 0 then
        rollback;
        select 'erro' as type, concat('Não é possível remover: existem ', v_count, ' membros vinculados a este objetivo.') as msg;
        leave proc;
    end if;

    -- tenta remover o registro
    delete from objetivo where objetivo_id = pObjetivoId;

    if row_count() = 0 then
        rollback;
        select 'erro' as type, 'Objetivo não encontrado para remoção.' as msg;
        leave proc;
    end if;

    commit;

    -- verifica exceção
    if exc = 1 then
        rollback;
        select 'erro' as type, 'Não foi possível remover o objetivo.' as msg;
    else
        select 'sucesso' as type, 'Objetivo removido com sucesso.' as msg;
    end if;

end $$
delimiter ;
