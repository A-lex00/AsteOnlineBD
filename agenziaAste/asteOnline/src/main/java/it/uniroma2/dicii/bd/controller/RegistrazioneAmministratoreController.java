package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.RegistrazioneAmministratoreDAO;
import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.Role;
import it.uniroma2.dicii.bd.view.RegistrazioneAmmView;
import java.io.IOException;
import it.uniroma2.dicii.bd.view.RegistrazioneAmmView.DatiRegistrazioneInput;



public class RegistrazioneAmministratoreController implements Controller {

        private final RegistrazioneAmministratoreDAO registrazioneAmministratoreDAO;
        private Credentials registratoCredentials;

        public RegistrazioneAmministratoreController(RegistrazioneAmministratoreDAO registrazioneAmministratoreDAO) {

            this.registrazioneAmministratoreDAO = registrazioneAmministratoreDAO;
        }

        @Override
        public void start() {
            RegistrazioneAmmView.displayHeader();
            this.registratoCredentials = null;

            try {

                DatiRegistrazioneInput datiInput = RegistrazioneAmmView.getDatiRegistrazione();


                Credentials nuoveCredenziali = new Credentials(datiInput.getUsername(), datiInput.getPassword(), Role.AMMINISTRATORE);


                Boolean successoRegistrazione = registrazioneAmministratoreDAO.execute(nuoveCredenziali);

                if (successoRegistrazione != null && successoRegistrazione) {
                    RegistrazioneAmmView.displaySuccess("Amministratore '" + nuoveCredenziali.getUsername() + "' registrato con successo!");
                    this.registratoCredentials = nuoveCredenziali;
                } else {
                    RegistrazioneAmmView.displayError("Registrazione amministratore fallita. L'username potrebbe essere già in uso o altro errore logico.");
                }

            } catch (IOException e) {

                RegistrazioneAmmView.displayError("Errore di I/O durante la registrazione: " + e.getMessage());
                e.printStackTrace(); // Stampa lo stack trace per il debug
            } catch (IllegalArgumentException e) {
                RegistrazioneAmmView.displayError("Dati di registrazione non validi: " + e.getMessage());
                e.printStackTrace();
            } catch (DAOException e) {

                RegistrazioneAmmView.displayError("Errore database durante la registrazione dell'amministratore: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {

                RegistrazioneAmmView.displayError("Si è verificato un errore inatteso: " + e.getMessage());
                e.printStackTrace();
            }

        }

        public Credentials getRegisteredCredentials() {
            return this.registratoCredentials;
        }
    }