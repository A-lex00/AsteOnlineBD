package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.model.dao.ConnectionFactory;
import it.uniroma2.dicii.bd.model.domain.Role;
import it.uniroma2.dicii.bd.view.UtenteView;

import java.io.IOException;
import java.sql.SQLException;

public class AmministratoreController implements Controller {
    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.amministratore);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }

        while(true) {
            int choice;
            try {
                choice = UtenteView.showMenu();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }

            switch(choice) {
                case 4 -> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }


}