

delimiter $$

-- Apaga a procedure se já existir
drop procedure if exists p_salve_cliente $$
create procedure p_salve_cliente(
    in pClienteId           int,
    in pNome                varchar(50),
    in pCpf                 varchar(14),
    in pDataNascimento      date,
    in pEmail               varchar(100),
    in pSenha               varchar(255)
)
proc: begin

    -- Declarações
    declare exc smallint default 0;
    declare v_count int;

    -- Handler para exceções SQL
    declare continue handler for sqlexception set exc = 1;

    -- Validações obrigatórias
    if pNome = '' or pNome is null then
        select 'erro' as type, 'Nome é obrigatório.' as msg;
        leave proc;
    end if;

    if pCpf = '' or pCpf is null then
        select 'erro' as type, 'CPF é obrigatório.' as msg;
        leave proc;
    end if;

    if pEmail = '' or pEmail is null then
        select 'erro' as type, 'Email é obrigatório.' as msg;
        leave proc;
    end if;

    if pDataNascimento is null or pDataNascimento > curdate() then
        select 'erro' as type, 'Data de nascimento inválida.' as msg;
        leave proc;
    end if;

    if pSenha = '' or pSenha is null then
        select 'erro' as type, 'Senha é obrigatória.' as msg;
        leave proc;
    end if;

    start transaction;

    -- Verifica duplicidade de CPF
    select count(*) into v_count
    from cliente
    where cpf = pCpf
      and (pClienteId = -1 or cliente_id != pClienteId);

    if v_count > 0 then
        select 'erro' as type, 'Já existe um cliente com esse CPF.' as msg;
        rollback;
        leave proc;
    end if;

    -- Verifica duplicidade de email
    select count(*) into v_count
    from cliente
    where email = pEmail
      and (pClienteId = -1 or cliente_id != pClienteId);

    if v_count > 0 then
        select 'erro' as type, 'Já existe um cliente com esse email.' as msg;
        rollback;
        leave proc;
    end if;

    -- Inserção
    if pClienteId = -1 then
        insert into cliente (
            nome, cpf, data_nascimento, email, senha
        ) values (
            pNome, pCpf, pDataNascimento, pEmail, pSenha
        );

        select last_insert_id() into pClienteId;

        commit;

    -- Atualização
    else
        set sql_safe_updates = 0;

        update cliente
        set 
            nome = pNome,
            cpf = pCpf,
            data_nascimento = pDataNascimento,
            email = pEmail,
            senha = pSenha
        where cliente_id = pClienteId;

        commit;
    end if;

    -- Tratamento de erro
    if exc = 1 then
        rollback;
        select 'erro' as type, 'Não foi possível registrar o cliente.' as msg;
    else
        select pClienteId as id, 'sucesso' as type, 'Cliente registrado com sucesso.' as msg;
    end if;

end $$
delimiter ;