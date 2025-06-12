package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.CategoriaDAO;
import it.uniroma2.dicii.bd.model.dao.InserimentoOggettiDAO;
import it.uniroma2.dicii.bd.model.domain.ListaCategorie;
import it.uniroma2.dicii.bd.model.domain.OggettoInAsta;
import it.uniroma2.dicii.bd.view.AggiungiOggettoView;
import it.uniroma2.dicii.bd.view.AggiungiOggettoView.DatiOggettoInput;
import it.uniroma2.dicii.bd.view.AggiungiOggettoView.ConfirmOption;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class AggiungiOggettoInAstaController implements Controller {

    private final InserimentoOggettiDAO inserimentoOggettiDAO;

    private final CategoriaDAO categoriaDAO;

    public AggiungiOggettoInAstaController(InserimentoOggettiDAO inserimentoOggettiDAO,CategoriaDAO categoriaDAO) throws DAOException {
        this.inserimentoOggettiDAO = inserimentoOggettiDAO;
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public void start() {
        AggiungiOggettoView.mostra();

        try {
            ListaCategorie listaCategorie = categoriaDAO.execute();
            DatiOggettoInput datiInput = AggiungiOggettoView.getDatiOggetto();

            OggettoInAsta nuovoOggetto = new OggettoInAsta();

            // Campi inseriti tramite la View
            nuovoOggetto.setCodice(datiInput.getCodice());
            nuovoOggetto.setDescrizione(datiInput.getDescrizione());
            nuovoOggetto.setPrezzoBase(datiInput.getPrezzoBase());
            nuovoOggetto.setAltezza(datiInput.getAltezza());
            nuovoOggetto.setLunghezza(datiInput.getLunghezza());
            nuovoOggetto.setLarghezza(datiInput.getLarghezza());
            if(listaCategorie.check(datiInput.getCategoria())){
                nuovoOggetto.setCategoria(datiInput.getCategoria());
            }
            else {
                AggiungiOggettoView.displayError("Categoria errata");
            }
            nuovoOggetto.setCategoria(datiInput.getCategoria());
            nuovoOggetto.setDurata(datiInput.getDurata());
            nuovoOggetto.setStato(datiInput.getStato());

            // Campi gestiti dal sistema

            nuovoOggetto.setStatoAsta("aperta");
            nuovoOggetto.setInizioAsta(Date.valueOf(LocalDate.now()));

            nuovoOggetto.setImportoOffertaMassima(datiInput.getPrezzoBase());
            nuovoOggetto.setProprietario(null);

            AggiungiOggettoView.displayDatiOggetto(nuovoOggetto);
            ConfirmOption confirm = AggiungiOggettoView.getConfirmation("Confermi l'aggiunta del oggetto in asta?");

            if (confirm == ConfirmOption.SI) {

                Boolean successoInserimento = inserimentoOggettiDAO.execute(nuovoOggetto);

                if (successoInserimento != null && successoInserimento) {
                    AggiungiOggettoView.displaySuccess("Asta correttamente creata!");
                } else {
                    AggiungiOggettoView.displayError("Oggetto non inserito ");
                }
            } else {
                AggiungiOggettoView.displayMessage("Aggiunta oggetto annullata.");
            }

        } catch (IOException e) {
            AggiungiOggettoView.displayError("Errore di I/O durante l'inserimento dell'oggetto" );
        } catch (IllegalArgumentException e) {
            AggiungiOggettoView.displayError("Dati oggetto non validi");
        } catch (DAOException e) {
            AggiungiOggettoView.displayError("Errore database durante l'aggiunta dell'oggetto ");
        }
    }
}