-- MySQL Script generated by MySQL Workbench
-- Tue Jun 14 10:34:36 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema backdonalds
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `backdonalds` ;

-- -----------------------------------------------------
-- Schema backdonalds
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `backdonalds` DEFAULT CHARACTER SET utf8 ;
USE `backdonalds` ;

-- -----------------------------------------------------
-- Table `backdonalds`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backdonalds`.`pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `finalizado` TINYINT NOT NULL DEFAULT 0,
  `retirado` TINYINT NULL DEFAULT 0,
  `pago` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `backdonalds`.`produto_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backdonalds`.`produto_tipo` (
  `id` INT NOT NULL,
  `tipo` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `backdonalds`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backdonalds`.`produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `valor` DOUBLE NULL,
  `descricao` VARCHAR(45) NULL,
  `produto_tipo_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_produto_produto_tipo1_idx` (`produto_tipo_id` ASC) VISIBLE,
  CONSTRAINT `fk_produto_produto_tipo1`
    FOREIGN KEY (`produto_tipo_id`)
    REFERENCES `backdonalds`.`produto_tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `backdonalds`.`ordem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backdonalds`.`ordem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pedido_id` INT NOT NULL,
  `quantidade` INT NOT NULL,
  `produto_id` INT NOT NULL,
  `observacoes` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ordem_pedido_idx` (`pedido_id` ASC) VISIBLE,
  INDEX `fk_ordem_produto1_idx` (`produto_id` ASC) VISIBLE,
  CONSTRAINT `fk_ordem_pedido`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `backdonalds`.`pedido` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ordem_produto1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `backdonalds`.`produto` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `backdonalds`.`adicional`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backdonalds`.`adicional` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `valor` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `backdonalds`.`ordem_has_adicional`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backdonalds`.`ordem_has_adicional` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ordem_id` INT NOT NULL,
  `adicional_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ordem_has_adicional_adicional1_idx` (`adicional_id` ASC) VISIBLE,
  INDEX `fk_ordem_has_adicional_ordem1_idx` (`ordem_id` ASC) VISIBLE,
  CONSTRAINT `fk_ordem_has_adicional_ordem1`
    FOREIGN KEY (`ordem_id`)
    REFERENCES `backdonalds`.`ordem` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ordem_has_adicional_adicional1`
    FOREIGN KEY (`adicional_id`)
    REFERENCES `backdonalds`.`adicional` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `backdonalds`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backdonalds`.`funcionario` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `telefone` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `backdonalds`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backdonalds`.`usuario` (
  `id` INT NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `funcionario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usuario_funcionario1_idx` (`funcionario_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_funcionario1`
    FOREIGN KEY (`funcionario_id`)
    REFERENCES `backdonalds`.`funcionario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=0;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO produto_tipo VALUES(1,'Lanche'),(2,'Bebida'),(3,'Sobremesa');


INSERT INTO produto VALUES (1,'X-Burguer',7.9,'Pão, Hamburguer, Queijo',1);
INSERT INTO produto VALUES (2,'X-Salada',9.9,'Pão, Hamburguer, Queijo, Salada',1);
INSERT INTO produto VALUES (3,'X-Bacon',12.9,'Pão, Hamburguer, Queijo, Bacon, Salada',1);
INSERT INTO produto VALUES (4,'Água sem Gás',4.00,'Garrafa 500ml',2);
INSERT INTO produto VALUES (5,'Água com Gás',4.00,'Garrafa 500ml',2);
INSERT INTO produto VALUES (6,'Coca-Cola',7.00,'Lata 350ml',2);
INSERT INTO produto VALUES (7,'Sprite',7.00,'Lata 350ml',2;
INSERT INTO produto VALUES (8,'Fanta',7.00,'Lata 350ml',2);
INSERT INTO produto VALUES (9,'Torta de maçã',9.00,'150g',3);
INSERT INTO produto VALUES (10,'Sorvete Soft',9.00,'Casquinha',3);


INSERT INTO adicional VALUES (1,'Bacon',2.00);
INSERT INTO adicional VALUES (2,'Ovo',2.00);
INSERT INTO adicional VALUES (3,'Cebola',2.00);
INSERT INTO adicional VALUES (4,'Calabresa',2.00);
INSERT INTO adicional VALUES (5,'Catupiry',2.00);
INSERT INTO adicional VALUES (6,'Batata palha',2.00);

INSERT INTO usuario VALUES (1,'airtong','admin',113),(2,'lrgsouza','admin',134);
INSERT INTO funcionario VALUES (113,'Airton Gabriel de Carvalho','airton.gabriel@ges.inatel.br','+55 35 9899-5465'),(134,'Lucas Ribeiro Garcia de Souza','lrgsouza@ges.inatel.br','+55 19 99344-7675');
