delimiter $$

drop procedure if exists p_salve_membro_grupo $$

create procedure p_salve_membro_grupo(
    in pMembroGrupoId int,
    in pClienteId int,
    in pObjetivoId int,
    in pGestor tinyint
)
proc: begin

    declare exc smallint default 0;
    declare vExiste int default 0;
    declare vCotas int default 0;
    declare vTotalMembros int default 0;
    declare vNumParcelas int default 0;
    declare vValorIntegralizacao decimal(10,2);
    declare vValorParcela decimal(10,2);
    declare vNovoMembroId int;
    declare vContador int default 1;
    declare vDataVencimento date;
    
    declare continue handler for sqlexception set exc = 1;

    start transaction;

    select count(*) into vExiste 
    from membro_grupo 
    where cliente_id = pClienteId 
      and objetivo_id = pObjetivoId 
      and membro_grupo_id <> pMembroGrupoId; -- Garante que não bloqueie a própria edição

    if vExiste > 0 then
        select 'erro' as type, 'Este cliente já é membro deste grupo (Objetivo).' as msg;
        rollback;
        leave proc;
    end if;

    select cotas, numero_parcelas, valor_integralizacao 
    into vCotas, vNumParcelas, vValorIntegralizacao
    from objetivo 
    where objetivo_id = pObjetivoId;

    if pMembroGrupoId = -1 or pMembroGrupoId = 0 then

        select count(*) into vTotalMembros from membro_grupo where objetivo_id = pObjetivoId;

        if vTotalMembros >= vCotas then
            select 'erro' as type, 'Limite de cotas para este objetivo foi atingido.' as msg;
            rollback;
            leave proc;
        end if;

        insert into membro_grupo (cliente_id, objetivo_id, gestor)
        values (pClienteId, pObjetivoId, pGestor);

        set vNovoMembroId = last_insert_id();

        if vCotas > 0 and vNumParcelas > 0 then
            set vValorParcela = (vValorIntegralizacao / vCotas) / vNumParcelas;
            
            set vContador = 1;
            while vContador <= vNumParcelas do
                set vDataVencimento = date_add(curdate(), interval vContador month);

                insert into parcela (membro_grupo_id, valor, data_vencimento)
                values (vNovoMembroId, vValorParcela, vDataVencimento);

                set vContador = vContador + 1;
            end while;
        end if;

    else
        update membro_grupo 
        set cliente_id = pClienteId, 
            objetivo_id = pObjetivoId, 
            gestor = pGestor
        where membro_grupo_id = pMembroGrupoId;
        
    end if;

    if exc = 1 then
        rollback;
        select 'erro' as type, 'Erro ao salvar membro do grupo.' as msg;
    else 
        commit;
        select 'sucesso' as type, 'Membro salvo e parcelas geradas com sucesso.' as msg;
    end if;

end $$

delimiter ;