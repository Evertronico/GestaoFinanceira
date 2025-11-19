DELIMITER $$

DROP PROCEDURE IF EXISTS p_salve_membro_grupo$$

CREATE PROCEDURE p_salve_membro_grupo(
    IN pObjetivoId INT,
    IN pClienteId INT
)
BEGIN
    DECLARE v_existe INT DEFAULT 0;

    SELECT COUNT(*) INTO v_existe
    FROM membro_grupo
    WHERE objetivo_id = pObjetivoId AND cliente_id = pClienteId;

    IF v_existe > 0 THEN
        SELECT 'Este cliente já está vinculado a este objetivo.' AS msg;
    ELSE
        INSERT INTO membro_grupo (cliente_id, objetivo_id, gestor)
        VALUES (pClienteId, pObjetivoId, 0);

        SELECT 'Membro vinculado com sucesso!' AS msg;
    END IF;
END$$

DELIMITER ;