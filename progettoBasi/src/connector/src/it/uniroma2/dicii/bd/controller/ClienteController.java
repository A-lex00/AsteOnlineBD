package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.BookingListProcedureDAO;
import it.uniroma2.dicii.bd.model.dao.ConnectionFactory;
import it.uniroma2.dicii.bd.model.domain.BookingList;
import it.uniroma2.dicii.bd.model.domain.Role;
import it.uniroma2.dicii.bd.view.UtenteView;

import java.io.IOException;
import java.sql.SQLException;

public class ClienteController implements Controller{

    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.cliente);
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
                case 1 -> bookFlight();
                case 2 -> bookingList();
                case 3 -> listFlights();
                case 4 -> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }

    public void bookFlight() {
        throw new RuntimeException("Not implemented yet");
    }

    public void bookingList() {
        BookingList bookingList;
        try {
             bookingList = new BookingListProcedureDAO().execute();
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }

        System.out.print(bookingList);
    }

    public void listFlights() {
        throw new RuntimeException("Not implemented yet");
    }
}
