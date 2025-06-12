package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Categoria;
import it.uniroma2.dicii.bd.model.domain.ListaCategorie;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CategoriaDAO implements GenericProcedureDAO<ListaCategorie> {

    private static CategoriaDAO instance = null;

    private CategoriaDAO() {}

    public static CategoriaDAO getInstance() {
        if (instance == null) {
            instance = new CategoriaDAO();
        }
        return instance;
    }

    @Override
    public ListaCategorie execute(Object... params) throws DAOException {
        Connection connection = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        ListaCategorie listaCategorie = new ListaCategorie();

        try {
            connection = ConnectionFactory.getConnection();
            String callSql = "{call recupera_categorie()}";

            cs = connection.prepareCall(callSql);
            boolean hadResults = cs.execute();

            if (hadResults) {
                rs = cs.getResultSet();
                while (rs.next()) {
                    String nomeCategoria = rs.getString("nome_categoria");
                    // getStri  ng ritorna null se il valore nel DB è NULL
                    String nomeMacrocategoria = rs.getString("categoria_superiore");

                    if (nomeMacrocategoria == null || nomeMacrocategoria.trim().isEmpty()) {
                        // Categoria di primo livello (senza macrocategoria)
                        listaCategorie.addOggettoCategoria(new Categoria(nomeCategoria));
                    } else {
                        // Categoria con macrocategoria
                        listaCategorie.addOggettoCategoria(new Categoria(nomeCategoria, nomeMacrocategoria));
                    }
                }
            }
            return listaCategorie;

        } catch (SQLException sqlException) {
            System.err.println("SQLState: " + sqlException.getSQLState());
            System.err.println("Error Code: " + sqlException.getErrorCode());
            throw new DAOException("Errore SQL durante il recupero delle categorie dalla tabella 'Categoria': " + sqlException.getMessage(), sqlException);
        } catch (Exception e) {
            throw new DAOException("Errore generico durante il recupero delle categorie: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (cs != null) cs.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Errore nella chiusura delle risorse del database in CategoriaDAO: " + e.getMessage());
            }
        }
    }
}

