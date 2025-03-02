-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dbjogo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dbjogo
-- -----------------------------------------------------
CREATE DATABASE dbjogo;
CREATE SCHEMA IF NOT EXISTS `dbjogo` DEFAULT CHARACTER SET utf8 ;
USE `dbjogo` ;

-- -----------------------------------------------------
-- Table `dbjogo`.`Jogo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbjogo`.`Jogo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  `nota` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbjogo`.`Jogador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbjogo`.`Jogador` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(45) NOT NULL,
  `vitorias` INT NOT NULL,
  `derrotas` INT NOT NULL,
  `elo` VARCHAR(45) NOT NULL,
  `Jogo_id` INT NOT NULL,
  PRIMARY KEY (`id`),  -- Mantém apenas `id` como chave primária
  UNIQUE INDEX `usuario_UNIQUE` (`usuario` ASC) VISIBLE,
  INDEX `fk_Jogador_Jogo_idx` (`Jogo_id` ASC) VISIBLE,
  CONSTRAINT `fk_Jogador_Jogo`
    FOREIGN KEY (`Jogo_id`)
    REFERENCES `dbjogo`.`Jogo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

SELECT * FROM jogo;
INSERT INTO jogo (nome, categoria, nota) VALUES ("Free Fire", "Battle Royale", 2);
INSERT INTO jogo (nome, categoria, nota) VALUES ("Among Us", "Sobrevivencia", 1);
INSERT INTO jogo (nome, categoria, nota) VALUES ("LOL", "MOBA", 1);
INSERT INTO jogo (nome, categoria, nota) VALUES ("Roblox", "Variedade", 3);

SELECT * FROM jogador;
INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_id) VALUES ("Laysie", 43, 22, "Ouro", 1);
INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_id) VALUES ("Ceuzan", 31, 5, "Platina", 1);
INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_id) VALUES ("Briril", 234, 75, "Diamante", 1);

INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_id) VALUES ("Bauska", 44, 5, "Platina", 2);
INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_id) VALUES ("Haikus", 78, 3, "Mestre", 2);
INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_id) VALUES ("Facair", 12, 1, "Bronze", 2);

INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_id) VALUES ("tatizinha", 95, 11, "Diamante", 16);
INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_id) VALUES ("filtz", 50, 3, "Mestre", 16);
INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_id) VALUES ("marcos_007", 45, 25, "Bronze", 16);
INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_id) VALUES ("nino", 32, 9, "Ouro", 16);

INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_id) VALUES ("marcelin", 37, 10, "Ouro", 17);
INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_id) VALUES ("larax", 54, 17, "Platina", 17);
INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_id) VALUES ("rian", 10, 2, "Bronze", 17);
INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_id) VALUES ("sakura", 90, 10, "Diamante", 17);

ALTER TABLE jogador ADD COLUMN Jogo_id INT;
ALTER TABLE jogador ADD CONSTRAINT fk_jogo FOREIGN KEY (Jogo_id) REFERENCES jogo(id);

