package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Cliente;
import it.uniroma2.dicii.bd.model.domain.ListaOggetti;
import it.uniroma2.dicii.bd.model.domain.OggettoInAsta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisuallizzaAsteInCorsoDAO implements GenericProcedureDAO<ListaOggetti>{

    private static VisuallizzaAsteInCorsoDAO visuallizzaAsteInCorsoDAO = null;

    private VisuallizzaAsteInCorsoDAO(){}

    public static VisuallizzaAsteInCorsoDAO getVisuallizzaAsteInCorsoDAO(){
        if(visuallizzaAsteInCorsoDAO == null){
            visuallizzaAsteInCorsoDAO = new VisuallizzaAsteInCorsoDAO();
        }
        return visuallizzaAsteInCorsoDAO;
    }
    @Override
    public ListaOggetti execute(Object... params) throws DAOException{
        ListaOggetti listaOggetti = new ListaOggetti();
        Cliente cliente = (Cliente) params[0];
        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement cs = connection.prepareCall("{call visualizza_asta_in_corso(?)}");
            cs.setString(1, cliente.getUsername());
            boolean flag = cs.execute();
            if(flag){

                ResultSet rs = cs.getResultSet();
                while(rs.next()){

                    OggettoInAsta oggettoInAsta = new OggettoInAsta();
                    oggettoInAsta.setCodice(rs.getString(1));
                    oggettoInAsta.setDescrizione(rs.getString(2));
                    oggettoInAsta.setPrezzoBase(rs.getDouble(3));
                    oggettoInAsta.setInizioAsta(rs.getDate(4));
                    oggettoInAsta.setAltezza(rs.getDouble(5));
                    oggettoInAsta.setLunghezza(rs.getDouble(6));
                    oggettoInAsta.setLarghezza(rs.getDouble(7));
                    oggettoInAsta.setDurata(rs.getInt(8));
                    oggettoInAsta.setStato(rs.getString(9));
                    oggettoInAsta.setCategoria(rs.getString(10));
                    oggettoInAsta.setImportoOffertaMassima(rs.getDouble(11));
                    oggettoInAsta.setFineAsta(rs.getString(12));
                    listaOggetti.addOggettoInAsta(oggettoInAsta);
                }
            }
        } catch (SQLException sqlException) {
            throw new DAOException("Errore lista oggetti in asta con offerte ");
        }
        return listaOggetti;
    }
}
