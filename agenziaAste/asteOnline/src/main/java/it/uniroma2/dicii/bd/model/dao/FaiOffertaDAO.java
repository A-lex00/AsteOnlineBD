package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Offerta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class FaiOffertaDAO implements GenericProcedureDAO<Boolean>{

    private static FaiOffertaDAO instance = null;

    public FaiOffertaDAO(){}

    public static FaiOffertaDAO getInstance(){
        if(instance == null){
            instance = new FaiOffertaDAO();
        }
        return instance;
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

            // *** LOGICA MODIFICATA PER VALORE MASSIMO IMPORTO (PUÒ ESSERE NULL) ***
            if (offerta.getValoreMassimoImporto() == null) {
                cs.setNull(4, Types.DOUBLE); // Imposta il parametro come NULL SQL di tipo DOUBLE
            } else {
                cs.setDouble(4, offerta.getValoreMassimoImporto()); // Imposta il valore Double
            }

            cs.execute();

            return true;
        } catch (SQLException sqlException) {
            throw new DAOException("Errore inserimento offerta : " + sqlException.getMessage(), sqlException);
        }
    }
}
