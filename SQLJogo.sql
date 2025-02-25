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

SELECT * FROM jogador;
ALTER TABLE jogador ADD COLUMN Jogo_id INT;
ALTER TABLE jogador ADD CONSTRAINT fk_jogo FOREIGN KEY (Jogo_id) REFERENCES jogo(id);

