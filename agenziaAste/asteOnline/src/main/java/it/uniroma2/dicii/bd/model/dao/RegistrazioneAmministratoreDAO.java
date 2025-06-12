package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Credenziali;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RegistrazioneAmministratoreDAO implements GenericProcedureDAO<Boolean>{

    public static RegistrazioneAmministratoreDAO registrazioneAmministratoreDAO = null;

    private RegistrazioneAmministratoreDAO(){
    }

    public static RegistrazioneAmministratoreDAO getRegistrazioneAmministratoreDAO(){
        if(registrazioneAmministratoreDAO == null){
            registrazioneAmministratoreDAO = new RegistrazioneAmministratoreDAO();
        }
        return registrazioneAmministratoreDAO;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException {

        if (params.length != 1 || !(params[0] instanceof Credenziali)) {
            throw new IllegalArgumentException("Parametri non validi");
        }
        Credenziali credenziali = (Credenziali) params[0];


        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = ConnectionFactory.getConnection();


            String call = "{call registrazione_amministratore(?,?)}";
            callableStatement = connection.prepareCall(call);

            callableStatement.setString(1, credenziali.getUsername());
            callableStatement.setString(2, credenziali.getPassword());


            callableStatement.execute();


            return true;

        } catch (SQLException sqlException) {

            System.err.println("Errore SQLState ");


            if (sqlException.getSQLState() != null && sqlException.getSQLState().startsWith("23")) {
                throw new DAOException("Registrazione fallita: username è già in uso. " );
            }

            throw new DAOException("Errore SQL durante la registrazione amministratore");
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
            } catch (SQLException e) {
                System.err.println("Errore nella chiusura del CallableStatement ");
            }
        }
    }
}