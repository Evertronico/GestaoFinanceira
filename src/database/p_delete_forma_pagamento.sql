delimiter $$

-- apaga procedure caso ela já exista
drop procedure if exists p_delete_forma_pagamento $$

-- cria procedure
create procedure p_delete_forma_pagamento(
    in pFormaPagamentoId int
)
proc: begin

    declare exc smallint default 0;
    declare continue handler for sqlexception set exc = 1;

    -- inicia a transação
    start transaction;

    -- remove o registro
    delete from forma_pagamento where forma_pagamento_id = pFormaPagamentoId;

    commit;

    -- verifica se ocorreu exceção
    if exc = 1 then
        rollback;
        select 'erro' as type, 'Não foi possível remover o registro de forma de pagamento.' as msg;
    else 
        select 'sucesso' as type, 'Registro removido.' as msg;
    end if;

    commit;

end $$