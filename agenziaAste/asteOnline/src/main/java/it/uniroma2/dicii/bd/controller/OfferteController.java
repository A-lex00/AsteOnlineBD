package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.FaiOffertaDAO;
import it.uniroma2.dicii.bd.model.domain.Offerta;
import it.uniroma2.dicii.bd.view.OffertaView;

import java.io.IOException;

public class OfferteController {

    private final FaiOffertaDAO offertaDAO;
    private final String utenteLoggato;
    public OfferteController(String username) {
        this.offertaDAO = FaiOffertaDAO.getFaiOffertaDAO();
        this.utenteLoggato = username;
    }

    public void handleFaiOfferta() {
        try {
            OffertaView.DatiOffertaInput datiOfferta = OffertaView.getDatiOfferta(utenteLoggato);

            Offerta nuovaOfferta = new Offerta(
                    datiOfferta.getUsernameUtente(),
                    datiOfferta.getCodiceOggetto(),
                    datiOfferta.getImportoOfferta(),
                    datiOfferta.getControffertaAutomatica()
            );

            offertaDAO.execute(nuovaOfferta);
            OffertaView.displayOffertaSuccesso();

        } catch (IOException e) {
            OffertaView.displayOffertaError("Errore di I/O durante l'inserimento dell'offerta ");
        } catch (IllegalArgumentException e) {
            OffertaView.displayOffertaError("Input offerta non valido");
        } catch (DAOException e) {
            OffertaView.displayOffertaError("Errore database durante l'effettuazione dell'offerta ");
        }
    }
}

