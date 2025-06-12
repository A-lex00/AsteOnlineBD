package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListaOggettiAcquistatiDAO implements GenericProcedureDAO<ListaOggetti>{

    private static ListaOggettiAcquistatiDAO listaOggettiAcquistatiDAO = null;
    private ListaOggettiAcquistatiDAO(){}

    public static ListaOggettiAcquistatiDAO getListaOggettiAcquistatiDAO(){
        if(listaOggettiAcquistatiDAO == null){
            listaOggettiAcquistatiDAO = new ListaOggettiAcquistatiDAO();
        }

        return listaOggettiAcquistatiDAO;
    }

    @Override
    public ListaOggetti execute(Object... params) throws DAOException{
        ListaOggetti listaOggetti = new ListaOggetti();
        Cliente cliente = (Cliente) params[0];

        try {

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement cs = connection.prepareCall("{call visualizza_oggetti_aggiudicati(?)}");
            cs.setString(1, cliente.getUsername());
            boolean flag = cs.execute();
            if(flag){

                ResultSet rs = cs.getResultSet();
                while(rs.next()){

                    OggettoInAsta oggettoInAsta = new OggettoInAsta();
                    oggettoInAsta.setDescrizione(rs.getString(1));
                    oggettoInAsta.setImportoOffertaMassima(rs.getDouble(2));
                    oggettoInAsta.setAltezza(rs.getDouble(3));
                    oggettoInAsta.setLunghezza(rs.getDouble(4));
                    oggettoInAsta.setLarghezza(rs.getDouble(5));
                    oggettoInAsta.setStatoAsta(rs.getString(6));
                    listaOggetti.addOggettoInAsta(oggettoInAsta);
                }
            }

        } catch (SQLException sqlException) {
            throw new DAOException("Errore in lista degli oggetti acquistati");
        }
        return  listaOggetti;
    }

}
