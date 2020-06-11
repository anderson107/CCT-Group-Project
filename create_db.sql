DROP SCHEMA IF EXISTS `spar_database`;

CREATE SCHEMA `spar_database`;

use `spar_database`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `spar_database`.`administrator` (`id`, `login`, `password`)
VALUES ('1', 'Administrator', 'Administrator');

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `dob` date NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `telephone` varchar(10) default NULL,
  `address` varchar(250) NOT NULL,
  `city` varchar(40) NOT NULL,
  `registration_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `firewarden`;

CREATE TABLE `firewarden` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_description` varchar(250) NOT NULL,
  `frequency` varchar(45) NOT NULL,
  `status` varchar(20) NOT NULL,
  `creation_date` date NOT NULL,
  `date` date,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `maintenance`;

CREATE TABLE `maintenance`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(50) NOT NULL,
  `description` varchar(150) NOT NULL,
  `creation_date` date NOT NULL,
  `last_date` date,
  `next_date` date,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `hs`;

CREATE TABLE `hs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_description` varchar(250) NOT NULL,
  `frequency` varchar(45) NOT NULL,
  `status` varchar(20) NOT NULL,
  `creation_date` date NOT NULL,
  `date` date,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

ALTER TABLE hs AUTO_INCREMENT = 50;

CREATE TABLE `deli_haccp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_description` varchar(250) NOT NULL,
  `frequency` varchar(45) NOT NULL,
  `status` varchar(20) NOT NULL,
  `creation_date` date NOT NULL,
  `date` date,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

ALTER TABLE deli_haccp AUTO_INCREMENT = 100;

CREATE TABLE `floor_haccp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_description` varchar(250) NOT NULL,
  `frequency` varchar(45) NOT NULL,
  `status` varchar(20) NOT NULL,
  `creation_date` date NOT NULL,
  `date` date,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

ALTER TABLE floor_haccp AUTO_INCREMENT = 150;

CREATE TABLE `coffee_haccp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_description` varchar(250) NOT NULL,
  `frequency` varchar(45) NOT NULL,
  `status` varchar(20) NOT NULL,
  `creation_date` date NOT NULL,
  `date` date,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

ALTER TABLE coffee_haccp AUTO_INCREMENT = 200;

DROP TABLE IF EXISTS `Task`;

CREATE TABLE `Task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_description` varchar(250) NOT NULL,
  `status` varchar(20) NOT NULL,
  `creation_date` date NOT NULL,
  `date` date,
  `action_description` varchar(300),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `seachange`;

CREATE TABLE `seachange` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `training` varchar(250) NOT NULL,
  `creation_date` date NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `virtual_academy`;

CREATE TABLE `virtual_academy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `training` varchar(250) NOT NULL,
  `creation_date` date NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

ALTER TABLE virtual_academy AUTO_INCREMENT = 20;

DROP TABLE IF EXISTS `hse`;

CREATE TABLE `hse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `training` varchar(250) NOT NULL,
  `creation_date` date NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

ALTER TABLE hse AUTO_INCREMENT = 40;

DROP TABLE IF EXISTS `emp_seachange_training`;

CREATE TABLE `emp_seachange_training` (
  `employee_id` int(11) NOT NULL,
  `training_id` int(11) NOT NULL,
  `status` varchar(50) NOT NULL,	
  `date` date,
  PRIMARY KEY(`employee_id`,`training_id`),
  KEY `FK_SEACHANGE_idx` (`training_id`),
  
  CONSTRAINT `FK_EMPLOYEE_SEACHANGE` FOREIGN KEY (`employee_id`) 
  REFERENCES `employee` (`id`) 
  ON DELETE CASCADE,
  
  CONSTRAINT `FK_SEACHANGE` FOREIGN KEY (`training_id`) 
  REFERENCES `seachange` (`id`) 
  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `emp_virtual_training`;

CREATE TABLE `emp_virtual_training` (
  `employee_id` int(11) NOT NULL,
  `training_id` int(11) NOT NULL,
  `status` varchar(50) NOT NULL,	
  `date` date,
  PRIMARY KEY(`employee_id`,`training_id`),  
  KEY `FK_VIRTUAL_idx` (`training_id`),
  
  CONSTRAINT `FK_EMPLOYEE_VIRTUAL` FOREIGN KEY (`employee_id`) 
  REFERENCES `employee` (`id`) 
  ON DELETE CASCADE,
  
  CONSTRAINT `FK_VIRTUAL` FOREIGN KEY (`training_id`) 
  REFERENCES `virtual_academy` (`id`) 
  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `emp_hse_training`;

CREATE TABLE `emp_hse_training` (
  `employee_id` int(11) NOT NULL,
  `training_id` int(11) NOT NULL,
  `status` varchar(50) NOT NULL,	
  `date` date,
  PRIMARY KEY(`employee_id`,`training_id`), 
  KEY `FK_HSE_idx` (`training_id`),
  
  CONSTRAINT `FK_EMPLOYEE_HSE` FOREIGN KEY (`employee_id`) 
  REFERENCES `employee` (`id`) 
  ON DELETE CASCADE,
  
  CONSTRAINT `FK_HSE` FOREIGN KEY (`training_id`) 
  REFERENCES `hse` (`id`) 
  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;