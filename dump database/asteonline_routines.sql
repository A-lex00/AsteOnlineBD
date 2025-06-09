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
-- Dumping events for database 'asteonline'
--

--
-- Dumping routines for database 'asteonline'
--
/*!50003 DROP FUNCTION IF EXISTS `controllo_CAP` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `controllo_CAP`(
  var_cap varchar(10)
) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
	if var_cap regexp '[0-9]{5}$' then
		return true;
	else
		return false;
	end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `controllo_carta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `controllo_carta`(
	var_numero varchar(30)
) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
IF var_numero regexp '^[0-9]{13,19}$' THEN
	RETURN true;
ELSE 
	RETURN false;
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `controllo_CF` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `controllo_CF`(
	var_cf VARCHAR(16)
) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
IF var_cf REGEXP '^[A-Z]{6}[0-9LMNPQRSTUV]{2}[A-Z]{1}[0-9LMNPQRSTUV]{2}[A-Z]{1}[0-9LMNPQRSTUV]{3}[A-Z]{1}$' THEN
	RETURN true;
ELSE
	RETURN false;
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `controllo_CVV` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `controllo_CVV`(
	var_cvv VARCHAR(4)
) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
IF var_cvv regexp '^[0-9]{3,4}$' THEN
	RETURN true;
ELSE
	RETURN false;
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `controllo_data` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `controllo_data`(
	 var_data date
) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
#dichiarazione di una variabile per il risultato 
DECLARE flag BOOLEAN;
IF var_data > CURDATE() THEN
	return true;
ELSE
	return false;
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `controllo_username` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `controllo_username`(
	var_username varchar(30),
    var_role varchar(16)
) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
	if (var_role = 'cliente') then
		if not exists(
			select username
			from cliente
            where username = var_username
            ) then
				return true; #metto false poichè nella chiamata uso la logica invertita
		else
			return false; 
	 end if;
	if(var_role = 'amministratore') then
			if not exists(
				select username_admin
				from amministratore
				where username_admin = var_username
            ) then
				return false; #metto false poichè nella chiamata uso la logica invertita
			else
				return true; 
			end if;
	end if;
end if;
return true;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `aggiungi_oggetto_in_asta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `aggiungi_oggetto_in_asta`(
	in var_inizio_asta datetime,
    in var_durata  int,
    IN var_descrizione VARCHAR(100),
    IN var_prezzo_base DOUBLE UNSIGNED,
    IN var_altezza DOUBLE,
    IN var_lunghezza DOUBLE,
    IN var_larghezza DOUBLE,
    IN var_stato VARCHAR(45),
    IN var_categoria VARCHAR(45),
    IN var_proprietario VARCHAR(16),
    IN var_offerta_massima DOUBLE
)
BEGIN
   DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
	set transaction isolation level read uncommitted;
	start transaction;

if not (asteonline.controllo_data(var_inizio_asta)) then
	signal sqlstate '45013' set message_text = 'data inizio asta non valida!';
end if;
INSERT INTO oggetti_in_asta (
            descrizione,
            prezzo_base,
            altezza,
            lunghezza,
            larghezza,
            stato,
            categoria,
            inizio_asta,
            durata,
            stato_asta
			) VALUES (
            var_descrizione,
            var_prezzo_base,
            var_altezza,
            var_lunghezza,
            var_larghezza,
            var_stato,
            var_categoria,
            var_inizio_asta,
            var_durata,
            'aperta'
);
commit;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `crea_categoria_con_macrocategoria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `crea_categoria_con_macrocategoria`(
		var_nome_categoria varchar(45),
        var_nome_macrocategoria varchar(45)
)
BEGIN

	 declare exit handler for sqlexception 
     begin
		 rollback;
         resignal;
	 end;

	 set transaction isolation level repeatable read;  #VALUTARE
	 start transaction;
	
     insert into categoria(nome_categoria,categoria_superiore) values ( var_nome_categoria,var_nome_macrocategoria);
	 commit;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `crea_categoria_senza_macrocategoria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `crea_categoria_senza_macrocategoria`(
	var_nome_categoria VARCHAR(45)
)
BEGIN

	declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;

	set transaction isolation level read uncommitted;
	start transaction;
		
        insert into categoria (nome_categoria) values (var_nome_categoria);
	commit;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `fai_offerta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `fai_offerta`(
    in var_username VARCHAR(30),
    in var_importo DOUBLE,
    in var_codice_oggetto INT,
    in var_controfferta_automatica DOUBLE
)
BEGIN
    DECLARE var_scadenza DATE;
    DECLARE var_inizio_asta DATE;
    DECLARE var_durata INT;
    DECLARE var_prezzo_base DOUBLE;
    DECLARE var_offerta_massima_attuale DOUBLE;
    DECLARE var_proprietario_attuale VARCHAR(30); -- Rinominato per chiarezza, era var_precedente_offerente
    
    -- Gestore di errore per qualsiasi eccezione SQL: esegue un ROLLBACK e ri-segnala l'errore
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    -- Imposta il livello di isolamento a SERIALIZABLE per prevenire race conditions
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    -- Inizia la transazione
    START TRANSACTION;
    
    -- Seleziona i dettagli dell'oggetto in asta, bloccando la riga con FOR UPDATE
    -- Questo previene che altri utenti modifichino l'oggetto mentre questa transazione è in corso
    SELECT
        prezzo_base,
        inizio_asta,
        durata,
        offerta_massima,
        proprietario 
    INTO 
        var_prezzo_base,
        var_inizio_asta,
        var_durata,
        var_offerta_massima_attuale,
        var_proprietario_attuale
    FROM
        oggetti_in_asta
    WHERE
        codice = var_codice_oggetto
    FOR UPDATE; -- AGGIUNTO: Blocca la riga selezionata

    -- Controllo granularità dell'importo (multiplo di 0.50)
    IF (var_importo * 100) % 50 != 0 THEN
        SIGNAL SQLSTATE '45110' SET MESSAGE_TEXT = 'Granularità importo non rispettata. L''importo deve essere un multiplo di 0.50.';
    END IF;
        
    -- Controllo esistenza oggetto
    IF var_inizio_asta IS NULL THEN
        SIGNAL SQLSTATE '45016' SET MESSAGE_TEXT = 'Oggetto non trovato.';
    END IF;
        
    -- Calcolo scadenza e controllo validità asta (data corrente vs scadenza)
    SET var_scadenza = DATE_ADD(var_inizio_asta, INTERVAL var_durata DAY);
    IF NOT (asteonline.controllo_data(var_scadenza)) THEN -- Assumo che questa funzione controlli la validità dell'asta
        SIGNAL SQLSTATE '45015' SET MESSAGE_TEXT = 'Asta non valida o scaduta.';
    END IF;
        
    -- Controllo carta di credito dell'utente
    IF (asteonline.controllo_carta(var_username)) THEN -- Assumo che questa funzione ritorni TRUE se la carta NON è valida
        SIGNAL SQLSTATE '45005' SET MESSAGE_TEXT = 'Carta di credito non valida per questo utente.';
    END IF;
        
    -- Controllo che l'utente non stia facendo un'offerta sul proprio oggetto o la stessa offerta massima
    IF (var_proprietario_attuale = var_username) THEN
        SIGNAL SQLSTATE '45111' SET MESSAGE_TEXT = 'Sei già il miglior offerente. Se vuoi aumentare, effettua una nuova offerta superiore.';
    END IF;
        

    -- Caso 1: È la prima offerta per questo oggetto (offerta_massima_attuale è NULL)
    IF var_offerta_massima_attuale IS NULL THEN
        IF var_importo > var_prezzo_base THEN
      
            UPDATE 
                oggetti_in_asta
            SET
                offerta_massima = var_importo,
                proprietario = var_username
            WHERE
                codice = var_codice_oggetto;
            

            INSERT INTO offerte(
                cliente,
                oggetto_in_vendita,
                valore_effettivo,
                controfferta_massima)
            VALUES(
                var_username,
                var_codice_oggetto,
                var_importo,
                var_controfferta_automatica);
        ELSE
            SIGNAL SQLSTATE '45100' SET MESSAGE_TEXT = 'Importo non sufficiente. Deve essere maggiore del prezzo base.';
        END IF;
    -- Caso 2: Ci sono già offerte per l'oggetto (offerta_massima_attuale NON è NULL)
    ELSE 
        IF (var_importo > var_offerta_massima_attuale) THEN
  
            UPDATE oggetti_in_asta
            SET 
                offerta_massima = var_importo,
                proprietario = var_username
            WHERE
                codice = var_codice_oggetto; 
                            
     
            INSERT INTO offerte(
                cliente,
                oggetto_in_vendita,
                valore_effettivo,
                controfferta_massima)
            VALUES(
                var_username,
                var_codice_oggetto,
                var_importo,
                var_controfferta_automatica);
        ELSE
            SIGNAL SQLSTATE '45100' SET MESSAGE_TEXT = 'Importo non sufficiente. Devi superare l''offerta massima attuale.';
        END IF;
    END IF; 

    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `login` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `login`(
	in var_username VARCHAR(30),
    in var_password VARCHAR(80),
    in var_click_role VARCHAR(15), -- Il ruolo selezionato dall'utente (es. 'cliente', 'amministratore')
    out var_role VARCHAR(15)        -- Il ruolo dell'utente loggato (se il login ha successo)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
		RESIGNAL; -- Rilancia l'errore originale
    END;

    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
    START TRANSACTION;
    
    SET var_role = NULL; 

    #Logica per il LOGIN DEL CLIENTE
    IF var_click_role = 'cliente' THEN
     SET var_role = 'cliente';
        IF NOT (asteonline.controllo_username(var_username, var_click_role) AND EXISTS (
            SELECT 1 FROM cliente WHERE username = var_username AND password_cliente = MD5(var_password)
        )) THEN
            #Se l'username non esiste o la password non corrisponde, lancia un errore.
            SIGNAL SQLSTATE '45007' SET MESSAGE_TEXT = 'utente non riconosciuto.';
       END IF;
       
    -- Logica per il LOGIN AMMINISTRATORE
    ELSE
        IF NOT (asteonline.controllo_username(var_username, var_click_role) AND EXISTS (
            SELECT 1 FROM amministratore WHERE username_admin = var_username AND password_admin = MD5(var_password)
        )) THEN
            -- Se l'username non esiste o la password non corrisponde, lancia un errore.
            SIGNAL SQLSTATE '45008' SET MESSAGE_TEXT = 'amministratore non riconosciuto.';
        ELSE
            SET var_role = 'amministratore';
        END IF;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registrazione` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrazione`(
    in var_nome varchar(45),
    in var_cognome varchar(45),
    in var_CF varchar(16),
    in var_data_nascita date,
    in var_città_nascita varchar(50),
    in var_password varchar(80),
    in var_numero_carta varchar(20),
	in var_CVV varchar(4),
    in var_data_scadenza date,
    in var_via varchar(100),
    in var_città varchar(100),
    in var_CAP varchar(10),
    in var_username varchar(30),
    in var_civico varchar(5)
)
BEGIN
declare select_role varchar(16);
declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;
	
	set transaction isolation level read uncommitted;
	start transaction;
    set select_role = 'cliente' ; 
    
    
    if not(asteonline.controllo_username(var_username, select_role)) then
		signal sqlstate '45008' set message_text = 'username già esistente.';
	end if;
    
	if not (asteonline.controllo_CF(var_CF)) then
		signal sqlstate '45001' set message_text = 'Il codice fiscale inserito non è valido.';
	end if;
    
    if not (asteonline.controllo_carta(var_numero_carta)) then
		signal sqlstate '45002' set message_text = "Il numero della carta di credito non è valido.";
     end if;   
     
     if not (asteonline.controllo_CVV(var_CVV)) then
		signal sqlstate '45003' set message_text = "Il codice CVV inserito non è valido.";
	end if;
    
    if not (asteonline.controllo_CAP(var_CAP)) then
		signal sqlstate '45004' set message_text = "Il CAP inserito non è valido.";
	end if;
    
    if not(asteonline.controllo_data(var_data_scadenza)) then
		signal sqlstate '45005' set message_text = 'la  carta di credito risulta scaduta.';
	end if;

	insert into cliente 
    (CAP,CF,città,città_nascita,civico,cognome,
    CVV,data_nascita,data_scadenza,nome,numero_carta,
    password_cliente,username,via)
    values (var_CAP,var_CF,var_città,var_città_nascita,var_civico,var_cognome,
    var_CVV,var_data_nascita,var_data_scadenza,var_nome,var_numero_carta,
    md5(var_password),var_username,var_via);
    
    commit;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registrazione_amministratore` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrazione_amministratore`(
	var_username varchar(30),
    var_password varchar(45)
)
BEGIN
declare select_role varchar(16);
declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;
set select_role = 'amministratore';
set transaction isolation level read uncommitted;
start transaction;

if not(asteonline.controllo_username(var_username,select_role)) then
	signal sqlstate '45008' set message_text = 'username già esistente.';
	end if;
insert into amministratore(username_admin,password_admin) VALUES
	(var_username,md5(var_password));

commit;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `visualizza_asta_in_corso` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `visualizza_asta_in_corso`(
    IN var_utente VARCHAR(30) -- Parametro per l'utente che ha fatto l'offerta
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
    START TRANSACTION;

    SELECT
        asta.codice,         
        asta.descrizione,     
        asta.inizio_asta,      
        asta.durata,           
        asta.prezzo_base,      
        asta.offerta_massima,  
        asta.stato,            
        CONCAT(
            'Scadenza il ',
            DATE_FORMAT((asta.inizio_asta + INTERVAL asta.durata DAY), '%d/%m'), -- Formatta la data di scadenza (es. 01/01)
            ' alle ',
            DATE_FORMAT((asta.inizio_asta + INTERVAL asta.durata DAY), '%H:%i'), -- Formatta l'ora di scadenza (es. 21:20)
            ' quindi mancano ',
            -- Calcola e formatta i giorni rimanenti
            CASE
                WHEN TIMESTAMPDIFF(DAY, NOW(), (asta.inizio_asta + INTERVAL asta.durata DAY)) > 0
                THEN CONCAT(TIMESTAMPDIFF(DAY, NOW(), (asta.inizio_asta + INTERVAL asta.durata DAY)), ' giorno')
            END,
            -- Calcola e formatta le ore rimanenti (modulo 24 per escludere i giorni completi)
            CASE
                WHEN MOD(TIMESTAMPDIFF(HOUR, NOW(), (asta.inizio_asta + INTERVAL asta.durata DAY)), 24) > 0
                THEN CONCAT(MOD(TIMESTAMPDIFF(HOUR, NOW(), (asta.inizio_asta + INTERVAL asta.durata DAY)), 24), ' ora')
            END,
            -- Calcola e formatta i minuti rimanenti (modulo 60 per escludere le ore complete)
            CASE
                WHEN MOD(TIMESTAMPDIFF(MINUTE, NOW(), (asta.inizio_asta + INTERVAL asta.durata DAY)), 60) > 0
                THEN CONCAT(MOD(TIMESTAMPDIFF(MINUTE, NOW(), (asta.inizio_asta + INTERVAL asta.durata DAY)), 60), ' minuto')
            END
        ) AS scadenza_e_tempo_residuo,
        (SELECT COUNT(*) FROM Offerte WHERE oggetto_in_vendita = asta.codice) AS NumeroOfferte
    FROM
        oggetti_in_asta AS asta 
    INNER JOIN
        offerte AS user_off ON asta.codice = user_off.oggetto_in_vendita -- Unisce con le offerte
                            AND user_off.cliente = var_utente -- Filtra per le offerte fatte dall'utente specificato
    WHERE
        (asta.inizio_asta + INTERVAL asta.durata DAY) > NOW() -- Filtra solo le aste non scadute (ancora in corso)
    GROUP BY
        asta.codice, asta.descrizione, asta.inizio_asta, asta.durata, 
        asta.prezzo_base, asta.offerta_massima, asta.stato
    ORDER BY
        asta.inizio_asta; -- Ordina i risultati per la data di inizio dell'asta
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `visualizza_aste_aperte` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `visualizza_aste_aperte`()
BEGIN
	
		declare exit handler for sqlexception
		begin
			rollback;
			resignal;
		end;

		set transaction isolation level read uncommitted;
		start transaction;
        
		SELECT
			codice,
			descrizione,
			prezzo_base,
			Altezza,
			Lunghezza,
			Larghezza,
			stato,
			categoria,
			offerta_massima,
            stato_asta,
			CONCAT(
				'Scadenza il ', 
				DATE_FORMAT((inizio_asta + INTERVAL durata DAY), '%d/%m'),
				' alle ', 
				DATE_FORMAT((inizio_asta + INTERVAL durata DAY), '%H:%i'), -- Formatta l'ora di chiusura (es. 21:20)
				' quindi mancano  ',
				-- Calcola i giorni rimanenti
				CASE
					WHEN TIMESTAMPDIFF(DAY, NOW(), (inizio_asta + INTERVAL durata DAY)) > 0
					THEN CONCAT(TIMESTAMPDIFF(DAY, NOW(), (inizio_asta + INTERVAL durata DAY)), ' giorni', 
								IF(TIMESTAMPDIFF(DAY, NOW(), (inizio_asta + INTERVAL durata DAY)) > 1, '', ''), ', ')
					ELSE ''
				END,
				-- Calcola le ore rimanenti (mod 24 per non includere i giorni completi)
				CASE
					WHEN MOD(TIMESTAMPDIFF(HOUR, NOW(), (inizio_asta + INTERVAL durata DAY)), 24) > 0
					THEN CONCAT(MOD(TIMESTAMPDIFF(HOUR, NOW(), (inizio_asta + INTERVAL durata DAY)), 24), ' ore',
								IF(MOD(TIMESTAMPDIFF(HOUR, NOW(), (inizio_asta + INTERVAL durata DAY)), 24) > 1, '', ''), 
								IF(MOD(TIMESTAMPDIFF(MINUTE, NOW(), (inizio_asta + INTERVAL durata DAY)), 60) > 0, ' e ', '')) -- Aggiunge ' e ' solo se ci sono anche i minuti
					ELSE ''
				END,
				-- Calcola i minuti rimanenti (mod 60 per non includere ore complete)
				CASE
					WHEN MOD(TIMESTAMPDIFF(MINUTE, NOW(), (inizio_asta + INTERVAL durata DAY)), 60) > 0
					THEN CONCAT(MOD(TIMESTAMPDIFF(MINUTE, NOW(), (inizio_asta + INTERVAL durata DAY)), 60), ' minuti',
								IF(MOD(TIMESTAMPDIFF(MINUTE, NOW(), (inizio_asta + INTERVAL durata DAY)), 60) > 1, '', ''))
					ELSE ''
				END
			) AS scadenza_e_tempo_residuo,
            (select count(*) from offerte where codice= oggetto_in_vendita) as numero_offerte
FROM
    oggetti_in_asta
WHERE
    NOW() < (inizio_asta + INTERVAL durata DAY) AND(stato_asta = 'aperta');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `visualizza_oggetti_aggiudicati` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `visualizza_oggetti_aggiudicati`(
	var_utente VARCHAR(30)
)
BEGIN

   declare exit handler for sqlexception 
    begin
		rollback;
        resignal;
	end;
    
    set transaction isolation level read uncommitted;
    start transaction;

SELECT  descrizione,offerta_massima as prezzo_finale,
		Altezza,Lunghezza,Larghezza
FROM oggetti_in_asta
where oggetti_in_asta.proprietario = var_utente;

END ;;
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
