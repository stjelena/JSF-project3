-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 27, 2020 at 08:48 PM
-- Server version: 8.0.21
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `zoo2020`
--
DROP DATABASE IF EXISTS `zoo2020`;
CREATE DATABASE IF NOT EXISTS `zoo2020` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `zoo2020`;

-- --------------------------------------------------------

--
-- Table structure for table `glasovi`
--

DROP TABLE IF EXISTS `glasovi`;
CREATE TABLE IF NOT EXISTS `glasovi` (
  `idG` int NOT NULL AUTO_INCREMENT,
  `idP` int DEFAULT NULL,
  `posetilac` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idG`),
  KEY `idP` (`idP`),
  KEY `posetilac` (`posetilac`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `glasovi`
--

INSERT INTO `glasovi` (`idG`, `idP`, `posetilac`) VALUES
(1, 1, 'jova'),
(2, 1, 'mina'),
(3, 2, 'mina'),
(4, 3, 'mina'),
(5, 3, 'jova'),
(6, 3, 'nikola');

-- --------------------------------------------------------

--
-- Table structure for table `korisnici`
--

DROP TABLE IF EXISTS `korisnici`;
CREATE TABLE IF NOT EXISTS `korisnici` (
  `ime` varchar(45) DEFAULT NULL,
  `prezime` varchar(45) DEFAULT NULL,
  `kor_ime` varchar(45) NOT NULL,
  `lozinka` varchar(45) DEFAULT NULL,
  `tip` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`kor_ime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `korisnici`
--

INSERT INTO `korisnici` (`ime`, `prezime`, `kor_ime`, `lozinka`, `tip`) VALUES
('Ana', 'Tomic', 'ana', 'ana123', 'radnik'),
('Jova', 'Mirkovic', 'jova', 'jova123', 'posetilac'),
('Maja', 'Petrovic', 'maja', 'maja123', 'radnik'),
('Mina', 'Ilic', 'mina', 'mina123', 'posetilac'),
('Nikola', 'Lazic', 'nikola', 'nikola123', 'posetilac');

-- --------------------------------------------------------

--
-- Table structure for table `predlozi`
--

DROP TABLE IF EXISTS `predlozi`;
CREATE TABLE IF NOT EXISTS `predlozi` (
  `idP` int NOT NULL AUTO_INCREMENT,
  `posetilac` varchar(45) DEFAULT NULL,
  `zivotinja` varchar(45) DEFAULT NULL,
  `komentar` varchar(45) DEFAULT NULL,
  `glasovi` int DEFAULT NULL,
  `odobren` int DEFAULT NULL,
  PRIMARY KEY (`idP`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `predlozi`
--

INSERT INTO `predlozi` (`idP`, `posetilac`, `zivotinja`, `komentar`, `glasovi`, `odobren`) VALUES
(1, 'jova', 'slon', 'Africki slon', 2, 0),
(2, 'mina', 'lav', 'Beli lav', 1, 0),
(3, 'mina', 'panter', 'Crni panter', 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `zivotinje`
--

DROP TABLE IF EXISTS `zivotinje`;
CREATE TABLE IF NOT EXISTS `zivotinje` (
  `idZ` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) DEFAULT NULL,
  `tezina` int DEFAULT NULL,
  PRIMARY KEY (`idZ`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `zivotinje`
--

INSERT INTO `zivotinje` (`idZ`, `naziv`, `tezina`) VALUES
(1, 'majmun', 50),
(2, 'veverica', 2),
(3, 'zirafa', 800),
(4, 'mravojed', 40);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `glasovi`
--
ALTER TABLE `glasovi`
  ADD CONSTRAINT `glasovi_ibfk_1` FOREIGN KEY (`idP`) REFERENCES `predlozi` (`idP`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `glasovi_ibfk_2` FOREIGN KEY (`posetilac`) REFERENCES `korisnici` (`kor_ime`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;