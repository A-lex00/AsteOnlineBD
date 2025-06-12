package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.model.domain.Credenziali;
import it.uniroma2.dicii.bd.view.ClienteView;

import java.io.IOException;

public class ClienteMenuController implements  Controller {

    private final Credenziali loggedInCredenziali;

    public ClienteMenuController(Credenziali loggedInCredenziali) {
        this.loggedInCredenziali = loggedInCredenziali;
    }

    @Override
    public void start() {
        if (loggedInCredenziali == null) {
            ClienteView.mostraErrore("Nessun cliente loggato. Impossibile avviare il menu.");
            return;
        }

            ClienteView.messaggioBenvenuto(loggedInCredenziali.getUsername());

            boolean flag = true;
            while (flag) {
                try {
                    int scelta = ClienteView.mostra();

                    switch (scelta) {
                        case 1: // Vedi aste aperte
                            System.out.println("Caricamento aste aperte...");
                            AsteAperteController asteAperteController = new AsteAperteController();
                            asteAperteController.start();
                            break;
                        case 2: // Vedi aste in corso
                            AsteInCorsoController asteInCorsoController = new AsteInCorsoController(loggedInCredenziali.getUsername());
                            asteInCorsoController.start();
                            break;
                        case 3: // fai offerta
                            OfferteController offerteController = new OfferteController(loggedInCredenziali.getUsername());
                            offerteController.handleFaiOfferta();
                            break;
                        case 4: // vedi oggetti acquistati
                            OggettiAcquistatiController oggettiAcquistatiController = new OggettiAcquistatiController(loggedInCredenziali.getUsername());
                            oggettiAcquistatiController.start();
                        case 5: //quit
                            ClienteView.messaggioArrivederci();
                            flag = false;
                            break;
                        default:
                            ClienteView.mostraErrore("Scelta non gestita. Errore interno.");
                            break;
                    }
                } catch (IOException e) {
                    ClienteView.mostraErrore("Errore di I/O nel menu cliente " );
                    flag = false;
                }
            }
        }
    }

