DROP DATABASE IF EXISTS AV2;
CREATE DATABASE IF NOT EXISTS AV2;
USE AV2;

SET SQL_SAFE_UPDATES = 0;

DROP TABLE IF EXISTS Aventureiro;
CREATE TABLE IF NOT EXISTS Aventureiro (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    classe VARCHAR(50),
    nivel INT,
    experiencia INT DEFAULT 0
);

DROP TABLE IF EXISTS Missao;
CREATE TABLE IF NOT EXISTS Missao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    dificuldade VARCHAR(20),
    recompensa INT
);

DROP TABLE IF EXISTS Habilidade;
CREATE TABLE IF NOT EXISTS Participacao (
    aventureiro_id INT,
    missao_id INT,
    status VARCHAR(50),
    FOREIGN KEY (aventureiro_id) REFERENCES Aventureiro(id),
    FOREIGN KEY (missao_id) REFERENCES Missao(id),
    PRIMARY KEY (aventureiro_id, missao_id)
);

-- A) Crie um trigger 'nivel_experiencia' que, antes de inserir um aventureiro, verifica sua experiência e define um nível a ele
-- Se for menor que 1000, o nível deve ser 1
-- Se for maior ou igual a 1000 e menor que 5000, o nível deve ser 2
-- Se for maior ou igual a 5000, o nível deve ser 3
-- Dica: Utilize ELSEIF
-- Resposta:

DELIMITER //
CREATE TRIGGER nivel_experiencia BEFORE INSERT ON Aventureiro
FOR EACH ROW
BEGIN
    IF NEW.experiencia < 1000 THEN
        SET NEW.nivel = 1;
    ELSEIF NEW.experiencia >= 1000 AND NEW.experiencia < 5000 THEN
        SET NEW.nivel = 2;
    ELSE
        SET NEW.nivel = 3;
    END IF;
END;//
DELIMITER ;


-- B) Crie uma procedure 'registrar_aventureiro' que recebe um nome, uma classe e uma experiência, e insere um novo aventureiro
-- Resposta:
DELIMITER //
CREATE PROCEDURE registrar_aventureiro(nome VARCHAR(100), classe VARCHAR(50), experiencia INT)
BEGIN
    INSERT INTO Aventureiro (nome, classe, experiencia) VALUES (nome, classe, experiencia);
END;//
DELIMITER ;

-- C) Crie uma function 'descricao_missao' que recebe o título e a dificuldade de uma missão e retorna uma única string formatada 'Missão <titulo>: Dificuldade <dificuldade>'
-- Resposta:
DELIMITER //
DROP FUNCTION IF EXISTS descricao_missao //
CREATE FUNCTION descricao_missao (titulo VARCHAR(100), dificuldade VARCHAR(20)) RETURNS VARCHAR(100)
DETERMINISTIC 
BEGIN 
	RETURN CONCAT('Missão ', titulo, ': Dificuldade ', dificuldade);
END; //
DELIMITER ;

-- D) Use a procedure 'registrar_aventureiro' para inserir 3 aventureiros com diferentes níveis de experiência
-- Resposta:
CALL registrar_aventureiro('Marcus', 'Bruxo', 5623);
CALL registrar_aventureiro('João', 'Guerreiro', 3498);
CALL registrar_aventureiro('Chico', 'Caçador' , 958);

-- E) Insira 3 missões com diferentes dificuldades e recompensas, depois, chame a function 'descricao_missao' para cada uma delas
-- Resposta:
INSERT INTO Missao (titulo, dificuldade, recompensa) VALUES ('Guerra da Magia', 'Alta', 5555);
INSERT INTO Missao (titulo, dificuldade, recompensa) VALUES ('Batalha dos zumbis', 'Média', 3333);
INSERT INTO Missao (titulo, dificuldade, recompensa) VALUES ('Caça as lobas', 'Baixa', 1111);

SELECT descricao_missao('Guerra da Magia', 'Alta');
SELECT descricao_missao('Batalha dos zumbis', 'Média');
SELECT descricao_missao('Caça as lobas', 'Baixa');

-- F) Escolha dois dos três aventureiros e duas das três missões, relacione-os os dois aventureiros as duas missões, uma com status 'Falhou' e outra com status 'Concluída'
-- Resposta:
INSERT INTO Participacao VALUES (1, 1, 'Concluída');
INSERT INTO Participacao VALUES (1, 2, 'Falhou');
INSERT INTO Participacao VALUES (2, 2, 'Concluída');
INSERT INTO Participacao VALUES (2, 1, 'Falhou');
select * from Participacao;

-- G) Crie uma view 'resumo_aventureiro' que retorne o nome do aventureiro, o nome da missão, o status e a recompensa, depois, chame a view
-- Resposta:
CREATE VIEW resumo_aventureiro AS
SELECT a.nome AS aventureiro, m.titulo AS missao, p.status, m.recompensa
FROM Aventureiro a
JOIN Participacao p ON a.id = p.aventureiro_id
JOIN Missao m ON m.id = p.missao_id;

SELECT * FROM resumo_aventureiro;

-- H) Busque o nome do aventureiro, a classe do aventureiro e o título da missão executada por ele
-- Resposta:
SELECT a.nome, a.classe, m.titulo
FROM Aventureiro a
JOIN Participacao p ON a.id = p.aventureiro_id
JOIN Missao m ON p.missao_id = m.id;

-- I) Busque todas as informações de todos os aventureiros e o nome de suas missões, mesmo que não tenham participado de nenhuma missão
-- Resposta:
SELECT a.*, m.titulo
FROM Aventureiro a
LEFT JOIN Participacao p ON a.id = p.aventureiro_id
LEFT JOIN Missao m ON p.missao_id = m.id;

-- J) Busque todas as informações de todas as missões e o nome de seus aventureiros, mesmo que nenhuma aventureiro tenha participado da missão
-- Resposta:
SELECT m.*, a.nome
FROM Missao m
LEFT JOIN Participacao p ON m.id = p.missao_id
LEFT JOIN Aventureiro a ON p.aventureiro_id = a.id;