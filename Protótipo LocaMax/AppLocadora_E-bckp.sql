CREATE DATABASE AppLocadora;

CREATE USER 'user'@'localhost' identified by 'locamax@projetouna';
GRANT ALL PRIVILEGES ON applocadora.* TO 'user'@'localhost';
FLUSH PRIVILEGES;

SELECT USER, HOST FROM mysql.USER WHERE USER = 'user';
DROP USER 'user'@'localhost';

/*---------------------------------------------*/

CREATE TABLE `pessoa` (
  `id_pessoa` int(11) NOT NULL AUTO_INCREMENT,
  
  `cpf` varchar(14) DEFAULT NULL UNIQUE,
  `cnh` varchar(11) DEFAULT NULL UNIQUE,
  `nome` varchar(100) DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  `telefone` varchar(14) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `sexo` enum('M','F') DEFAULT NULL,
  PRIMARY KEY (`id_pessoa`)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `endereco` (
  `id_endereco` int(11) NOT NULL AUTO_INCREMENT,
  `id_pessoa` int(11) NOT NULL,
  
  `cep` varchar(9) DEFAULT NULL UNIQUE,
  `pais` varchar(100) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `rua` varchar(50) DEFAULT NULL,
  `numero` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_endereco`),
  FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_pessoa`)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `id_pessoa` int(11) NOT NULL,
  
  `usuario` varchar(100) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  KEY `id_pessoa` (`id_pessoa`),
  FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_pessoa`)
) CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `colaborador` (
  `id_colaborador` int(11) NOT NULL AUTO_INCREMENT,
  `id_pessoa` int(11) NOT NULL,
  
  `matricula` varchar(100) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_colaborador`),
  FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_pessoa`)
) CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `aluguel` (
  `id_aluguel` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `id_carro` int(11) NOT NULL,
  
  `data_retirada` date DEFAULT NULL,
  `data_devolucao` date DEFAULT NULL,
  `hora_retirada` time DEFAULT NULL,
  `hora_devolucao` time DEFAULT NULL,
  
  `valor_contrato` double DEFAULT NULL,
  `forma_de_pagamento` enum('Cartão de Crédito','Cartão Débito','Pix','Transferência','Boleto') DEFAULT NULL,
  PRIMARY KEY (`id_aluguel`),
  FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  FOREIGN KEY (`id_carro`) REFERENCES `carro` (`id_carro`)
) CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `carro` (
  `id_carro` int(11) NOT NULL AUTO_INCREMENT,
  `id_categoria` int(11) NOT NULL,
  `id_modelo` int(11) NOT NULL,
  
  `nome_carro` varchar(100) NULL,
  `placa` varchar(7) DEFAULT NULL UNIQUE,
  `qnt_assentos` tinyint(4) DEFAULT NULL,
  `qnt_portas` tinyint(4) DEFAULT NULL,
  `quilometragem` int(11) DEFAULT NULL,
  
  `tipo_combustivel` enum('Gasolina','Etanol','Diesel','Híbrido','Flex','Elétrico') DEFAULT NULL,
  `potencia_motor` enum('1.0','1.3','1.4','1.5','1.6','1.8','2.0','2.0 - 2.9') DEFAULT NULL,
  `cambio` enum('Manual','Automático','CVT') DEFAULT NULL,
  `capacidade_tanque` int(11) DEFAULT NULL,
  
  `arCondicionado` boolean DEFAULT NULL,
  `airbag` boolean DEFAULT NULL,
  
  `cor` varchar(20) DEFAULT NULL,
  `disponibilidade` enum('Disponível','Alugado','Em manutenção') DEFAULT NULL,
  PRIMARY KEY (`id_carro`),
  FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id_categoria`),
  FOREIGN KEY (`id_modelo`) REFERENCES `modelo` (`id_modelo`)
) CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `modelo` (
  `id_modelo` int(11) NOT NULL AUTO_INCREMENT,
  
  `nome_modelo` varchar(50) DEFAULT NULL,
  `fabricante` varchar(100) DEFAULT NULL,
  `ano_fabricacao` year(4) DEFAULT NULL,
  PRIMARY KEY (`id_modelo`)
) CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `categoria` (
  `id_categoria` int(11) NOT NULL AUTO_INCREMENT,
  
  `nome_categoria` varchar(50) DEFAULT NULL,
  `valor_diaria` double DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*--------------------------------------------------------------*/

ALTER TABLE pessoa MODIFY cpf VARCHAR(14);

ALTER TABLE carro ADD COLUMN nome_carro varchar(100) after id_modelo;

select * from pessoa;
select * from endereco;
select * from cliente;
select * from colaborador;
select * from carro;

select * from modelo;

update modelo set nome_modelo = 'HB20' where id_modelo=1;

delete from pessoa where id_pessoa >=4;
alter table pessoa auto_increment =7;

delete from cliente where id_pessoa =7;
alter table cliente auto_increment =7;

select * from categoria;
select * from modelo;
select * from carro;

delete from categoria where id_categoria >=1;
alter table categoria auto_increment =2;

alter table modelo auto_increment =1;

desc cliente;
desc colaborador;

truncate table cliente;
delete from pessoa where id_pessoa =8;
delete from endereco where id_endereco >=1;
delete from cliente where id_cliente >=1;
delete from colaborador where id_colaborador >=1;

alter table pessoa auto_increment =1;
alter table cliente auto_increment =1;
alter table endereco auto_increment =1;
alter table colaborador auto_increment =1;