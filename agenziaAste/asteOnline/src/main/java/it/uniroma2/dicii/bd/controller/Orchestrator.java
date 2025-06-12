package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.model.dao.RegistrazioneAmministratoreDAO;
import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.view.ApplicationView;
import it.uniroma2.dicii.bd.view.LoginView;
import it.uniroma2.dicii.bd.view.StartView;

import java.io.IOException;

public class Orchestrator implements Controller {

    // Questo è il punto di ingresso dell'applicazione
    @Override
    public void start() {
        boolean running = true;
        while (running) {
            try {
                int userTypeChoice = StartView.getTypeOfUser(); // Chiede il tipo di utente

                switch (userTypeChoice) {
                    case 1: // Cliente
                        handleClienteFlow();
                        break;
                    case 2: // Amministratore
                         handleAmministratoreFlow();
                        break;
                    case 0: // Esci dall'applicazione
                        StartView.displayExitMessage();
                        running = false;
                        break;
                    default:
                        StartView.displayInvalidInputError();
                        break;
                }
            } catch (IOException e) {
                StartView.displayError("Errore di I/O durante la selezione del tipo di utente: " + e.getMessage());

                running = false;
            } catch (RuntimeException e) {

                StartView.displayError("Si è verificato un errore inatteso nel flusso dell'applicazione: " + e.getMessage());

            }
        }
    }
    private void handleClienteFlow() {
        System.out.println("\n--- Benvenuto Cliente ---");
        int choice = -1;
        try {
            choice = ApplicationView.showApplicationView();
        } catch (java.util.InputMismatchException e) { // Cattura se l'utente non inserisce un intero
            System.err.println("Input non valido. Inserisci un numero.");
            return;
        }switch (choice) {
            case 0:
                ApplicationView.displayMessage("Torno al menu principale.");
                break;
            case 1: // Registrazione Cliente
                RegistrazioneClienteController registrazioneClienteController = new RegistrazioneClienteController();
                registrazioneClienteController.start();
                Credentials clienteCred = registrazioneClienteController.getCred();
                if (clienteCred != null) {
                    System.out.println("Registrazione cliente completata e login automatico riuscito!");
                    ClienteMenuController clienteMenuController = new ClienteMenuController(clienteCred);
                    clienteMenuController.start(); // Avvia il menu specifico per il cliente
                }
                break;
            case 2:
                LoginController loginController = new LoginController();
                loginController.start();
                Credentials clienteLogin = loginController.getCred();
                if(clienteLogin != null){
                    System.out.println("Benvenuto" + clienteLogin.getUsername());
                    ClienteMenuController clienteMenuController = new ClienteMenuController(clienteLogin);
                    clienteMenuController.start(); // Avvia il menu specifico per il cliente
                }
                break;
            default:
                ApplicationView.displayMessage("Scelta non valida.");
                break;
        }
    }

    private void handleAmministratoreFlow() {
        System.out.println("\n--- Benvenuto Amministratore ---");

        int choice = -1;
        try {
            choice = ApplicationView.showApplicationView();
        } catch (java.util.InputMismatchException e) { // Cattura se l'utente non inserisce un intero
            System.err.println("Input non valido. Inserisci un numero.");
            return;
        }
        switch (choice) {
        case 1: // Registrazione Amministratore
            RegistrazioneAmministratoreDAO regAmmDao = RegistrazioneAmministratoreDAO.getInstance();
            RegistrazioneAmministratoreController registrazioneAmministratoreController = new RegistrazioneAmministratoreController(regAmmDao);
            registrazioneAmministratoreController.start();
            Credentials adminCred = registrazioneAmministratoreController.getRegisteredCredentials();
            if (adminCred != null) {
                System.out.println("Registrazione amministratore completata e login automatico riuscito!");
                AmministratoreMenuController amministratoreMenuController = new AmministratoreMenuController(adminCred);
                amministratoreMenuController.start();
            }
            break;
        case 2:  // Login Amministratore
            LoginController loginController = new LoginController();
            loginController.start();
            Credentials adminLogin = loginController.getCred();
            if (adminLogin != null) {
                AmministratoreMenuController amministratoreMenuController = new AmministratoreMenuController(adminLogin);
                amministratoreMenuController.start();
            }

        case 0: // QUIT
            ApplicationView.displayMessage("Torno al menu principale.");
            break;
        default:
            ApplicationView.displayMessage("Scelta non valida.");
            break;
        }
    }
}
