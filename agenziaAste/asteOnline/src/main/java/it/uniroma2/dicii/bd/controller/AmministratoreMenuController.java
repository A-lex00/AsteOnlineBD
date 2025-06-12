package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.CategoriaDAO;
import it.uniroma2.dicii.bd.model.dao.CreaCategoriaDAO;
import it.uniroma2.dicii.bd.model.dao.InserimentoOggettiDAO;
import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.view.AmministratoreView;

import java.io.IOException;

public class AmministratoreMenuController implements Controller {

    private final Credentials loggedInCredentials;
    private final InserimentoOggettiDAO inserimentoOggettiDAO; // Corretto: Dichiarata una sola volta e final
    private final CreaCategoriaDAO creaCategoriaDAO;
    private final CategoriaDAO categoriaDAO;

    // Il costruttore ora riceve tutte le dipendenze necessarie
    public AmministratoreMenuController(Credentials loggedInCredentials,
                                        InserimentoOggettiDAO inserimentoOggettiDAO,
                                        CreaCategoriaDAO creaCategoriaDAO,
                                        CategoriaDAO categoriaDAO) {
        this.loggedInCredentials = loggedInCredentials;
        this.inserimentoOggettiDAO = inserimentoOggettiDAO;
        this.creaCategoriaDAO = creaCategoriaDAO;
        this.categoriaDAO = categoriaDAO;
    }
    public AmministratoreMenuController(Credentials loggedInCredentials){
        this.loggedInCredentials = loggedInCredentials;
        this.categoriaDAO = CategoriaDAO.getInstance();
        this.creaCategoriaDAO = CreaCategoriaDAO.getInstance();
        this.inserimentoOggettiDAO = InserimentoOggettiDAO.getInstance();
    }

    @Override
    public void start() {
        if (loggedInCredentials == null) {
            AmministratoreView.displayGenericError("Nessun amministratore loggato. Impossibile avviare il menu."); // Ho corretto "cliente" ad "amministratore" per consistenza
            return;
        }

        AmministratoreView.displayWelcomeMessage(loggedInCredentials.getUsername());

        boolean running = true;
        while (running) {
            try {
                int choice = AmministratoreView.showMenu(); // Chiamata al metodo showMenu()

                switch (choice) {
                    case 1: // Crea Asta
                        AggiungiOggettoInAstaController aggiungiOggettoInAstaController = new AggiungiOggettoInAstaController(inserimentoOggettiDAO);
                        aggiungiOggettoInAstaController.start();
                        break;
                    case 2: // Gestisci Categoria
                        // Utilizza l'istanza 'categoriaDAO' (minuscola)
                        GestioneCategorieController gestioneCategorieController = new GestioneCategorieController(creaCategoriaDAO, this.categoriaDAO);
                        gestioneCategorieController.start();
                        break;
                    case 3: // Mostra Categorie
                        // Utilizza l'istanza 'categoriaDAO' (minuscola)
                        MostraCategorieController mostraCategorieController = new MostraCategorieController(this.categoriaDAO);
                        mostraCategorieController.start();
                        break;
                    case 4: // quit (Assumo che tu abbia corretto l'AmministratoreView per avere 4 opzioni)
                        AmministratoreView.displayLogoutMessage();
                        running = false; // Esce dal loop del menu
                        break;
                    default:
                        AmministratoreView.displayGenericError("Scelta non valida. Inserisci un numero tra 1 e 4."); // Ho corretto il messaggio per riflettere le 4 opzioni
                        break;
                }
            } catch (IOException e) {
                AmministratoreView.displayGenericError("Errore di I/O nel menu amministratore: " + e.getMessage()); // Ho corretto "cliente" ad "amministratore"
                running = false;
            } catch (RuntimeException e) { // Cattura generica runtime, potresti voler essere più specifico
                AmministratoreView.displayGenericError("Si è verificato un errore inatteso: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}