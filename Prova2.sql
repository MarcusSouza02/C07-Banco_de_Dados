DROP DATABASE IF EXISTS AV2;
CREATE DATABASE IF NOT EXISTS AV2;
USE AV2;
 
SET SQL_SAFE_UPDATES = 0;
 
CREATE TABLE IF NOT EXISTS Cozinheiros (
    id INT PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    senha VARCHAR(100)
);
 
CREATE TABLE IF NOT EXISTS Ingredientes (
    id INT PRIMARY KEY,
    nome VARCHAR(100)
);
 
CREATE TABLE IF NOT EXISTS Estoques (
    cozinheiro_id INT,
    ingrediente_id INT,
    quantidade_gramas FLOAT,
    PRIMARY KEY (cozinheiro_id, ingrediente_id),
    FOREIGN KEY (cozinheiro_id) REFERENCES Cozinheiros(id),
    FOREIGN KEY (ingrediente_id) REFERENCES Ingredientes(id)
);
 
INSERT INTO Cozinheiros (id, nome, email, senha) VALUES
(1, 'Soebad Saliv', 'contato@poisonfrit.com', 'Jaca_1234'),
(2, 'Poles Najos', 'poles@g.com', 'DnD13#'),
(3, 'Sani Vosjal', 'sani@g.com', 'Kituti67!');
 
SELECT * FROM Cozinheiros;
 
INSERT INTO Ingredientes (id, nome) VALUES
(1, 'Banana'),
(2, 'Leite'),
(3, 'Ovo'),
(4, 'Açúcar'),
(5, 'Farinha de trigo'),
(6, 'Manteiga'),
(7, 'Baunilha'),
(8, 'Jaca'),
(9, 'Barracuda'),
(10, 'Sal'),
(11, 'Pimenta'),
(12, 'Cebola');
 
SELECT * FROM Ingredientes;
 
INSERT INTO Estoques (cozinheiro_id, ingrediente_id, quantidade_gramas) VALUES
(1, 5, 1854),
(1, 6, 985),
(1, 8, 1520),
(1, 9, 1652),
(1, 10, 566),
(1, 11, 362),
(1, 12, 512),
(2, 1, 124),
(2, 2, 512),
(2, 3, 222),
(2, 4, 356),
(2, 5, 1020),
(2, 6, 558),
(3, 1, 102),
(3, 2, 253),
(3, 5, 152),
(3, 9, 280);
 
UPDATE Estoques
SET quantidade_gramas = quantidade_gramas - 200
WHERE cozinheiro_id = 1 AND ingrediente_id IN (8, 9);
 
UPDATE Estoques
SET quantidade_gramas = 0
WHERE cozinheiro_id = 2 AND ingrediente_id = 2;
 
SELECT * FROM Estoques;
 
CREATE TABLE IF NOT EXISTS Receitas (
    id INT PRIMARY KEY,
    nome VARCHAR(100),
    modo_preparo TEXT
);
 
CREATE TABLE IF NOT EXISTS Composicoes (
    receita_id INT,
    ingrediente_id INT,
    quantidade FLOAT,
    PRIMARY KEY (receita_id, ingrediente_id),
    FOREIGN KEY (receita_id) REFERENCES Receitas(id),
    FOREIGN KEY (ingrediente_id) REFERENCES Ingredientes(id)
);
 
INSERT INTO Receitas (id, nome, modo_preparo) VALUES
(1, 'Bolo de Banana', 'Misture tudo coloque na forma e asse por 30 min.'),
(2, 'Batida de Banana', 'Bata tudo no liquidificador e aproveite!'),
(3, 'Tarte jacquier poisson', 'Cozinhe a jaca em pressão por 20 min. Refogue a barracuda em pedaços pequenos, com a jaca cozida, manteiga, cebola, sal e pimenta. Faça a massa com farinha, manteiga e uma pitada de sal. Forre uma forma com a massa e coloque o recheio. Cubra com outra parte da massa e leve ao forno por 20 min ou até dourar.');
 
INSERT INTO Composicoes (receita_id, ingrediente_id, quantidade) VALUES
(1, 1, 150),
(1, 3, 80),
(1, 4, 100),
(1, 5, 150),
(1, 6, 50),
(2, 1, 100),
(2, 2, 150),
(3, 5, 200),
(3, 6, 100),
(3, 8, 300),
(3, 9, 200),
(3, 10, 10),
(3, 11, 10),
(3, 12, 200);
 
SELECT 
    R.nome AS nome_receita,
    COUNT(C.ingrediente_id) AS quantidade_ingredientes
FROM 
    Receitas R
JOIN 
    Composicoes C ON R.id = C.receita_id
GROUP BY 
    R.id, R.nome
ORDER BY 
    R.nome ASC;