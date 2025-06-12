package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Cliente;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RegistrazioneClienteDAO implements GenericProcedureDAO<Boolean>{

    private static RegistrazioneClienteDAO registrazioneClienteDAO = null;
    public RegistrazioneClienteDAO(){}

    public static RegistrazioneClienteDAO getInstance(){
        if(registrazioneClienteDAO == null){
            registrazioneClienteDAO = new RegistrazioneClienteDAO();
        }
        return registrazioneClienteDAO;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException{
        Cliente cliente = (Cliente) params[0];

        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement cs = connection.prepareCall("{call registrazione(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, cliente.getNome());
            cs.setString(2, cliente.getCognome());
            cs.setString(3, cliente.getCF());
            cs.setDate(4, cliente.getDataNascita());
            cs.setString(5, cliente.getCittàNascita());
            cs.setString(6, cliente.getPassword());
            cs.setString(7, cliente.getNumeroCarta());
            cs.setString(8 ,cliente.getCVV());
            cs.setDate(9, cliente.getDataScadenza());
            cs.setString(10, cliente.getVia());
            cs.setString(11, cliente.getCittà());
            cs.setString(12, cliente.getCAP());
            cs.setString(13, cliente.getUsername());
            cs.setString(14, cliente.getCivico());
            cs.executeQuery();

        }catch (SQLException e){
            throw new DAOException("Errore nella registrazione Cliente ");
        }
        return true;
    }

}
