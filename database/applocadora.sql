-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 25/06/2025 às 05:35
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE TABLE `aluguel` (
  `id_aluguel` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_carro` int(11) NOT NULL,
  `data_retirada` date DEFAULT NULL,
  `data_devolucao` date DEFAULT NULL,
  `local_retirada` varchar(100) DEFAULT NULL,
  `local_devolucao` varchar(100) DEFAULT NULL,
  `valor_contrato` double DEFAULT NULL,
  `forma_de_pagamento` enum('Cartão de Crédito','Cartão Débito','Pix','Transferência','Boleto') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `carro` (
  `id_carro` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `id_modelo` int(11) NOT NULL,
  `nome_carro` varchar(99) NOT NULL,
  `placa` varchar(7) DEFAULT NULL,
  `qnt_assentos` tinyint(4) DEFAULT NULL,
  `qnt_portas` tinyint(4) DEFAULT NULL,
  `quilometragem` int(11) DEFAULT NULL,
  `tipo_combustivel` enum('Gasolina','Etanol','Diesel','Híbrido','Flex','Elétrico') DEFAULT NULL,
  `potencia_motor` enum('1.0','1.3','1.4','1.5','1.6','1.8','2.0','2.0 - 2.9') DEFAULT NULL,
  `cambio` enum('Manual','Automático','CVT') DEFAULT NULL,
  `capacidade_tanque` int(11) DEFAULT NULL,
  `arCondicionado` tinyint(1) DEFAULT NULL,
  `airbag` tinyint(1) DEFAULT NULL,
  `cor` varchar(20) DEFAULT NULL,
  `disponibilidade` enum('Disponível','Alugado','Em manutenção') DEFAULT NULL,
  `descricao` varchar(999) NOT NULL,
  `data_retirada` date DEFAULT NULL,
  `data_devolucao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `carro` (`id_carro`, `id_categoria`, `id_modelo`, `nome_carro`, `placa`, `qnt_assentos`, `qnt_portas`, `quilometragem`, `tipo_combustivel`, `potencia_motor`, `cambio`, `capacidade_tanque`, `arCondicionado`, `airbag`, `cor`, `disponibilidade`, `descricao`, `data_retirada`, `data_devolucao`) VALUES
(1, 1, 1, 'Hyundai HB20 - 2015', 'PCM-234', 5, 4, 12650, 'Gasolina', '2.0', 'Automático', 3959, 1, 1, 'Branco', 'Disponível', 'Compacto, moderno e econômico, o HB20 2015 oferece conforto, bom desempenho urbano e baixo consumo de combustível. Ideal para quem busca agilidade no dia a dia com estilo e confiabilidade.', '2025-06-30', '2025-07-29'),
(2, 2, 2, 'Jeep Compass - 2018', 'DJM-352', 5, 4, 598692, 'Diesel', '2.0', 'Manual', 3959, 1, 1, 'Preto', 'Disponível', 'Robusto e sofisticado, o Compass 2018 combina conforto, tecnologia e excelente desempenho tanto na cidade quanto em estradas. Um SUV ideal para quem busca segurança, espaço e versatilidade.', '2025-06-29', '2025-07-10'),
(3, 3, 3, 'Chevrolet Onix', 'ASD-213', 5, 4, 123213, 'Flex', '1.4', 'Automático', 135166, 1, 1, 'Cinza', 'Disponível', 'Chevrolet Onix 2018 — Econômico, confiável e completo. Motor 1.4, ótimo consumo, excelente para uso urbano. Conectividade com MyLink, manutenção barata e excelente custo-benefício.', '2025-06-27', '2025-09-10'),
(4, 4, 4, 'Fiat Toro', 'BOG-123', 5, 4, 64312, 'Flex', '2.0', 'Automático', 64134, 1, 1, 'Preto', 'Disponível', 'Picape intermediária com visual robusto, oferece conforto de SUV e versatilidade para o trabalho, com versões a diesel e motor turbo flex.', '2025-06-28', '2025-09-04'),
(5, 5, 5, 'Renault Duster', 'TEW-231', 5, 4, 34551, 'Flex', '1.6', 'CVT', 5315, 1, 1, 'Cinza', 'Disponível', 'SUV compacto com bom espaço interno e ótima altura do solo, ideal para uso urbano e estradas de terra, com destaque para seu custo acessível.', '2025-06-30', '2025-08-07'),
(6, 6, 6, 'Renault Kwid', 'VDS-123', 5, 4, 4312, 'Flex', '1.0', 'Manual', 123, 1, 1, 'Branco', 'Disponível', 'Subcompacto urbano de baixo consumo e manutenção barata, é um dos carros mais econômicos do país.', '2025-06-30', '2025-07-04'),
(7, 7, 7, 'Volkswagen Voyage\r\n', 'MCO-583', 5, 4, 73164, 'Flex', '1.0', 'Manual', 53153, 1, 1, 'Cinza', 'Disponível', 'Sedan compacto tradicional, derivado do Gol, oferece porta-malas amplo e confiabilidade mecânica, sendo bastante usado por frotistas e motoristas de aplicativo.\r\n', '2025-06-26', '2025-07-10'),
(8, 8, 8, 'Jeep Renegade', 'PLD-231', 5, 4, 35142, 'Flex', '1.8', 'Automático', 5315, 1, 1, 'Preto', 'Disponível', 'SUV com design robusto e boa capacidade off-road nas versões 4x4, oferece bom nível de segurança e tecnologia embarcada.', '2025-07-02', '2025-08-05'),
(9, 9, 9, 'Volkswagen Amarok', 'MNC-253', 5, 4, 73513, 'Diesel', '2.0', 'Automático', 63173, 1, 1, 'Branco', 'Disponível', 'Picape média com pegada mais refinada, motor potente (inclusive V6), bom acabamento e desempenho acima da média na categoria.', '2025-06-27', '2025-06-30'),
(10, 10, 10, 'Hyundai Creta', 'JKD-913', 5, 4, 91732, 'Flex', '1.6', 'Automático', 23135, 1, 1, 'Branco', 'Disponível', 'SUV compacto com visual marcante, interior confortável e equipamentos modernos, sendo um dos preferidos da categoria no Brasil.\r\n', '2025-07-03', '2025-07-16'),
(11, 11, 11, 'Toyota Hilux', 'LSD-314', 5, 4, 53512, 'Diesel', '2.0 - 2.9', 'Manual', 13934, 1, 1, 'Branco', 'Disponível', 'Pickup média reconhecida pela robustez, confiabilidade e alto valor de revenda. Oferece bom desempenho tanto em ambientes urbanos quanto em terrenos off-road, sendo uma das preferidas do agronegócio e serviços pesados.', '2025-07-04', '2025-07-14'),
(12, 12, 12, 'BMW - 530', 'SDW-353', 5, 4, 13515, 'Híbrido', '2.0', 'Automático', 51353, 1, 1, 'Branco', 'Disponível', 'Sedan executivo híbrido plug-in, combina desempenho esportivo com eficiência energética, luxo e tecnologia de ponta.', '2025-07-02', '2026-04-03');

CREATE TABLE `categoria` (
  `id_categoria` int(11) NOT NULL,
  `nome_categoria` varchar(50) DEFAULT NULL,
  `valor_diaria` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `categoria` (`id_categoria`, `nome_categoria`, `valor_diaria`) VALUES
(1, 'Hatch', 212.39),
(2, 'SUV', 230),
(3, 'Hatch', 170),
(4, 'Pickup', 300),
(5, 'SUV', 125.98),
(6, 'Hatch', 112.12),
(7, 'Sedan', 236.3),
(8, 'SUV', 306.96),
(9, 'Pickup', 298.99),
(10, 'SUV', 203.23),
(11, 'Pickup', 280.82),
(12, 'Sedan', 1200.32);

CREATE TABLE `cliente` (
  `id_pessoa` int(11) NOT NULL,
  `usuario` varchar(100) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `cliente` (`id_pessoa`, `usuario`, `senha`, `email`) VALUES
(1, 'admin', '1234', 'admin@gmail.com');

CREATE TABLE `colaborador` (
  `id_pessoa` int(11) NOT NULL,
  `matricula` varchar(100) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `endereco` (
  `id_pessoa` int(11) NOT NULL,
  `cep` varchar(9) DEFAULT NULL,
  `pais` varchar(100) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `rua` varchar(50) DEFAULT NULL,
  `numero` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `endereco` (`id_pessoa`, `cep`, `pais`, `estado`, `cidade`, `bairro`, `rua`, `numero`) VALUES
(1, '12314', 'adm', 'adm', 'adm', 'adm', 'adm', '123');

CREATE TABLE `modelo` (
  `id_modelo` int(11) NOT NULL,
  `nome_modelo` varchar(50) DEFAULT NULL,
  `fabricante` varchar(100) DEFAULT NULL,
  `ano_fabricacao` year(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `modelo` (`id_modelo`, `nome_modelo`, `fabricante`, `ano_fabricacao`) VALUES
(1, 'Hatch', 'Fiat', '1985'),
(2, 'SUV', 'Jeep', '2022'),
(3, 'Hatch', 'Chevrolet', '2018'),
(4, 'Pickup', 'Fiat', '2025'),
(5, 'SUV', 'Renault', '2024'),
(6, 'Hatch', 'Renault', '2021'),
(7, 'Sedan', 'Volkswagen', '2021'),
(8, 'SUV', 'Renegade', '2018'),
(9, 'Pickup', 'Volkswagen', '2018'),
(10, 'SUV', 'Hyundai', '2021'),
(11, 'Pickup', 'Toyota', '2025'),
(12, 'Sedan', 'BMW', '2021');

CREATE TABLE `pessoa` (
  `id_pessoa` int(11) NOT NULL,
  `cpf` varchar(11) DEFAULT NULL,
  `cnh` varchar(11) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  `telefone` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `pessoa` (`id_pessoa`, `cpf`, `cnh`, `nome`, `dataNascimento`, `telefone`) VALUES
(1, '1234', '1234', 'admin', '2025-06-04', '1234');

ALTER TABLE `aluguel`
  ADD PRIMARY KEY (`id_aluguel`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_carro` (`id_carro`);

ALTER TABLE `carro`
  ADD PRIMARY KEY (`id_carro`),
  ADD UNIQUE KEY `placa` (`placa`),
  ADD KEY `id_categoria` (`id_categoria`),
  ADD KEY `id_modelo` (`id_modelo`);

ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id_categoria`);

ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_pessoa`);

ALTER TABLE `colaborador`
  ADD PRIMARY KEY (`id_pessoa`);

ALTER TABLE `endereco`
  ADD PRIMARY KEY (`id_pessoa`),
  ADD UNIQUE KEY `cep` (`cep`);

ALTER TABLE `modelo`
  ADD PRIMARY KEY (`id_modelo`);

ALTER TABLE `pessoa`
  ADD PRIMARY KEY (`id_pessoa`),
  ADD UNIQUE KEY `cpf` (`cpf`),
  ADD UNIQUE KEY `cnh` (`cnh`);

ALTER TABLE `aluguel`
  MODIFY `id_aluguel` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `carro`
  MODIFY `id_carro` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `categoria`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `colaborador`
  MODIFY `id_pessoa` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `endereco`
  MODIFY `id_pessoa` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `modelo`
  MODIFY `id_modelo` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `pessoa`
  MODIFY `id_pessoa` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `aluguel`
  ADD CONSTRAINT `aluguel_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `aluguel_ibfk_2` FOREIGN KEY (`id_carro`) REFERENCES `carro` (`id_carro`);

  /*A CONSTRAINT tava falhando pq a ibfk_1 "id_cliente" estava tentando referenciar a coluna "id_cliente" na tabela cliente, e ela não existe já
  que passou a ser "id_pessoa". Comando que utilizei abaixo pra estar adicionando-as:*/
  
  ALTER TABLE aluguel
	ADD CONSTRAINT `aluguel_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES cliente (`id_pessoa`);
    
  ALTER TABLE aluguel
	ADD CONSTRAINT `aluguel_ibfk_2` FOREIGN KEY (`id_carro`) REFERENCES carro (`id_carro`);

ALTER TABLE `carro`
  ADD CONSTRAINT `carro_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id_categoria`),
  ADD CONSTRAINT `carro_ibfk_2` FOREIGN KEY (`id_modelo`) REFERENCES `modelo` (`id_modelo`);

ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_pessoa`);

ALTER TABLE `colaborador`
  ADD CONSTRAINT `colaborador_ibfk_1` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_pessoa`);

ALTER TABLE `endereco`
  ADD CONSTRAINT `endereco_ibfk_1` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_pessoa`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
