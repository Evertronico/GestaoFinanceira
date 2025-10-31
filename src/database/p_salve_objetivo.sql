delimiter $$

drop procedure if exists p_salve_objetivo $$
create procedure p_salve_objetivo(
    in pObjetivoId              int,
    in pNome                    varchar(50),
    in pValorIntegralizacao     decimal(10,2),
    in pDataRealizacao          date,
    in pJurosAtrasoDiario       decimal(10,2),
    in pMultaAtraso             decimal(10,2),
    in pNumeroParcelas          int,
    in pCotas                   int
)
proc: begin

    -- declarações
    declare exc smallint default 0;
    declare v_count int;

    -- handler
    declare continue handler for sqlexception set exc = 1;

    -- validações obrigatórias
    if pNome = '' or pNome is null then
        select 'erro' as type, 'Nome é obrigatório.' as msg;
        leave proc;
    end if;

    if pValorIntegralizacao is null or pValorIntegralizacao <= 0 then
        select 'erro' as type, 'Valor de integralização deve ser maior que zero.' as msg;
        leave proc;
    end if;

    if pNumeroParcelas is null or pNumeroParcelas <= 0 then
        select 'erro' as type, 'Número de parcelas deve ser maior que zero.' as msg;
        leave proc;
    end if;

    if pCotas is null or pCotas <= 0 then
        select 'erro' as type, 'Quantidade de cotas deve ser maior que zero.' as msg;
        leave proc;
    end if;

    start transaction;

    -- verifica duplicidade de nome (um nome único por objetivo)
    select count(*) into v_count
    from objetivo
    where nome = pNome
      and (pObjetivoId = -1 or objetivo_id != pObjetivoId);

    if v_count > 0 then
        select 'erro' as type, 'Já existe um objetivo com esse nome.' as msg;
        rollback;
        leave proc;
    end if;

    -- inserção
    if pObjetivoId = -1 then
        insert into objetivo (
            nome, valor_integralizacao, data_realizacao, 
            juros_atraso_diario, multa_atraso, numero_parcelas, cotas
        ) values (
            pNome, pValorIntegralizacao, pDataRealizacao,
            pJurosAtrasoDiario, pMultaAtraso, pNumeroParcelas, pCotas
        );

        select last_insert_id() into pObjetivoId;

        commit;

    else
        -- atualização
        set sql_safe_updates = 0;

        update objetivo
        set 
            nome = pNome,
            valor_integralizacao = pValorIntegralizacao,
            data_realizacao = pDataRealizacao,
            juros_atraso_diario = pJurosAtrasoDiario,
            multa_atraso = pMultaAtraso,
            numero_parcelas = pNumeroParcelas,
            cotas = pCotas
        where objetivo_id = pObjetivoId;

        commit;
    end if;

    -- tratamento de erro
    if exc = 1 then
        rollback;
        select 'erro' as type, 'Não foi possível registrar o objetivo.' as msg;
    else
        select pObjetivoId as id, 'sucesso' as type, 'Objetivo registrado com sucesso.' as msg;
    end if;

end $$
delimiter ;
