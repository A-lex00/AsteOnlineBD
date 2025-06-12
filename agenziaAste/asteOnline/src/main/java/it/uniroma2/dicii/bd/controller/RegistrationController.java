package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.RegistrazioneClienteDAO;
import it.uniroma2.dicii.bd.model.domain.Cliente;
import it.uniroma2.dicii.bd.view.RegistrationView;
import java.io.IOException;

public class RegistrationController implements Controller {

        private Cliente cliente = null;

        @Override
        public void start() {
            try {
                // 1. Ottieni i dati di registrazione dalla View
                cliente = RegistrationView.getRegistrationData();

                // 2. Esegui le validazioni dei dati di business nel Controller
                validateUserData(cliente); // Ho spostato la validazione qui o in un servizio

                // 3. Hash della password prima di passarla al DAO
                String password = cliente.getPassword();
                cliente.setPassword(password);

                // 4. Salva l'utente tramite il DAO
                new RegistrazioneClienteDAO().execute(cliente);

                // 5. Mostra il successo tramite la View
                RegistrationView.displaySuccess();

            } catch (IOException e) {
                RegistrationView.displayError("Errore durante l'input dei dati: " + e.getMessage());
                throw new RuntimeException("Errore di I/O nel controller di registrazione", e);
            } catch (IllegalArgumentException e) {
                RegistrationView.displayError(e.getMessage());
            } catch (DAOException e) {
                RegistrationView.displayError("Errore nel sistema: " + e.getMessage());
                throw new RuntimeException("Errore DAO nel controller di registrazione", e);
            } catch (Exception e) {
                RegistrationView.displayError("Si è verificato un errore imprevisto: " + e.getMessage());
                throw new RuntimeException("Errore generico nel controller di registrazione", e);
            }
        }

        private void validateUserData(Cliente cliente) throws IllegalArgumentException, DAOException {
            if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
                throw new IllegalArgumentException("Il nome è obbligatorio.");
            }
            if (cliente.getCognome() == null || cliente.getCognome().trim().isEmpty()) {
                throw new IllegalArgumentException("Il cognome è obbligatorio.");
            }
            if (cliente.getCF() == null || cliente.getCF().trim().isEmpty() || cliente.getCF().length() != 16) {
                throw new IllegalArgumentException("Il Codice Fiscale (CF) è obbligatorio e deve essere di 16 caratteri.");
            }
            if (cliente.getDataNascita() == null) { // Già validato nel view, ma un controllo qui non fa male
                throw new IllegalArgumentException("La data di nascita è obbligatoria e deve essere valida.");
            }
            if (cliente.getNomeUtente() == null || cliente.getNomeUtente().trim().isEmpty() || cliente.getNomeUtente().length() < 3 || cliente.getNomeUtente().length() > 30) {
                throw new IllegalArgumentException("Lo username è obbligatorio e deve avere tra 3 e 30 caratteri.");
            }
            if (cliente.getPassword() == null || cliente.getPassword().trim().isEmpty() || cliente.getPassword().length() < 8) {
                throw new IllegalArgumentException("La password è obbligatoria e deve essere di almeno 8 caratteri.");
            }
        }
        public Cliente getNewUser() {
            return cliente;
        }
    }

