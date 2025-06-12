package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.FaiOffertaDAO;
import it.uniroma2.dicii.bd.model.dao.VisualizzaAsteDAO;
import it.uniroma2.dicii.bd.model.domain.Offerta;
import it.uniroma2.dicii.bd.model.domain.OggettoInAsta;
import it.uniroma2.dicii.bd.view.OffertaView;
import it.uniroma2.dicii.bd.view.ViewAsteAperte;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class OfferteController {

    private final FaiOffertaDAO offertaDAO;
    private final String loggedInUsername;
    public OfferteController(String username) {
        this.offertaDAO = FaiOffertaDAO.getInstance(); // Usiamo getInstance() se è un Singleton come nel tuo codice
        this.loggedInUsername = username;
    }

    public void handleMakeOffer() {
        try {
            OffertaView.DatiOffertaInput datiOfferta = OffertaView.getDatiOfferta(loggedInUsername);

            Offerta nuovaOfferta = new Offerta(
                    datiOfferta.getUsernameUtente(),
                    datiOfferta.getCodiceOggetto(),
                    datiOfferta.getImportoOfferta(),
                    datiOfferta.getControffertaAutomatica()
            );


            offertaDAO.execute(nuovaOfferta);

            // 4. Visualizza il feedback all'utente tramite la view specifica dell'offerta.
            OffertaView.displayOffertaSuccesso();

        } catch (IOException e) {
            // Gestione degli errori di input/output che provengono dalla view
            OffertaView.displayOffertaError("Errore di I/O durante l'inserimento dell'offerta: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // Gestione degli errori di validazione dell'input (es. importo negativo) dalla view
            OffertaView.displayOffertaError("Input offerta non valido: " + e.getMessage());
        } catch (DAOException e) {
            // Gestione degli errori che provengono dal livello di accesso ai dati (DAO)
            OffertaView.displayOffertaError("Errore database durante l'effettuazione dell'offerta: " + e.getMessage());
        } catch (Exception e) {
            // Cattura qualsiasi altra eccezione inattesa per evitare crash improvvisi
            OffertaView.displayOffertaError("Si è verificato un errore inatteso durante l'offerta: " + e.getMessage());
        }
    }
}

