xdelimiter $$

drop procedure if exists p_salve_cliente $$
create procedure p_salve_cliente(
    in pClienteId               int,
    in pNome                    varchar(200),
    in pCpf                     char(11),
    in pDataNascimento          date,
    in pEmail                   varchar(120),
    in pSenha                   varchar(64),
    in pNumeroTelefoneMovel     char(13),
    in pNumeroTelefoneFixo      char(13)
)
proc: begin

    declare exc smallint default 0;
    declare v_count int;
    declare vTelefoneId int;

    declare continue handler for sqlexception set exc = 1;

    if pNome is null or pNome = '' then
        select 'erro' as type, 'Nome é obrigatório.' as msg;
        leave proc;
    end if;

    if pCpf is null or pCpf = '' then
        select 'erro' as type, 'CPF é obrigatório.' as msg;
        leave proc;
    end if;

    if char_length(pCpf) != 11 then
        select 'erro' as type, 'CPF deve conter 11 dígitos (somente números).' as msg;
        leave proc;
    end if;

    if pEmail is null or pEmail = '' then
        select 'erro' as type, 'E-mail é obrigatório.' as msg;
        leave proc;
    end if;

    if pSenha is null or pSenha = '' then
        select 'erro' as type, 'Senha é obrigatória.' as msg;
        leave proc;
    end if;

    start transaction;

    select count(*) into v_count
    from cliente
    where cpf = pCpf
      and (pClienteId = -1 or cliente_id != pClienteId);

    if v_count > 0 then
        select 'erro' as type, 'Já existe um cliente com esse CPF.' as msg;
        rollback;
        leave proc;
    end if;

    if pClienteId = -1 then
        insert into cliente (
            nome, cpf, data_nascimento, email, senha
        ) values (
            pNome, pCpf, pDataNascimento, pEmail, pSenha
        );

        select last_insert_id() into pClienteId;
    else
        update cliente
        set 
            nome = pNome,
            cpf = pCpf,
            data_nascimento = pDataNascimento,
            email = pEmail,
            senha = pSenha
        where cliente_id = pClienteId;
    end if;

    select telefone_id into vTelefoneId
    from telefone
    where cliente_id = pClienteId and tipo = 'm'
    limit 1;

    if pNumeroTelefoneMovel is null or trim(pNumeroTelefoneMovel) = '' then
        if vTelefoneId is not null then
            delete from telefone where telefone_id = vTelefoneId;
        end if;
    else
        if vTelefoneId is not null then
            update telefone
            set numero = pNumeroTelefoneMovel
            where telefone_id = vTelefoneId;
        else
            insert into telefone (numero, tipo, cliente_id)
            values (pNumeroTelefoneMovel, 'm', pClienteId);
        end if;
    end if;

    select telefone_id into vTelefoneId
    from telefone
    where cliente_id = pClienteId and tipo = 'f'
    limit 1;

    if pNumeroTelefoneFixo is null or trim(pNumeroTelefoneFixo) = '' then
        if vTelefoneId is not null then
            delete from telefone where telefone_id = vTelefoneId;
        end if;
    else
        if vTelefoneId is not null then
            update telefone
            set numero = pNumeroTelefoneFixo
            where telefone_id = vTelefoneId;
        else
            insert into telefone (numero, tipo, cliente_id)
            values (pNumeroTelefoneFixo, 'f', pClienteId);
        end if;
    end if;

    commit;

    if exc = 1 then
        rollback;
        select 'erro' as type, 'Não foi possível registrar o cliente.' as msg;
    else
        select pClienteId as id, 'sucesso' as type, 'Cliente registrado com sucesso.' as msg;
    end if;

end $$
delimiter ;