DELIMITER $$

DROP PROCEDURE IF EXISTS p_salve_membro_grupo $$

CREATE PROCEDURE p_salve_membro_grupo(
    IN pMembroGrupoId INT,
    IN pClienteId INT,
    IN pObjetivoId INT,
    IN pGestor TINYINT
)
proc: BEGIN
    DECLARE exc SMALLINT DEFAULT 0;
    DECLARE v_count INT;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET exc = 1;

    -- Validações
    IF pClienteId IS NULL OR pObjetivoId IS NULL THEN
        SELECT 'erro' AS type, 'Cliente e Objetivo são obrigatórios.' AS msg;
        LEAVE proc;
    END IF;

    START TRANSACTION;

    -- Verifica se o cliente já está neste objetivo (evitar duplicidade)
    SELECT COUNT(*) INTO v_count FROM membro_grupo 
    WHERE cliente_id = pClienteId AND objetivo_id = pObjetivoId
    AND (pMembroGrupoId = -1 OR membro_grupo_id != pMembroGrupoId);

    IF v_count > 0 THEN
        SELECT 'erro' AS type, 'Este cliente já faz parte deste grupo.' AS msg;
        ROLLBACK;
        LEAVE proc;
    END IF;

    IF pMembroGrupoId = -1 THEN
        INSERT INTO membro_grupo (cliente_id, objetivo_id, gestor)
        VALUES (pClienteId, pObjetivoId, pGestor);
        SELECT LAST_INSERT_ID() INTO pMembroGrupoId;
    ELSE
        UPDATE membro_grupo SET cliente_id = pClienteId, objetivo_id = pObjetivoId, gestor = pGestor
        WHERE membro_grupo_id = pMembroGrupoId;
    END IF;

    COMMIT;

    IF exc = 1 THEN
        ROLLBACK;
        SELECT 'erro' AS type, 'Erro ao salvar membro do grupo.' AS msg;
    ELSE
        SELECT pMembroGrupoId AS id, 'sucesso' AS type, 'Membro salvo com sucesso.' AS msg;
    END IF;
END $$

DROP PROCEDURE IF EXISTS p_delete_membro_grupo $$

CREATE PROCEDURE p_delete_membro_grupo(IN pMembroGrupoId INT)
BEGIN
    DECLARE exc SMALLINT DEFAULT 0;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET exc = 1;

    START TRANSACTION;
    -- Poderia haver validação de parcelas pagas aqui antes de deletar
    DELETE FROM membro_grupo WHERE membro_grupo_id = pMembroGrupoId;
    COMMIT;

    IF exc = 1 THEN
        ROLLBACK;
        SELECT 'erro' AS type, 'Erro ao remover membro.' AS msg;
    ELSE
        SELECT 'sucesso' AS type, 'Membro removido com sucesso.' AS msg;
    END IF;
END $$

DELIMITER ;