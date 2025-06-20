package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Categoria;
import it.uniroma2.dicii.bd.model.domain.ListaCategorie;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CategoriaDAO implements GenericProcedureDAO<ListaCategorie> {

    private static CategoriaDAO categoriaDAO = null;

    private CategoriaDAO() {}

    public static CategoriaDAO getCategoriaDAO() {
        if (categoriaDAO == null) {
            categoriaDAO = new CategoriaDAO();
        }
        return categoriaDAO;
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

                    String nomeMacrocategoria = rs.getString("categoria_superiore");

                    if (nomeMacrocategoria == null || nomeMacrocategoria.trim().isEmpty()) {

                        listaCategorie.addOggettoCategoria(new Categoria(nomeCategoria));
                    } else {

                        listaCategorie.addOggettoCategoria(new Categoria(nomeCategoria, nomeMacrocategoria));
                    }
                }
            }
            return listaCategorie;

        } catch (SQLException sqlException) {
            System.err.println("SQLState ");
            System.err.println("Error Code ");
            throw new DAOException("Errore SQL durante il recupero delle categorie dalla tabella 'Categoria' ");
        } catch (Exception e) {
            throw new DAOException("Errore generico durante il recupero delle categorie " );
        }
    }
}

