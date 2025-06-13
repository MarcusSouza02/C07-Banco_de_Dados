DROP DATABASE IF EXISTS exercicio_procedure_function;
CREATE DATABASE exercicio_procedure_function;
USE exercicio_procedure_function;

CREATE TABLE Aluno(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50),
    idade INT,
    curso VARCHAR(3),
    email VARCHAR(100)
);

DELIMITER $$
CREATE FUNCTION criaEmailAluno(nome VARCHAR(100), curso VARCHAR(10))
RETURNS VARCHAR(255)
DETERMINISTIC
BEGIN
    DECLARE email VARCHAR(255);
    SET email = CONCAT(nome, '@', curso, '.inatel.br');
    RETURN email;
END $$
DELIMITER;

DELIMITER $$
CREATE PROCEDURE insereAluno()
BEGIN
    INSERT INTO Aluno (id, nome, idade, curso, email) VALUES
    (1, 'Joao', 20, 'ges', criaEmailAluno('Joao', 'ges')),
    (2, 'Maria', 21, 'gec', criaEmailAluno('Maria', 'gec')),
    (3, 'Jose', 18, 'geb', criaEmailAluno('Jose', 'geb')),
    (4, 'Ana', 19, 'gec', criaEmailAluno('Ana', 'gec'));
END $$
DELIMITER ;

CALL insereAluno();

SELECT * FROM Aluno;

