package it.uniroma2.dicii.bd.controller;


import it.uniroma2.dicii.bd.model.dao.ConnectionFactory;
import it.uniroma2.dicii.bd.model.domain.Role;
import it.uniroma2.dicii.bd.view.ClienteView;

import java.io.IOException;
import java.sql.SQLException;

public class ClienteController implements Controller{
    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.CLIENTE);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }

        while(true) {
            int choice;
            try {
                choice = ClienteView.showMenu();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }

            switch(choice) {
                case 1 -> vediAsteAperte();
                case 2 -> vediAsteInCorso();
                case 3 -> vediOggettiAcquistati();
                case 4 -> faiOfferta();
                case 5 -> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }

    private void  vediAsteAperte(){
    }
    private void vediAsteInCorso(){}
    private void vediOggettiAcquistati(){}
    private void faiOfferta(){}


}