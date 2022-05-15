DROP SCHEMA IF EXISTS kakeibo;
CREATE SCHEMA kakeibo;
USE kakeibo;
SET AUTOCOMMIT=0;

DROP TABLE IF EXISTS `sample_city`;
CREATE TABLE `kakeibo`.`sample_city` (
  `id` INT(7) NOT NULL,
  `zip` VARCHAR(7) NOT NULL,
  `jp_prefecture` VARCHAR(20) NULL,
  `jp_city` VARCHAR(50) NULL,
  `jp_address` VARCHAR(100) NULL,
  `prefecture` VARCHAR(20) NULL,
  `city` VARCHAR(50) NULL,
  `address` VARCHAR(100) NULL,
  PRIMARY KEY (`id`)
);