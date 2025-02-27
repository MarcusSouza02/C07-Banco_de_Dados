
DROP DATABASE IF EXISTS aula3;
CREATE DATABASE aula3;
USE aula3;

#COMANDO PARA DESATIVAR O SAFE MODE
SET SQL_SAFE_UPDATES = 0;

#QUESTAO 1
CREATE TABLE IF NOT EXISTS usuario(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45),
    endereco VARCHAR(45),
    cargo VARCHAR(45)
);
INSERT INTO usuario (id, nome, endereco, cargo) VALUES('354', 'Marcus', 'Rua 9', 'chefe');
INSERT INTO usuario (id, nome, endereco, cargo) VALUES('3844', 'Maria', 'Rua 8', 'funcionaria');
INSERT INTO usuario (id, nome, endereco, cargo) VALUES('3994', 'Vitoria', 'Rua 10', 'estagiaria');

SELECT * FROM usuario;

UPDATE usuario SET endereco = 'Rua 100' WHERE id = '354';
SELECT * FROM usuario;

DELETE FROM usuario WHERE id = '3994';
SELECT * FROM usuario;

#QUESTAO 2
CREATE TABLE IF NOT EXISTS empresa(
	matricula INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45),
    cpf VARCHAR(45),
    cargo VARCHAR(45),
    nProjetos INT
);

INSERT INTO empresa (matricula, nome, cpf, cargo, nProjetos) VALUES('1', 'Junior', '000.000.000-00', 'GP', '2');
INSERT INTO empresa (matricula, nome, cpf, cargo, nProjetos) VALUES('2', 'Maria', '111.111.111-11', 'Desenvolvedor', '3');
INSERT INTO empresa (matricula, nome, cpf, cargo, nProjetos) VALUES('3', 'Carlos', '222.222.222-22', 'Teste', '4');
INSERT INTO empresa (matricula, nome, cpf, cargo, nProjetos) VALUES('4', 'Julia', '333.333.333-33', 'Desenvolvedor', '5');

SELECT COUNT(*) FROM empresa WHERE nProjetos >= 4;

SELECT AVG(nProjetos) FROM empresa WHERE cargo != 'GP';

select distinct cargo from empresa;

select nome, cpf, nProjetos from empresa where cargo = 'Desenvolvedor';

#RESPONDA AS QUESTOES 3 E 4 AQUI
#Atencao: para a resposta da letra A, preencha a linha da tabela da resposta no comando 'CREATE TABLE' com 'NOT NULL PRIMARY KEY'
#Exemplo: caso você acredite que a chave primária deva ser o campo "ano_nasc" preencha na linha com 'ano_nasc INT NOT NULL PRIMARY KEY'
#QUESTAO 3
CREATE TABLE IF NOT EXISTS aluno (
    matricula INT NOT NULL PRIMARY KEY,
    nome VARCHAR(45),
    endereco VARCHAR(45),
    ano_nasc INT,
    curso VARCHAR(45)
);

# A chave primária deve ser a matrícula pois ela, de forma alguma, se repete.

Insert into aluno(nome, matricula, endereco, ano_nasc, curso) values('Carlos', '333', 'Rua 0', '2003', 'GEB');
Insert into aluno(nome, matricula, endereco, ano_nasc, curso) values('Janaina','123', 'Rua 1', '1998', 'GEB');
Insert into aluno(nome, matricula, endereco, ano_nasc, curso) values('Luca', '354', 'Rua 2', '2004', 'GES');
Insert into aluno(nome, matricula, endereco, ano_nasc, curso) values('Paula', '332', 'Rua 3', '2000', 'GEC');

select * from aluno;

#QUESTÃO 4
Insert into aluno(nome, matricula, endereco, ano_nasc, curso) values('Pedro','225','Rua 25','1999','GEA');
Insert into aluno(nome, matricula, endereco, ano_nasc, curso) values('Jander','109','Rua 10','2000','GES');
Insert into aluno(nome, matricula, endereco, ano_nasc, curso) values('Laura','342','Rua 3','2002','GEC');

select nome, matricula, curso from aluno where nome like 'Jan____';

select nome, curso from aluno where nome like 'L__%a';

select nome, ano_nasc, curso from aluno where ano_nasc <= 2000;

select * from aluno limit 4;

UPDATE aluno SET endereco = 'Rua 100' WHERE curso = 'GES';
select * from aluno where curso = 'GES';

DELETE FROM aluno WHERE curso = 'GES';
select * from aluno;
