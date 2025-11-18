delimiter $$

drop procedure if exists p_delete_cliente $$
create procedure p_delete_cliente(
    in pClienteId int
)
proc: begin

    declare exc smallint default 0;
    declare v_count int;

    declare continue handler for sqlexception set exc = 1;

    if pClienteId is null or pClienteId <= 0 then
        select 'erro' as type, 'ID do cliente inválido.' as msg;
        leave proc;
    end if;

    start transaction;

    select count(*) into v_count
    from membro_grupo
    where cliente_id = pClienteId;

    if v_count > 0 then
        rollback;
        select 'erro' as type, concat('Não é possível remover: cliente vinculado a ', v_count, ' registro(s) como membro de grupo.') as msg;
        leave proc;
    end if;

    delete from telefone where cliente_id = pClienteId;

    delete from cliente where cliente_id = pClienteId;

    if row_count() = 0 then
        rollback;
        select 'erro' as type, 'Cliente não encontrado para remoção.' as msg;
        leave proc;
    end if;

    commit;

    if exc = 1 then
        rollback;
        select 'erro' as type, 'Não foi possível remover o cliente.' as msg;
    else
        select 'sucesso' as type, 'Cliente removido com sucesso.' as msg;
    end if;

end $$
delimiter ;