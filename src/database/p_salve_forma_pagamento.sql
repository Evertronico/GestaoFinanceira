delimiter $$

drop procedure if exists p_salve_forma_pagamento $$

create procedure p_salve_forma_pagamento(
    in pFormaPagamentoId int,
    in pDescricao        varchar(50)
)
proc: begin

    -- declarações devem vir primeiro
    declare exc smallint default 0;
    declare v_count int;

    -- depois, handlers
    declare continue handler for sqlexception set exc = 1;

    -- valida campo obrigatório
    if pDescricao = '' then
        select 'erro' as type, 'Descrição é obrigatória.' as msg;
        leave proc;
    end if;

    start transaction;

    -- verifica se já existe uma forma de pagamento com o mesmo nome
    select count(*) into v_count
    from forma_pagamento
    where descricao = pDescricao
      and (pFormaPagamentoId = -1 or forma_pagamento_id != pFormaPagamentoId);

    if v_count > 0 then
        select 'erro' as type, 'Já existe uma forma de pagamento com essa descrição.' as msg;
        rollback;
        leave proc;
    end if;

    -- caso a forma de pagamento não exista, insere
    if pFormaPagamentoId = -1 then
        insert into forma_pagamento (descricao)
        values (pDescricao);

        select last_insert_id() into pFormaPagamentoId;

        commit;

    else
        set sql_safe_updates = 0;

        update forma_pagamento
        set descricao = pDescricao
        where forma_pagamento_id = pFormaPagamentoId;

        commit;
    end if;

    if exc = 1 then
        rollback;
        select 'erro' as type, 'Não foi possível registrar a forma de pagamento.' as msg;
    else
        select pFormaPagamentoId as id, 'sucesso' as type, 'Forma de pagamento registrada' as msg;
    end if;

end $$
