package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.ListaOggetti;
import it.uniroma2.dicii.bd.model.domain.OggettoInAsta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VisualizzaAsteDAO implements GenericProcedureDAO<ListaOggetti>{

    private static VisualizzaAsteDAO instance = null;

    public VisualizzaAsteDAO(){}

    public static VisualizzaAsteDAO getInstance(){
        if(instance == null){
            instance = new VisualizzaAsteDAO();
        }

        return instance;
    }

    @Override
    public ListaOggetti execute(Object... params) throws DAOException{

        ListaOggetti listaOggetti =  new ListaOggetti();

        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement cs = connection.prepareCall("{call visualizza_aste_aperte()}");
            boolean flag = cs.execute();

            if(flag){

                ResultSet rs = cs.getResultSet();
                while (rs.next()){
                    OggettoInAsta oggettoInAsta = new OggettoInAsta();
                    oggettoInAsta.setCodice(rs.getString(1));
                    oggettoInAsta.setDescrizione(rs.getString(2));
                    oggettoInAsta.setPrezzoBase(rs.getDouble(3));
                    oggettoInAsta.setAltezza(rs.getDouble(4));
                    oggettoInAsta.setLunghezza(rs.getDouble(5));
                    oggettoInAsta.setLarghezza(rs.getDouble(6));
                    oggettoInAsta.setStato(rs.getString(7));
                    oggettoInAsta.setCategoria(rs.getString(8));
                    oggettoInAsta.setImportoOffertaMassima(rs.getDouble(9));
                    oggettoInAsta.setStatoAsta(rs.getString(10));
                    oggettoInAsta.setTempoMancante(rs.getString(11));
                    listaOggetti.addOggettoInAsta(oggettoInAsta);
                }
            }
        } catch (SQLException sqlException) {
            throw new DAOException("Errore lista oggetti: " + sqlException.getMessage());
        }
        return listaOggetti;
    }
}
