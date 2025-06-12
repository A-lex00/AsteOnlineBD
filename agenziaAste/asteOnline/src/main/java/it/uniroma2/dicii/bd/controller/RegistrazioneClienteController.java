package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.RegistrazioneClienteDAO;
import it.uniroma2.dicii.bd.model.domain.Cliente;
import it.uniroma2.dicii.bd.model.domain.Credenziali;
import it.uniroma2.dicii.bd.view.RegistrazioneClienteView;

import java.io.IOException;

    public class RegistrazioneClienteController implements Controller{
        private Boolean flag;
        private Credenziali cred = null;
        private Cliente cliente;

        @Override
        public void start(){

            try {
                cred = RegistrazioneClienteView.registra();
                cliente = RegistrazioneClienteView.setUserInfo(cred);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                RegistrazioneClienteDAO registerProcedureDAO = RegistrazioneClienteDAO.getInstance();
                flag = registerProcedureDAO.execute(cliente);
            }catch (DAOException e){
                throw new RuntimeException(e);
            }
            if(flag){
                System.out.println("Registrazione avvenuta con successo!");
            }
        }
        public Credenziali getCred(){
            return cred;
        }
        public Cliente getUser(){
            return cliente;
        }
    }


