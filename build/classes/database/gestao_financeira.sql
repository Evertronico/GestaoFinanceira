-- Cria o banco de dados e seleciona
CREATE DATABASE IF NOT EXISTS gestao_financeira CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE gestao_financeira;

-- =============================
-- 1. Tabelas sem dependências
-- =============================

CREATE TABLE cliente (
  cliente_id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(200) NOT NULL,
  cpf CHAR(11) DEFAULT NULL,
  data_nascimento DATE NOT NULL,
  email VARCHAR(120) NOT NULL,
  senha VARCHAR(64) NOT NULL,
  PRIMARY KEY (cliente_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE forma_pagamento (
  forma_pagamento_id INT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(50) NOT NULL,
  PRIMARY KEY (forma_pagamento_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE objetivo (
  objetivo_id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  valor_integralizacao DECIMAL(10,2) NOT NULL,
  data_realizacao DATE NOT NULL,
  juros_atraso_diario DECIMAL(10,2) NOT NULL,
  multa_atraso DECIMAL(10,2) NOT NULL,
  numero_parcelas INT NOT NULL,
  cotas INT NOT NULL,
  PRIMARY KEY (objetivo_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- =============================
-- 2. Tabelas dependentes
-- =============================

CREATE TABLE telefone (
  telefone_id INT NOT NULL AUTO_INCREMENT,
  numero CHAR(13) NOT NULL,
  tipo ENUM('f','m') NOT NULL,
  cliente_id INT NOT NULL,
  PRIMARY KEY (telefone_id),
  CONSTRAINT fk_telefone_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (cliente_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE membro_grupo (
  membro_grupo_id INT NOT NULL AUTO_INCREMENT,
  cliente_id INT NOT NULL,
  objetivo_id INT NOT NULL,
  gestor TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (membro_grupo_id),
  CONSTRAINT fk_grupo_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (cliente_id),
  CONSTRAINT fk_grupo_objetivo FOREIGN KEY (objetivo_id) REFERENCES objetivo (objetivo_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE parcela (
  parcela_id INT NOT NULL AUTO_INCREMENT,
  membro_grupo_id INT NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  data_vencimento DATE NOT NULL,
  PRIMARY KEY (parcela_id),
  CONSTRAINT fk_parcela_membro_grupo FOREIGN KEY (membro_grupo_id) REFERENCES membro_grupo (membro_grupo_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE transacao (
  transacao_id INT NOT NULL AUTO_INCREMENT,
  parcela_id INT NOT NULL,
  forma_pagamento_id INT NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  data_pagamento DATE NOT NULL,
  desconto DECIMAL(10,2) DEFAULT 0.00,
  juros DECIMAL(10,2) DEFAULT 0.00,
  multa DECIMAL(10,2) DEFAULT 0.00,
  PRIMARY KEY (transacao_id),
  CONSTRAINT fk_transacao_parcela FOREIGN KEY (parcela_id) REFERENCES parcela (parcela_id),
  CONSTRAINT fk_transacao_forma_pagamento FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento (forma_pagamento_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- =============================
-- 3. Inserções iniciais
-- =============================

INSERT INTO forma_pagamento (descricao)
VALUES 
  ('Dinheiro'),
  ('PIX'),
  ('Cartão de Crédito'),
  ('Cartão de Débito');

INSERT INTO objetivo (nome, valor_integralizacao, data_realizacao, juros_atraso_diario, multa_atraso, numero_parcelas, cotas)
VALUES
  ('Viagem', 1000.00, '2025-12-20', 1.00, 5.00, 3, 1),
  ('Trocar de celular', 4000.00, '2026-02-15', 0.00, 0.00, 4, 1),
  ('Comprar Tênis', 350.00, '2026-10-16', 1.00, 2.00, 2, 1);
