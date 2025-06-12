package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.InserimentoOggettiDAO;
import it.uniroma2.dicii.bd.model.domain.OggettoInAsta;
import it.uniroma2.dicii.bd.view.AggiungiOggettoView;
import it.uniroma2.dicii.bd.view.AggiungiOggettoView.DatiOggettoInput;
import it.uniroma2.dicii.bd.view.AggiungiOggettoView.ConfirmOption;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class AggiungiOggettoInAstaController implements Controller {


    private final InserimentoOggettiDAO inserimentoOggettiDAO;

    public AggiungiOggettoInAstaController(InserimentoOggettiDAO inserimentoOggettiDAO) {
        this.inserimentoOggettiDAO = inserimentoOggettiDAO;
    }

    @Override
    public void start() {
        AggiungiOggettoView.displayHeader();

        try {
            // 1. Raccogli i dati dell'oggetto dalla View
            DatiOggettoInput datiInput = AggiungiOggettoView.getDatiOggetto();

            // 2. Crea l'oggetto dominio 'OggettoInAsta' e popolalo.
            OggettoInAsta nuovoOggetto = new OggettoInAsta();

            // Campi inseriti dall'utente tramite la View
            nuovoOggetto.setCodice(datiInput.getCodice());
            nuovoOggetto.setDescrizione(datiInput.getDescrizione());
            nuovoOggetto.setPrezzoBase(datiInput.getPrezzoBase());
            nuovoOggetto.setAltezza(datiInput.getAltezza());
            nuovoOggetto.setLunghezza(datiInput.getLunghezza());
            nuovoOggetto.setLarghezza(datiInput.getLarghezza());
            nuovoOggetto.setCategoria(datiInput.getCategoria());
            nuovoOggetto.setDurata(datiInput.getDurata());

            // Campi gestiti dal sistema/logica applicativa
            // Lo stato dell'oggetto ("disponibile", "venduto", ecc.) è gestito qui
            nuovoOggetto.setStato("disponibile"); // Lo stato iniziale è "disponibile" per un nuovo oggetto in asta
            nuovoOggetto.setStatoAsta("aperta"); // L'asta è inizialmente "aperta"
            nuovoOggetto.setInizioAsta(Date.valueOf(LocalDate.now())); // Data di inizio asta = data odierna
            // Come da tua logica, l'offerta massima iniziale è il prezzo base
            nuovoOggetto.setImportoOffertaMassima(datiInput.getPrezzoBase());
            // Il proprietario è impostato a null, come da tua ultima indicazione
            nuovoOggetto.setProprietario(null); // Imposta il proprietario a null

            // 3. Chiedi conferma all'utente prima di procedere con l'inserimento
            AggiungiOggettoView.displayDatiOggetto(nuovoOggetto); // Mostra un riepilogo per conferma
            ConfirmOption confirm = AggiungiOggettoView.getConfirmation("Confermi l'aggiunta di questo oggetto in asta?");

            if (confirm == ConfirmOption.YES) {
                // 4. Invocare il DAO per persistere l'oggetto nel database
                Boolean successoInserimento = inserimentoOggettiDAO.execute(nuovoOggetto);

                if (successoInserimento != null && successoInserimento) {
                    AggiungiOggettoView.displaySuccess("Oggetto '" + nuovoOggetto.getDescrizione() + "' aggiunto con successo all'asta!");
                } else {
                    AggiungiOggettoView.displayError("L'inserimento dell'oggetto non è andato a buon fine per un motivo logico.");
                }
            } else {
                AggiungiOggettoView.displayMessage("Aggiunta oggetto annullata.");
            }

        } catch (IOException e) {
            AggiungiOggettoView.displayError("Errore di I/O durante l'inserimento dell'oggetto: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            AggiungiOggettoView.displayError("Dati oggetto non validi: " + e.getMessage());
            e.printStackTrace();
        } catch (DAOException e) {
            AggiungiOggettoView.displayError("Errore database durante l'aggiunta dell'oggetto: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            AggiungiOggettoView.displayError("Si è verificato un errore inatteso: " + e.getMessage());
            e.printStackTrace();
        }
    }
}