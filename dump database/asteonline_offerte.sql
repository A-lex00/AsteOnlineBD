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
-- Table structure for table `offerte`
--

DROP TABLE IF EXISTS `offerte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offerte` (
  `controfferta_massima` int DEFAULT NULL,
  `cliente` varchar(30) NOT NULL,
  `oggetto_in_vendita` int NOT NULL,
  `valore_effettivo` double NOT NULL,
  KEY `responsabile_idx` (`cliente`),
  CONSTRAINT `responsabile` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offerte`
--

LOCK TABLES `offerte` WRITE;
/*!40000 ALTER TABLE `offerte` DISABLE KEYS */;
INSERT INTO `offerte` VALUES (NULL,'mario.rossi',5,810),(820,'micci',5,815),(NULL,'mario.rossi',5,817),(900,'micci',5,818),(NULL,'mario.rossi',5,819);
/*!40000 ALTER TABLE `offerte` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trg_after_insert_controfferta` AFTER INSERT ON `offerte` FOR EACH ROW BEGIN 
    DECLARE v_old_offerente VARCHAR(30);
    DECLARE v_controfferta_max_old_offerente DOUBLE;
    DECLARE v_importo_nuova_controfferta DOUBLE;
    DECLARE v_offerta_massima_attuale_oggetto DOUBLE;
    DECLARE v_prezzo_base_oggetto DOUBLE;
    DECLARE v_durata_oggetto INT;
    DECLARE v_inizio_asta_oggetto DATE;
    DECLARE v_scadenza_oggetto DATE;
    
    -- Ottieni i dettagli dell'oggetto per la nuova offerta
SELECT 
    offerta_massima, prezzo_base, durata, inizio_asta
INTO v_offerta_massima_attuale_oggetto , v_prezzo_base_oggetto , v_durata_oggetto , v_inizio_asta_oggetto FROM
    oggetti_in_asta
WHERE
    codice = NEW.oggetto_in_vendita;

    -- Calcola la data di scadenza dell'asta
    SET v_scadenza_oggetto = DATE_ADD(v_inizio_asta_oggetto, INTERVAL v_durata_oggetto DAY);

    IF (asteonline.controllo_data(v_scadenza_oggetto)) THEN 
        SELECT 
            o.cliente,
            o.controfferta_massima
        INTO
            v_old_offerente,
            v_controfferta_max_old_offerente
        FROM 
            offerte o
        WHERE 
            o.oggetto_in_vendita = NEW.oggetto_in_vendita
            AND o.cliente != NEW.cliente 
            AND o.controfferta_massima IS NOT NULL
            AND o.controfferta_massima > 0
        ORDER BY 
            o.valore_effettivo DESC, o.controfferta_massima DESC 
        LIMIT 1;

        -- Se esiste un precedente offerente con controfferta automatica attiva
        IF v_old_offerente IS NOT NULL THEN
            SET v_importo_nuova_controfferta = NEW.valore_effettivo + 0.50; 

            IF v_importo_nuova_controfferta > v_controfferta_max_old_offerente THEN
                SET v_importo_nuova_controfferta = v_controfferta_max_old_offerente;
            END IF;

            IF (v_importo_nuova_controfferta > NEW.valore_effettivo AND v_importo_nuova_controfferta > v_prezzo_base_oggetto) THEN
                UPDATE oggetti_in_asta
                SET
                    offerta_massima = v_importo_nuova_controfferta,
                    proprietario = v_old_offerente
                WHERE
                    codice = NEW.oggetto_in_vendita;

                INSERT INTO offerte(
                    cliente,
                    oggetto_in_vendita,
                    valore_effettivo,
                    controfferta_massima)
                VALUES(
                    v_old_offerente,
                    NEW.oggetto_in_vendita,
                    v_importo_nuova_controfferta,
                    v_controfferta_max_old_offerente);
            END IF;
        END IF;
    END IF; 
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-09 22:59:02
