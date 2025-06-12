package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.view.ClienteView;

import java.io.IOException;

public class ClienteMenuController implements  Controller {

    private final Credentials loggedInCredentials; // Le credenziali del cliente loggato

    public ClienteMenuController(Credentials loggedInCredentials) {
        this.loggedInCredentials = loggedInCredentials;
    }

    @Override
    public void start() {
        if (loggedInCredentials == null) {
            ClienteView.displayGenericError("Nessun cliente loggato. Impossibile avviare il menu.");
            return;
        }

            ClienteView.displayWelcomeMessage(loggedInCredentials.getUsername());

            boolean running = true;
            while (running) {
                try {
                    int choice = ClienteView.showMenu(); // Chiamata al metodo showMenu() della tua ClienteView

                    switch (choice) {
                        case 1: // Vedi aste aperte
                            System.out.println("Caricamento aste aperte...");
                            AsteAperteController asteAperteController = new AsteAperteController();
                            asteAperteController.start();
                            break;
                        case 2: // Vedi aste in corso
                            AsteInCorsoController asteInCorsoController = new AsteInCorsoController(loggedInCredentials.getUsername());
                            asteInCorsoController.start();
                            break;
                        case 3: // fai offerta
                            OfferteController offerteController = new OfferteController(loggedInCredentials.getUsername());
                            offerteController.handleMakeOffer();
                            break;
                        case 4: // vedi oggetti acquistati
                            OggettiAcquistatiController oggettiAcquistatiController = new OggettiAcquistatiController(loggedInCredentials.getUsername());
                            oggettiAcquistatiController.start();
                        case 5: //quit
                            ClienteView.displayLogoutMessage();
                            running = false; // Esce dal loop del menu cliente
                            break;
                        default:
                            ClienteView.displayGenericError("Scelta non gestita. Errore interno.");
                            break;
                    }
                } catch (IOException e) {
                    ClienteView.displayGenericError("Errore di I/O nel menu cliente: " + e.getMessage());
                    // In un'applicazione reale, potresti voler gestire questo errore in modo più robusto
                    running = false; // Esci dal menu in caso di errore grave di I/O
                } catch (RuntimeException e) {
                    ClienteView.displayGenericError("Si è verificato un errore inatteso: " + e.getMessage());
                }
            }
        }
    }

