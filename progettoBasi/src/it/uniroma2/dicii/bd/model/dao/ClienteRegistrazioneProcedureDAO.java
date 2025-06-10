package it.uniroma2.dicii.bd.model.dao;



import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Cliente;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ClienteRegistrazioneProcedureDAO implements GenericProcedureDAO<Boolean>{

    private static ClienteRegistrazioneProcedureDAO registerDAO = null;
    private ClienteRegistrazioneProcedureDAO(){}

    public static ClienteRegistrazioneProcedureDAO getInstance(){
        if(registerDAO == null){
            registerDAO = new ClienteRegistrazioneProcedureDAO();
        }
        return registerDAO;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException{
        Cliente cliente = (Cliente) params[0];
        java.sql.Date sqlDataScadenza;
        java.sql.Date sqlDataNascita;

        //conversione per dataScadenza
        try{
            LocalDate dataScadenzaCarta = LocalDate.parse(cliente.getDataScadenza());
            sqlDataScadenza = java.sql.Date.valueOf(dataScadenzaCarta);
        }catch (DateTimeParseException e) {
            System.err.println("Errore nel formato data scadenza carta");
            throw new DateTimeException("formato data di scadenza carta non valido" + e);
        }
        //conversione per data di nascita
        try{
            LocalDate dataNascita = LocalDate.parse(cliente.getDataNascita());
            sqlDataNascita = java.sql.Date.valueOf(dataNascita);
        }
        catch (DateTimeParseException e){
            System.err.println("Errore nel formato data scadenza carta");
            throw new DateTimeException("formato data di nascita non valido" + e);
        }
        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call registrazione(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setString(1, cliente.getNome());
            callableStatement.setString(2, cliente.getCognome());
            callableStatement.setString(3, cliente.getCF());
            callableStatement.setDate(4, sqlDataNascita);
            callableStatement.setString(5, cliente.getCittàNascita());
            callableStatement.setString(6, cliente.getPassword());
            callableStatement.setString(7, cliente.getNumeroCarta());
            callableStatement.setString(8 ,cliente.getCVV());
            callableStatement.setDate(9, sqlDataScadenza);
            callableStatement.setString(10, cliente.getVia());
            callableStatement.setString(11 ,cliente.getCittà());
            callableStatement.setString(12, cliente.getCAP());
            callableStatement.setString(13, cliente.getNomeUtente());
            callableStatement.setString(14, cliente.getCivico());
            callableStatement.executeQuery();

        }catch (SQLException e){
            throw new DAOException("Error: " + e.getMessage());
        }

        return true;
    }

}
