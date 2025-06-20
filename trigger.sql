CREATE
DEFINER=`root`@`localhost`
TRIGGER `trg_after_insert_controfferta`
AFTER INSERT ON `asteonline`.`offerte`
FOR EACH ROW

BEGIN
    DECLARE var_offerta_massima_oggetto DOUBLE;
    DECLARE var_durata_asta INT;
    DECLARE var_inizio_asta_oggetto DATETIME;
    DECLARE var_scadenza_asta DATETIME;

    DECLARE var_vecchio_offerente VARCHAR(30);
    DECLARE var_controfferta_massima_vecchia DOUBLE;

    DECLARE var_importo_nuova_controfferta DOUBLE;

    SELECT
        oia.offerta_massima,
        oia.durata,
        oia.inizio_asta
    INTO
        var_offerta_massima_oggetto,
        var_durata_asta,
        var_inizio_asta_oggetto
    FROM
        asteonline.oggetti_in_asta oia
    WHERE
        oia.codice = NEW.oggetto_in_vendita;

    SET var_scadenza_asta = DATE_ADD(var_inizio_asta_oggetto, INTERVAL var_durata_asta DAY);

    IF (asteonline.controllo_data(var_scadenza_asta)) THEN

        SELECT
            o.cliente,
            o.controfferta_massima
        INTO
            var_vecchio_offerente,
            var_controfferta_massima_vecchia
        FROM
            asteonline.offerte o
        WHERE
            o.oggetto_in_vendita = NEW.oggetto_in_vendita
            AND o.cliente != NEW.cliente
            AND o.controfferta_massima IS NOT NULL
            AND o.controfferta_massima > 0
        ORDER BY
            o.valore_effettivo DESC,
            o.controfferta_massima DESC
        LIMIT 1;

        IF var_vecchio_offerente IS NOT NULL THEN
            SET var_importo_nuova_controfferta = NEW.valore_effettivo + 0.50;

            IF var_importo_nuova_controfferta > var_controfferta_massima_vecchia THEN
                SET var_importo_nuova_controfferta = var_controfferta_massima_vecchia;
            END IF;

            IF (var_importo_nuova_controfferta > NEW.valore_effettivo) THEN
                INSERT INTO asteonline.controfferte_in_coda (
                    var_cliente,
                    var_oggetto_in_vendita,
                    var_offerta_automatica,
                    var_controfferta_massima
                )
                VALUES (
                    var_vecchio_offerente,
                    NEW.oggetto_in_vendita,
                    var_importo_nuova_controfferta,
                    var_controfferta_massima_vecchia
                );
            END IF;

        END IF;

    END IF;
END;