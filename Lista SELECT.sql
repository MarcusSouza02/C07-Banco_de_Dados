DROP DATABASE IF EXISTS Herois;
CREATE DATABASE IF NOT EXISTS Herois;
USE Herois;

CREATE TABLE IF NOT EXISTS Heroi (
  idHeroi INT NOT NULL AUTO_INCREMENT,
  Nome VARCHAR(45) NOT NULL,
  endereco VARCHAR(45),
  nascimento date,
  habilidade varchar(45),
  sexo char,
  email varchar(45),
  PRIMARY KEY (idHeroi)
  );
  
create table if not exists Vilao(
	idVilao int not null auto_increment,
    nome varchar(45) not null,
    num_crimes int not null,
    primary key (idVilao)
);

Create table if not exists Missao(
	idMissao int not null auto_increment,
    datas date not null,
    nome varchar(45) not null,
    localizacao varchar(45),
    duracao int,
	primary key (idMissao),
    Vilao_idVilao int,
    constraint fk_missao_vilao
		foreign key (Vilao_idVilao)
        references Vilao(idVilao)
);

create table if not exists Heroi_has_Missao(
	Heroi_idHeroi int not null,
    Missao_idMissao int not null,
    primary key(Heroi_idHeroi, Missao_idMissao),
    constraint fk_Heroi_has_Missao_Heroi
		foreign key (Heroi_idHeroi)
        references Heroi (idHeroi),
	constraint fk_Heroi_has_Missao_Missao
		foreign key (Missao_idMissao)
        references Missao (idMissao)
);

insert into Heroi(idHeroi, nome) values
(1, "Hulk" ),
(2, "Capitão América"),
(3, "Thor"),
(4, "Hulk"),
(5, "Hulk");

update Heroi set nome = "Homem de Ferro" where idHeroi = 4;
update Heroi set nome = "Pantera Negra" where idHeroi = 5;

insert into Vilao(idVilao, nome, num_crimes) values
(1, "Coringa", 47),
(2, "LOKI", 38),
(3, "Thanos", 100),
(4, "Doende Verde", 22),
(5, "Arlequina", 18 );

insert into Missao (datas, nome, localizacao, duracao, Vilao_idVilao) values
('2025-01-15', 'Caos em Gotham', 'Gotham City', 3, 1),         -- Coringa
('2025-02-10', 'Invasão de Asgard', 'Asgard', 5, 2),           -- Loki
('2025-03-01', 'Confronto em Titã', 'Titã', 6, 3),             -- Thanos
('2025-06-18', 'Ataque à Oscorp', 'Nova York', 2, 4),          -- Duende Verde
('2025-04-05', 'Explosão no Carnaval', 'Rio de Janeiro', 4, 5);-- Arlequina


insert into Heroi_has_Missao (Heroi_idHeroi, Missao_idMissao) values
(1, 1),  -- Hulk corina gothan 
(1, 3),  -- Hulk thanos titan
(1, 4),  -- Hulk doende verde Oscorp

(2, 2),  -- Capitão América Loki Asgard
(2, 1),  -- Capitão América coringa Gotham
(2, 5),  -- Capitão América Arlequina Carnaval

(3, 3),  -- Thor Thanos Titã
(3, 2),  -- Thor Loki Asgard
(3, 4),  -- Thor Doende Verde Oscorp 

(4, 4),  -- Homem de Ferro Doende Verde Oscorp 
(4, 3),  -- Homem de Ferro Thanos Titã
(4, 5),  -- Homem de Ferro Arlequina Carnaval

(5, 5),  -- Pantera Negra Arlequina Carnaval
(5, 2),  -- Pantera Negra Loki Asgard
(5, 1);  -- Pantera Negra corina gothan 

SELECT h.Nome AS Nome_Heroi, m.nome AS Nome_Missao, v.nome AS Nome_Vilao
FROM Heroi h
JOIN Heroi_has_Missao hm ON h.idHeroi = hm.Heroi_idHeroi
JOIN Missao m ON hm.Missao_idMissao = m.idMissao
JOIN Vilao v ON m.Vilao_idVilao = v.idVilao
ORDER BY h.Nome;

SELECT h.Nome AS Nome_Heroi, SUM(m.duracao) AS Duracao_Total, COUNT(m.idMissao) AS Quantidade_Missoes
FROM Heroi h
JOIN Heroi_has_Missao hm ON h.idHeroi = hm.Heroi_idHeroi
JOIN Missao m ON hm.Missao_idMissao = m.idMissao
GROUP BY h.idHeroi, h.Nome
ORDER BY h.Nome;

SELECT m.nome AS Nome_Missao, h.Nome AS Nome_Heroi, v.num_crimes AS Crimes_Vilao
FROM Missao m
JOIN Heroi_has_Missao hm ON m.idMissao = hm.Missao_idMissao
JOIN Heroi h ON hm.Heroi_idHeroi = h.idHeroi
JOIN Vilao v ON m.Vilao_idVilao = v.idVilao
ORDER BY m.nome;

