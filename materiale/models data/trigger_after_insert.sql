CREATE TRIGGER `trg_after_insert_controfferta`
AFTER INSERT ON `offerte`
FOR EACH ROW
BEGIN 
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
        offerta_massima,
        prezzo_base,
        durata,
        inizio_asta
    INTO
        v_offerta_massima_attuale_oggetto,
        v_prezzo_base_oggetto,
        v_durata_oggetto,
        v_inizio_asta_oggetto
    FROM
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

END