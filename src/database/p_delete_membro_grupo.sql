DELIMITER $$

DROP PROCEDURE IF EXISTS p_delete_membro_grupo$$

CREATE PROCEDURE p_delete_membro_grupo(
    IN pObjetivoId INT,
    IN pClienteId INT
)
BEGIN
    DECLARE v_count INT DEFAULT 0;

    -- Verifica se o vínculo existe
    SELECT COUNT(*) INTO v_count
    FROM membro_grupo
    WHERE objetivo_id = pObjetivoId 
      AND cliente_id = pClienteId;

    IF v_count = 0 THEN
        -- Retorna erro com a coluna 'msg' (exatamente como o Java espera)
        SELECT 'Vínculo não encontrado.' AS msg;
    ELSE
        -- Remove o vínculo
        DELETE FROM membro_grupo
        WHERE objetivo_id = pObjetivoId 
          AND cliente_id = pClienteId;

        -- Retorna sucesso
        SELECT 'Membro removido do objetivo com sucesso!' AS msg;
    END IF;
END$$

DELIMITER ;