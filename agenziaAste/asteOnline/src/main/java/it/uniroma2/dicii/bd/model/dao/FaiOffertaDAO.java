package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Offerta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class FaiOffertaDAO implements GenericProcedureDAO<Boolean>{

    private static FaiOffertaDAO faiOffertaDAO = null;

    public FaiOffertaDAO(){}

    public static FaiOffertaDAO getFaiOffertaDAO(){
        if(faiOffertaDAO == null){
            faiOffertaDAO = new FaiOffertaDAO();
        }
        return faiOffertaDAO;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException {
        Offerta offerta = (Offerta) params[0];
        return insertOffer(offerta);
    }

    private Boolean insertOffer(Offerta offerta) throws DAOException{
        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement cs = connection.prepareCall("{call fai_offerta(?,?,?,?)}")) {

            cs.setString(1, offerta.getCliente());
            cs.setDouble(2, offerta.getImporto());
            cs.setString(3, offerta.getOggetto());

            if (offerta.getValoreMassimoImporto() == null) {
                cs.setNull(4, Types.DOUBLE);
            } else {
                cs.setDouble(4, offerta.getValoreMassimoImporto());
            }

            cs.execute();

            return true;
        } catch (SQLException sqlException) {
            throw new DAOException("Errore inserimento offerta  ");
        }
    }
}
