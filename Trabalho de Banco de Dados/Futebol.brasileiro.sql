CREATE DATABASE Futebol;
USE Futebol;

CREATE TABLE Tecnicos (
    Cpf INT PRIMARY KEY,
    nome VARCHAR(50),
    estilo_de_jogo VARCHAR(45),
    nacionalidade VARCHAR(45)
);

CREATE TABLE Estádios (
    Localizacao VARCHAR(40) PRIMARY KEY,
    Capacidade INT,
    gramado VARCHAR(45),
    nome VARCHAR(45)
);

CREATE TABLE Time (
    nome VARCHAR(40) PRIMARY KEY,
    cor VARCHAR(45),
    localizacao VARCHAR(45),
    Tecnicos_Cpf INT,
    Estádios_Localizacao VARCHAR(40),
    FOREIGN KEY (Tecnicos_Cpf) REFERENCES Tecnicos(Cpf),
    FOREIGN KEY (Estádios_Localizacao) REFERENCES Estádios(Localizacao)
);

CREATE TABLE Jogador (
    nome VARCHAR(50),
    posicao VARCHAR(45),
    idade INT,
    cpf INT PRIMARY KEY,
    time_nome VARCHAR(40),
    FOREIGN KEY (time_nome) REFERENCES Time(nome)
);

CREATE TABLE Campeonato (
    nome VARCHAR(45) PRIMARY KEY,
    formato VARCHAR(45),
    divisao VARCHAR(45)
);

CREATE TABLE time_has_Campeonato (
    time_nome VARCHAR(40),
    Tecnicos_Cpf INT,
    Campeonato_nome VARCHAR(45),
    FOREIGN KEY (time_nome) REFERENCES Time(nome),
    FOREIGN KEY (Tecnicos_Cpf) REFERENCES Tecnicos(Cpf),
    FOREIGN KEY (Campeonato_nome) REFERENCES Campeonato(nome)
);

INSERT INTO Tecnicos VALUES (101, 'Abel Ferreira', 'Equilibrado', 'Portugal');
INSERT INTO Tecnicos VALUES (102, 'Dorival Junior', 'Defensivo', 'Brasil');
select * from Tecnicos;

INSERT INTO Estádios VALUES ('Barra Funda', 43713, 'Sintético', 'Allianz Parque');
INSERT INTO Estádios VALUES ('Itaquera', 49205, 'Natural', 'Arena Corinthians');
select * from Estádios;

INSERT INTO Time VALUES ('Palmeiras', 'Alviverde', 'São Paulo', 101, 'Barra Funda');
INSERT INTO Time VALUES ('Corinthians', 'Alvinegro', 'São Paulo', 102, 'Itaquera');
select * from Time;

INSERT INTO Jogador VALUES ('Estevão', 'Atacante', 18, 201, 'Palmeiras');
INSERT INTO Jogador VALUES ('Yuri', 'Atacante', 24, 202, 'Corinthians');
select * from Jogador;

INSERT INTO Campeonato VALUES ('Brasileirão', 'Pontos Corridos', 'A');
INSERT INTO Campeonato VALUES ('Copa do Brasil', 'Mata-Mata', 'Única');
select * from Campeonato;

INSERT INTO time_has_Campeonato VALUES ('Palmeiras', 101, 'Brasileirão');
INSERT INTO time_has_Campeonato VALUES ('Corinthians', 102, 'Copa do Brasil');
select * from time_has_Campeonato;

-- ATUALIZAÇÕES
UPDATE Tecnicos SET nacionalidade = 'Uruguai' WHERE Cpf = 102;
UPDATE Tecnicos SET estilo_de_jogo = 'Ofensivo' WHERE Cpf = 101;
select * from Tecnicos;

UPDATE Estádios SET Capacidade = 62000 WHERE Localizacao = 'Itaquera';
UPDATE Estádios SET gramado = 'Híbrido' WHERE Localizacao = 'Barra Funda';
select * from Estádios;

UPDATE Time SET cor = 'Verde e Branco' WHERE nome = 'Palmeiras';
UPDATE Time SET localizacao = 'SP' WHERE nome = 'Corinthians';
select * from Time;

UPDATE Jogador SET idade = 19 WHERE cpf = 201;
UPDATE Jogador SET posicao = 'Centro Avante' WHERE cpf = 202;
select * from Jogador;

UPDATE Campeonato SET formato = 'Grupos e Eliminatórias' WHERE nome = 'Copa do Brasil';
UPDATE Campeonato SET divisao = 'B' WHERE nome = 'Brasileirão';
select * from Campeonato;

-- Jogadores, Times e Técnicos
SELECT j.nome AS Jogador, t.nome AS Time, te.nome AS Tecnico
FROM Jogador j
JOIN Time t ON j.time_nome = t.nome
JOIN Tecnicos te ON t.Tecnicos_Cpf = te.Cpf;

-- Times, Estádios e Técnicos
SELECT t.nome AS Time, e.nome AS Estádio, te.nome AS Tecnico
FROM Time t
JOIN Estádios e ON t.Estádios_Localizacao = e.Localizacao
JOIN Tecnicos te ON t.Tecnicos_Cpf = te.Cpf;

-- Times, Campeonatos e Técnicos
SELECT ti.nome AS Time, c.nome AS Campeonato, te.nome AS Tecnico
FROM time_has_Campeonato thc
JOIN Time ti ON thc.time_nome = ti.nome
JOIN Campeonato c ON thc.Campeonato_nome = c.nome
JOIN Tecnicos te ON thc.Tecnicos_Cpf = te.Cpf;

-- Views são ideais para consultas complexas que envolvem múltiplas tabelas
-- Elas facilitam a reutilização de consultas e melhoram a segurança e manutenção
CREATE VIEW vw_time_detalhes AS
SELECT 
    t.nome AS time_nome,
    t.cor,
    te.nome AS tecnico_nome,
    e.Localizacao AS estadio_local,
    e.Capacidade,
    e.gramado
FROM Time t
JOIN Tecnicos te ON t.Tecnicos_Cpf = te.Cpf
JOIN Estádios e ON t.Estádios_Localizacao = e.Localizacao;
SELECT * FROM vw_time_detalhes;

-- Triggers são úteis para manter a consistência automática dos dados
-- Impede a exclusão de técnico se ele ainda estiver associado a algum time
DELIMITER //
CREATE TRIGGER before_delete_tecnico
BEFORE DELETE ON Tecnicos
FOR EACH ROW
BEGIN
    IF EXISTS (SELECT 1 FROM Time WHERE Tecnicos_Cpf = OLD.Cpf) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Não é possível excluir técnico com times associados.';
    END IF;
END;
//
DELIMITER ;

-- EXCLUSÕES
DELETE FROM Jogador WHERE cpf = 202;
DELETE FROM Jogador WHERE cpf = 201;

DELETE FROM time_has_Campeonato WHERE time_nome = 'Palmeiras';
DELETE FROM time_has_Campeonato WHERE time_nome = 'Corinthians';

DELETE FROM Campeonato WHERE nome = 'Brasileirão';
DELETE FROM Campeonato WHERE nome = 'Copa do Brasil';

DELETE FROM Time WHERE nome = 'Palmeiras';
DELETE FROM Time WHERE nome = 'Corinthians';

DELETE FROM Estádios WHERE Localizacao = 'Barra Funda';
DELETE FROM Estádios WHERE Localizacao = 'Itaquera';

DELETE FROM Tecnicos WHERE Cpf = 101;
DELETE FROM Tecnicos WHERE Cpf = 102;

