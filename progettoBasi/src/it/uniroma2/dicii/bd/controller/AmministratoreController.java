package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.BookingListProcedureDAO;
import it.uniroma2.dicii.bd.model.dao.ConnectionFactory;
import it.uniroma2.dicii.bd.model.domain.Role;
import it.uniroma2.dicii.bd.view.ClienteView;

import java.io.IOException;
import java.sql.SQLException;

public class AmministratoreController implements Controller {
    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.AMMINISTRATORE);
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
             /*   case 1 -> creaOggettoInVendita();
                case 2 -> aggiungiAmministratori();
                case 3 -> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }*/

#FINIRE


    public void bookingList() {
        BookingList bookingList;
        try {
            bookingList = new BookingListProcedureDAO().execute();
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }

        System.out.print(bookingList);
    }


}
