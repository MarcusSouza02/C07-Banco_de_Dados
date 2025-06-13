CREATE DATABASE AV1;
USE AV1;

CREATE TABLE Heroi(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    nome_heroi VARCHAR(50) NOT NULL,
    poder VARCHAR(50) NOT NULL,
    idade INT NOT NULL,
    nome_inimigo VARCHAR(50) NOT NULL
);

-- Responda a letra a) aqui
INSERT INTO Heroi (id, nome, nome_heroi, poder, idade, nome_inimigo) VALUES('1', 'Peter Parker', 'Homem Aranha', 'soltar teia', '16', 'Duende Verde');
INSERT INTO Heroi (id, nome, nome_heroi, poder, idade, nome_inimigo) VALUES('2', 'Tony Stark', 'Homem de Ferro', 'tecnologia', '53', 'Mandarim');
INSERT INTO Heroi (id, nome, nome_heroi, poder, idade, nome_inimigo) VALUES('3', 'Steve Rogers', 'Capitão América', 'super força', '105', 'Caveira vermelha');
INSERT INTO Heroi (id, nome, nome_heroi, poder, idade, nome_inimigo) VALUES('4', 'Stephen Strange', 'Doutor Estranho', 'magia', '95', 'Dormammu');
INSERT INTO Heroi (id, nome, nome_heroi, poder, idade, nome_inimigo) VALUES('5', 'Natasha Romanoff', 'Viúva Negra', 'nenhum', '39', 'Bullseye');
INSERT INTO Heroi (id, nome, nome_heroi, poder, idade, nome_inimigo) VALUES('6', 'Wanda Maximoff', 'Feiticeira Escarlate', 'magia', '30', 'Agatha Harkness');
select * from heroi;

-- Respoda a letra b) aqui
select * from Heroi where idade >= 18;

-- Responda a letra c) aqui
select nome from Heroi where idade = (select max(idade) from Heroi);

-- Responda a letra d) aqui
update Heroi set idade = '18' where id = '1';
select nome, idade, poder from Heroi where id = '1';

-- Responda a letra e) aqui
delete from Heroi where id = '5';
select * from Heroi;

-- Responda a letra f) aqui
select nome from Heroi where nome_inimigo = 'Agatha Harkness';

-- Responda a letra g) aqui
update Heroi set poder = 'magia' where id = '4';
select * from Heroi where poder = 'magia' and idade >'50';

-- Responda a letra h) aqui
select * from Heroi where nome_heroi like 'Ho___%';

-- Responda a letra i) aqui
select distinct poder from Heroi;