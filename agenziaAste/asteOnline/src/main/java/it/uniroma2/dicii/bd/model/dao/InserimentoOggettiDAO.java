package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.OggettoInAsta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class InserimentoOggettiDAO implements GenericProcedureDAO<Boolean>{

    private static InserimentoOggettiDAO instance = null;

    private InserimentoOggettiDAO(){}

    public static InserimentoOggettiDAO getInstance(){
        if(instance == null){
            instance = new InserimentoOggettiDAO();
        }

        return instance;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException{

        OggettoInAsta oggettoInAsta = (OggettoInAsta) params[0];

        try{
            Connection connection = ConnectionFactory.getConnection();
            CallableStatement cs = connection.prepareCall("{call aggiungi_oggetto_in_asta(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setDate(1, oggettoInAsta.getInizioAsta());
            cs.setInt(2, oggettoInAsta.getDurata());
            cs.setString(3, oggettoInAsta.getDescrizione());
            cs.setDouble(4, oggettoInAsta.getPrezzoBase());
            cs.setDouble(5, oggettoInAsta.getAltezza());
            cs.setDouble(6, oggettoInAsta.getLunghezza());
            cs.setDouble(7,oggettoInAsta.getLarghezza());
            cs.setString(8,oggettoInAsta.getStato());
            cs.setString(9,oggettoInAsta.getCategoria());
            cs.setString(10,oggettoInAsta.getProprietario());
            cs.setDouble(11,oggettoInAsta.getImportoOffertaMassima());
            cs.setString(12, oggettoInAsta.getCodice());
            cs.execute();

        } catch (SQLException sqlException) {
            throw new DAOException("Errore nell'inserimento dell'oggetto : " + sqlException.getMessage());
        }
        return true;
    }
}
