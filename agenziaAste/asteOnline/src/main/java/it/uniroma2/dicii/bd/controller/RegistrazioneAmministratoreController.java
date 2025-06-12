package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.RegistrazioneAmministratoreDAO;
import it.uniroma2.dicii.bd.model.domain.Credenziali;
import it.uniroma2.dicii.bd.model.domain.Ruolo;
import it.uniroma2.dicii.bd.view.RegistrazioneAmmView;
import java.io.IOException;
import it.uniroma2.dicii.bd.view.RegistrazioneAmmView.DatiRegistrazioneInput;



public class RegistrazioneAmministratoreController implements Controller {

        private final RegistrazioneAmministratoreDAO registrazioneAmministratoreDAO;
        private Credenziali registratoCredenziali;

        public RegistrazioneAmministratoreController(RegistrazioneAmministratoreDAO registrazioneAmministratoreDAO) {

            this.registrazioneAmministratoreDAO = registrazioneAmministratoreDAO;
        }

        @Override
        public void start() {
            RegistrazioneAmmView.mostra();
            this.registratoCredenziali = null;

            try {
                DatiRegistrazioneInput datiInput = RegistrazioneAmmView.getDatiRegistrazione();


                Credenziali nuoveCredenziali = new Credenziali(datiInput.getUsername(), datiInput.getPassword(), Ruolo.AMMINISTRATORE);


                Boolean flag = registrazioneAmministratoreDAO.execute(nuoveCredenziali);

                if (flag != null && flag) {
                    RegistrazioneAmmView.messaggioSuccesso("Amministratore  registrato con successo!");
                    this.registratoCredenziali = nuoveCredenziali;
                } else {
                    RegistrazioneAmmView.mostraErrore("Registrazione amministratore fallita.");
                }

            } catch (IOException e) {
                RegistrazioneAmmView.mostraErrore("Errore di I/O durante la registrazione ");
            } catch (IllegalArgumentException e) {
                RegistrazioneAmmView.mostraErrore("Dati  non validi ");
            } catch (DAOException e) {
                RegistrazioneAmmView.mostraErrore("Errore database durante la registrazione dell'amministratore " );
            }

        }

        public Credenziali getCredenzialiRegistrate() {
            return this.registratoCredenziali;
        }
    }