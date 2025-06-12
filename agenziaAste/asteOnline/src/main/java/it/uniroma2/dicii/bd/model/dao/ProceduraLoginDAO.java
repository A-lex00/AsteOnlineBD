package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.Role;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class ProceduraLoginDAO implements GenericProcedureDAO<Credentials>{

    private static ProceduraLoginDAO instance = null;
    public ProceduraLoginDAO(){}

    public static ProceduraLoginDAO getInstance(){
        if(instance == null){
            instance = new ProceduraLoginDAO();
        }
        return instance;
    }

    @Override
    public Credentials execute(Object... params) throws DAOException{

        String username = (String) params[0];
        String password = (String) params[1];
        String role;

        try{
            Connection connection = ConnectionFactory.getConnection();
            CallableStatement cs = connection.prepareCall("{call login(?,?,?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.executeQuery();
            role = cs.getString(3);

        } catch (SQLException sqlException) {
            throw new DAOException("Login error: " + sqlException.getMessage());
        }
        return new Credentials(username, password, Role.fromString(role) );
    }

}
