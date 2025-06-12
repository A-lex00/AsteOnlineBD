package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
public class CreaCategoriaDAO implements GenericProcedureDAO<Boolean>{
    private static CreaCategoriaDAO instance = null;

    private CreaCategoriaDAO() {}

    public static CreaCategoriaDAO getInstance() {
        if (instance == null) {
            instance = new CreaCategoriaDAO();
        }
        return instance;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException {
        Connection connection = null;
        CallableStatement cs = null;

        try {
            connection = ConnectionFactory.getConnection();
            String nomeCategoria;
            String macroCategoria = null; // Inizializzato a null per indicare l'assenza di macrocategoria
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
                throw new IllegalArgumentException("Parametri non validi per CreaCategoriaDAO. Attesi (String nomeCategoria) o (String nomeCategoria, String macroCategoria). I nomi non possono essere vuoti.");
            }

            cs = connection.prepareCall(callSql);
            cs.setString(1, nomeCategoria);

            if (macroCategoria != null) {
                cs.setString(2, macroCategoria);
            }

            cs.execute();
            return true;

        } catch (SQLException sqlException) {
            System.err.println("SQLState: " + sqlException.getSQLState());
            System.err.println("Error Code: " + sqlException.getErrorCode());

            if (sqlException.getSQLState() != null && sqlException.getSQLState().startsWith("45")) {
                throw new DAOException("Errore nella creazione della categoria: " + sqlException.getMessage(), sqlException);
            }
            throw new DAOException("Errore SQL durante la creazione della categoria: " + sqlException.getMessage(), sqlException);
        } catch (IllegalArgumentException e) {
            throw new DAOException("Errore di input nella creazione della categoria: " + e.getMessage(), e);
        }
    }
}