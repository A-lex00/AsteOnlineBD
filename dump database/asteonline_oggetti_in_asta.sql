-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: asteonline
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `oggetti_in_asta`
--

DROP TABLE IF EXISTS `oggetti_in_asta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oggetti_in_asta` (
  `codice` int NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(100) NOT NULL,
  `prezzo_base` double NOT NULL,
  `Altezza` double NOT NULL,
  `Lunghezza` double NOT NULL,
  `Larghezza` double NOT NULL,
  `stato` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `proprietario` varchar(30) DEFAULT NULL,
  `offerta_massima` double DEFAULT NULL,
  `inizio_asta` datetime NOT NULL,
  `durata` int NOT NULL,
  `stato_asta` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`codice`),
  KEY `categoria_idx` (`categoria`),
  KEY `amministratore_idx` (`offerta_massima`),
  KEY `proprietà_idx` (`proprietario`),
  CONSTRAINT `categoria` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`nome_categoria`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `proprietà` FOREIGN KEY (`proprietario`) REFERENCES `cliente` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oggetti_in_asta`
--

LOCK TABLES `oggetti_in_asta` WRITE;
/*!40000 ALTER TABLE `oggetti_in_asta` DISABLE KEYS */;
INSERT INTO `oggetti_in_asta` VALUES (5,'Smartphone ultimo modello',800,0.15,0.08,0.01,'Ottime condizioni','Android','mario.rossi',819,'2025-06-07 10:00:00',7,'aperta'),(6,'Orologio classico svizzero',1200,0.04,0.04,0.01,'Ottime condizioni','Orologi da Polso',NULL,NULL,'2025-06-10 09:00:00',10,'aperta');
/*!40000 ALTER TABLE `oggetti_in_asta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-09 22:59:02
