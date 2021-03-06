-- MySQL Script generated by MySQL Workbench
-- 12/21/16 04:53:17
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema desafio
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema desafio
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `desafio` DEFAULT CHARACTER SET latin1 ;
USE `desafio` ;

-- -----------------------------------------------------
-- Table `desafio`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `desafio`.`user` (
  `iduser` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `question1` VARCHAR(45) NULL DEFAULT NULL,
  `question2` VARCHAR(45) NULL DEFAULT NULL,
  `question3` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = MyISAM
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
