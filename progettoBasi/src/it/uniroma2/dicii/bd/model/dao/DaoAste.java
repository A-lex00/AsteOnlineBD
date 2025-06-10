package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.exception.DataAccessException;
import it.uniroma2.dicii.bd.model.dao.factory.ConnectionFactory;
import it.uniroma2.dicii.bd.model.domain.Cliente;
import it.uniroma2.dicii.bd.model.domain.OggettoInVendita;

// import it.uniroma2.dicii.bd.model.domain.Utente; // Se hai una classe Utente per il proprietario

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp; // Per convertire LocalDateTime in Timestamp

public class OggettoInVenditaDAO {

    // Costruttore privato per impedire l'instanziazione, preferendo metodi statici
    private OggettoInVenditaDAO() {}

    /**
     * Recupera una lista di tutte le aste attualmente aperte dal database.
     * @return Una lista di oggetti OggettoInVendita che rappresentano le aste aperte.
     * @throws DAOException Se si verifica un errore durante l'accesso al database.
     */
    public static List<OggettoInVendita> getAsteAperte() throws DAOException {
        List<OggettoInVendita> asteAperte = new ArrayList<>();
        String call = "{CALL visuallizza_aste_aperte()}";

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement cs = connection.prepareCall(call);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                asteAperte.add(mapResultSetToOggettoInVendita(rs));
            }
        } catch (SQLException e) {
            System.err.println("Errore DAO durante il recupero delle aste aperte: " + e.getMessage());
            throw new DAOException("Impossibile recuperare le aste aperte dal database.", e);
        } catch (IllegalArgumentException e) {
            System.err.println("Errore di conversione enum o dati non validi: " + e.getMessage());
            throw new DAOException("Dati non validi nel database per stato/categoria/altri campi dell'oggetto.", e);
        }
        return asteAperte;
    }
    /**
     * Crea un nuovo oggetto in vendita nel database, avviando una nuova asta.
     * Utilizza una stored procedure 'createAuction'.
     *
     * @param oggetto Il nuovo oggetto OggettoInVendita da persistere.
     * @throws DAOException Se si verifica un errore durante l'accesso al database.
     */
    public static void createOggettoInVendita(OggettoInVendita oggetto) throws DateTimeParseException,DAOException {
        String call = "{CALL aggiungi_oggetto_in_asta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        //preparazione valori
        Timestamp inizioAsta;

        try{
            String inizioAstaJava = oggetto.getInizioAsta(); // Questo è un java.time.LocalDateTime
            inizioAsta = Timestamp.valueOf(inizioAstaJava);
        }
        catch (DateTimeParseException e){
            System.err.println("Errore nella conversione di inizio asta");
            throw new DateTimeException("Errore nella conversione di inizio asta" + e);}

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement cs = connection.prepareCall(call)) {

            cs.setTimestamp(1, inizioAsta);
            cs.setInt(2,oggetto.getDurata());
            cs.setString(3,oggetto.getDescrizione());
            cs.setDouble(4,oggetto.getPrezzoBase());
            cs.setDouble(5,oggetto.getAltezza());
            cs.setDouble(6,oggetto.getLunghezza());
            cs.setDouble(7,oggetto.getLarghezza());
            cs.setString(8,oggetto.getStato());
            cs.setString(9,oggetto.getCategoria());
            cs.setString(10, "null");
            cs.setString(11,"null");
            cs.execute();
            System.out.println("Oggetto in vendita creato con successo nel DB.");

        } catch (SQLException e) {
            System.err.println("Errore DAO durante la creazione dell'oggetto in vendita: " + e.getMessage());
            throw new DAOException("Impossibile creare il nuovo oggetto in vendita nel database.", e);
        }
    }
    public static void getAsteInCorso(Cliente cliente) throws DAOException {
        String call = "{CALL visualizza_asta_in_corso(?)}";

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement cs = connection.prepareCall(call)) {

            cs.setString(1, cliente.getNomeUtente());



            cs.execute();
        } catch (SQLException e) {
            System.err.println("Errore DAO durante l'aggiornamento dell'offerta massima: " + e.getMessage());
            throw new DAOException("Impossibile aggiornare l'offerta massima nel database.", e);
        }
    }
    // --- Metodo helper per mappare un ResultSet a un oggetto OggettoInVendita ---
    private static OggettoInVendita mapResultSetToOggettoInVendita(ResultSet rs) throws SQLException {
        int codice = rs.getInt("codice");
        double prezzoBase = rs.getDouble("prezzoBase");
        String descrizione = rs.getString("descrizione");
        StatoOggetto stato = StatoOggetto.valueOf(rs.getString("stato").toUpperCase());
        double larghezza = rs.getDouble("larghezza");
        double lunghezza = rs.getDouble("lunghezza");
        double altezza = rs.getDouble("altezza");

        LocalDateTime inizioAsta = rs.getTimestamp("inizioAsta").toLocalDateTime();
        Duration durataAsta = Duration.ofSeconds(rs.getLong("durataSecondi")); // Assumo 'durataSecondi' nel DB

        CategoriaOggetto categoria = CategoriaOggetto.valueOf(rs.getString("categoria").toUpperCase());

        OggettoInVendita oggetto = new OggettoInVendita(
                codice, prezzoBase, descrizione, stato, larghezza, lunghezza, altezza,
                inizioAsta, durataAsta, categoria
        );

        // Popola gli attributi mutabili che potrebbero essere nel ResultSet
        oggetto.setNumeroOfferte(rs.getInt("numeroOfferte"));
        oggetto.setImportoOffertaMassima(rs.getDouble("importoOffertaMassima"));

        // Se hai un proprietario (Utente), dovresti recuperarlo qui dal suo ID e impostarlo nell'oggetto.
        // int idProprietario = rs.getInt("idProprietario");
        // Utente proprietario = UtenteDAO.getUtenteById(idProprietario); // Richiede un UtenteDAO
        // oggetto.setProprietario(proprietario);

        return oggetto;
    }
}