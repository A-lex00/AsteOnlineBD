package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.model.dao.RegistrazioneAmministratoreDAO;
import it.uniroma2.dicii.bd.model.domain.Credenziali;
import it.uniroma2.dicii.bd.view.MenuView;
import it.uniroma2.dicii.bd.view.StartView;

import java.io.IOException;

public class Orchestrator implements Controller {

    @Override
    public void start() {
        boolean flag = true;
        while (flag) {
            try {
                int userTypeChoice = StartView.getTypeOfUser();

                switch (userTypeChoice) {
                    case 1: // Cliente
                        gestioneCliente();
                        break;
                    case 2: // Amministratore
                         gestioneAmministratore();
                        break;
                    case 0: // Esci dall'applicazione
                        StartView.displayExitMessage();
                        flag = false;
                        break;
                    default:
                        StartView.displayInvalidInputError();
                        break;
                }
            } catch (IOException e) {
                StartView.displayError("Errore di I/O durante la selezione del tipo di utente");

                flag = false;
            }
        }
    }
    private void gestioneCliente() {
        System.out.println("\n--- Benvenuto Cliente ---");
        int scelta = -1;
        try {
            scelta = MenuView.showApplicationView();
        } catch (java.util.InputMismatchException e) {
            System.err.println("Input non valido. Inserisci un numero.");
            return;
        }switch (scelta) {
            case 0:
                MenuView.mostraMessaggio("Torno al menu principale.");
                break;
            case 1: // Registrazione Cliente
                RegistrazioneClienteController registrazioneClienteController = new RegistrazioneClienteController();
                registrazioneClienteController.start();
                Credenziali clienteCred = registrazioneClienteController.getCred();
                if (clienteCred != null) {
                    System.out.println("Registrazione cliente completata e login automatico riuscito!");
                    ClienteMenuController clienteMenuController = new ClienteMenuController(clienteCred);
                    clienteMenuController.start();
                }
                break;
            case 2:   //login cliente
                LoginController loginController = new LoginController();
                loginController.start();
                Credenziali clienteLogin = loginController.getCred();
                if(clienteLogin != null){
                    System.out.println("Benvenuto" + clienteLogin.getUsername());
                    ClienteMenuController clienteMenuController = new ClienteMenuController(clienteLogin);
                    clienteMenuController.start();
                }
                break;
            default:
                MenuView.mostraMessaggio("Scelta non valida.");
                break;
        }
    }

    private void gestioneAmministratore() {
        System.out.println("\n--- Benvenuto Amministratore ---");

        int scelta = -1;
        try {
            scelta = MenuView.showApplicationView();
        } catch (java.util.InputMismatchException e) {
            System.err.println("Input non valido. Inserisci un numero.");
            return;
        }
        switch (scelta) {
        case 1: // Registrazione Amministratore
            RegistrazioneAmministratoreDAO regAmmDao = RegistrazioneAmministratoreDAO.getRegistrazioneAmministratoreDAO();
            RegistrazioneAmministratoreController registrazioneAmministratoreController = new RegistrazioneAmministratoreController(regAmmDao);
            registrazioneAmministratoreController.start();
            Credenziali adminCred = registrazioneAmministratoreController.getCredenzialiRegistrate();
            if (adminCred != null) {
                System.out.println("Registrazione amministratore completata e login automatico riuscito!");
                AmministratoreMenuController amministratoreMenuController = new AmministratoreMenuController(adminCred);
                amministratoreMenuController.start();
            }
            break;
        case 2:  // Login Amministratore
            LoginController loginController = new LoginController();
            loginController.start();
            Credenziali adminLogin = loginController.getCred();
            if (adminLogin != null) {
                AmministratoreMenuController amministratoreMenuController = new AmministratoreMenuController(adminLogin);
                amministratoreMenuController.start();
            }
        case 0: // QUIT
            MenuView.mostraMessaggio("Torno al menu principale.");
            break;
        default:
            MenuView.mostraMessaggio("Scelta non valida.");
            break;
        }
    }
}
