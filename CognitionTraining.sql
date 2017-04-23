-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema CognitionTraining
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CognitionTraining
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CognitionTraining` DEFAULT CHARACTER SET utf8 ;
USE `CognitionTraining` ;

-- -----------------------------------------------------
-- Table `CognitionTraining`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CognitionTraining`.`role` (
  `role_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CognitionTraining`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CognitionTraining`.`user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CognitionTraining`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CognitionTraining`.`user_role` (
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_user_role_roleid_idx` (`role_id` ASC),
  CONSTRAINT `fk_user_role_roleid`
    FOREIGN KEY (`role_id`)
    REFERENCES `CognitionTraining`.`role` (`role_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_userid`
    FOREIGN KEY (`user_id`)
    REFERENCES `CognitionTraining`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
