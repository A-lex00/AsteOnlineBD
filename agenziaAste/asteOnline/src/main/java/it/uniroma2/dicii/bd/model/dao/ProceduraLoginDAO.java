package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Credenziali;
import it.uniroma2.dicii.bd.model.domain.Ruolo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class ProceduraLoginDAO implements GenericProcedureDAO<Credenziali>{

    private static ProceduraLoginDAO proceduraLoginDAO = null;
    public ProceduraLoginDAO(){}

    public static ProceduraLoginDAO getProceduraLoginDAO(){
        if(proceduraLoginDAO == null){
            proceduraLoginDAO = new ProceduraLoginDAO();
        }
        return proceduraLoginDAO;
    }

    @Override
    public Credenziali execute(Object... params) throws DAOException{

        String username = (String) params[0];
        String password = (String) params[1];
        String ruolo;

        try{
            Connection connection = ConnectionFactory.getConnection();
            CallableStatement cs = connection.prepareCall("{call login(?,?,?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.executeQuery();
            ruolo = cs.getString(3);

        } catch (SQLException sqlException) {
            throw new DAOException("Errore SQL LoginDAO");
        }
        return new Credenziali(username, password, Ruolo.fromString(ruolo) );
    }

}
