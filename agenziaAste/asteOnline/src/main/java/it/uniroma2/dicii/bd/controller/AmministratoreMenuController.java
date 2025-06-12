package it.uniroma2.dicii.bd.controller;


import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.CategoriaDAO;
import it.uniroma2.dicii.bd.model.dao.CreaCategoriaDAO;
import it.uniroma2.dicii.bd.model.dao.InserimentoOggettiDAO;
import it.uniroma2.dicii.bd.model.domain.Credenziali;
import it.uniroma2.dicii.bd.view.AmministratoreView;

import java.io.IOException;

public class AmministratoreMenuController implements Controller {

    private final Credenziali loggedInCredenziali;
    private final InserimentoOggettiDAO inserimentoOggettiDAO;
    private final CreaCategoriaDAO creaCategoriaDAO;
    private final CategoriaDAO categoriaDAO;



    public AmministratoreMenuController(Credenziali loggedInCredenziali){
        this.loggedInCredenziali = loggedInCredenziali;
        this.categoriaDAO = CategoriaDAO.getCategoriaDAO();
        this.creaCategoriaDAO = CreaCategoriaDAO.getCreaCategoriaDAO();
        this.inserimentoOggettiDAO = InserimentoOggettiDAO.getInserimentoOggettiDAO();
    }

    @Override
    public void start() {
        if (loggedInCredenziali == null) {
            AmministratoreView.messaggioErrore("Nessun amministratore loggato.");
            return;
        }

        AmministratoreView.displayWelcomeMessage(loggedInCredenziali.getUsername());

        boolean flag = true;
        while (flag) {
            try {
                int scelta = AmministratoreView.mostra();

                switch (scelta) {
                    case 1: // Crea Asta
                        AggiungiOggettoInAstaController aggiungiOggettoInAstaController = new AggiungiOggettoInAstaController(inserimentoOggettiDAO,categoriaDAO);
                        aggiungiOggettoInAstaController.start();
                        break;
                    case 2: // Crea Categorie
                        GestioneCategorieController gestioneCategorieController = new GestioneCategorieController(creaCategoriaDAO, this.categoriaDAO);
                        gestioneCategorieController.start();
                        break;
                    case 3: // Mostra Categorie

                        MostraCategorieController mostraCategorieController = new MostraCategorieController(this.categoriaDAO);
                        mostraCategorieController.start();
                        break;
                    case 4: // quit
                        AmministratoreView.messaggioArrivederci();
                        flag = false;
                        break;
                    default:
                        AmministratoreView.messaggioErrore("Scelta non valida. Inserisci un numero tra 1 e 4.");
                        break;
                }
            } catch (IOException e) {
                AmministratoreView.messaggioErrore("Errore di I/O nel menu amministratore: " );
                flag = false;
            } catch (RuntimeException e) {
                AmministratoreView.messaggioErrore("Si è verificato un errore inatteso: ");
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}