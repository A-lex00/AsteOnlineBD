package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Credentials;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RegistrazioneAmministratoreDAO implements GenericProcedureDAO<Boolean>{

    public static RegistrazioneAmministratoreDAO instance = null;

    private RegistrazioneAmministratoreDAO(){
        // Costruttore privato per il pattern Singleton
    }

    public static RegistrazioneAmministratoreDAO getInstance(){
        if(instance == null){
            instance = new RegistrazioneAmministratoreDAO();
        }
        return instance;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException{

        if (params.length != 1 || !(params[0] instanceof Credentials)) {
            throw new IllegalArgumentException("Parametri non validi per RegistrazioneAmministratoreDAO. Previsto un oggetto Credentials.");
        }
        Credentials credenziali = (Credentials) params[0];


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

            System.err.println("SQLState: " + sqlException.getSQLState());
            System.err.println("Error Code: " + sqlException.getErrorCode());


            if (sqlException.getSQLState() != null && sqlException.getSQLState().startsWith("23")) {
                throw new DAOException("Registrazione fallita: L'username '" + credenziali.getUsername() + "' è già in uso. " + sqlException.getMessage(), sqlException);
            }

            throw new DAOException("Errore SQL durante la registrazione amministratore: " + sqlException.getMessage(), sqlException);
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
            } catch (SQLException e) {
                System.err.println("Errore nella chiusura del CallableStatement: " + e.getMessage());
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Errore nella chiusura della Connection: " + e.getMessage());

            }
        }
    }
}