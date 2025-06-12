package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
public class CreaCategoriaDAO implements GenericProcedureDAO<Boolean>{
    private static CreaCategoriaDAO creaCategoriaDAO = null;

    private CreaCategoriaDAO() {}

    public static CreaCategoriaDAO getCreaCategoriaDAO() {
        if (creaCategoriaDAO == null) {
            creaCategoriaDAO = new CreaCategoriaDAO();
        }
        return creaCategoriaDAO;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException {
        Connection connection = null;
        CallableStatement cs = null;

        try {
            connection = ConnectionFactory.getConnection();
            String nomeCategoria;
            String macroCategoria = null;
            String callSql;

            if (params.length == 1 && params[0] instanceof String && !((String)params[0]).trim().isEmpty()) {
                nomeCategoria = ((String) params[0]).trim();
                callSql = "{call crea_categoria_senza_macrocategoria(?)}";
            } else if (params.length == 2 && params[0] instanceof String && !((String)params[0]).trim().isEmpty()
                    && params[1] instanceof String) {
                nomeCategoria = ((String) params[0]).trim();
                macroCategoria = ((String) params[1]).trim();
                callSql = "{call crea_categoria_con_macrocategoria(?,?)}";
            } else {
                throw new IllegalArgumentException("Parametri non validi ");
            }

            cs = connection.prepareCall(callSql);
            cs.setString(1, nomeCategoria);

            if (macroCategoria != null) {
                cs.setString(2, macroCategoria);
            }

            cs.execute();
            return true;

        } catch (SQLException sqlException) {
            System.err.println("Errore SQLState CreaCategoriaDAO " );

            if (sqlException.getSQLState() != null && sqlException.getSQLState().startsWith("45")) {
                throw new DAOException("Errore nella creazione della categoria " );
            }
            throw new DAOException("Errore SQL durante la creazione della categoria" );
        } catch (IllegalArgumentException e) {
            throw new DAOException("Errore di input nella creazione della categoria" );
        }
    }
}