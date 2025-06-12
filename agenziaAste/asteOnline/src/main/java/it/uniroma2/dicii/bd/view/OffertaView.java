package it.uniroma2.dicii.bd.view;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class OffertaView {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static class DatiOffertaInput {
        private String codiceOggetto;
        private double importoOfferta;
        private Double controffertaAutomatica; // *** CAMBIATO A Double ***
        private String usernameUtente;

        // ... (getter e setter rimangono simili, solo i tipi cambiano) ...
        public Double getControffertaAutomatica() { return controffertaAutomatica; }
        public void setControffertaAutomatica(Double controffertaAutomatica) { this.controffertaAutomatica = controffertaAutomatica; }

        public String getCodiceOggetto() { return codiceOggetto; }
        public void setCodiceOggetto(String codiceOggetto) { this.codiceOggetto = codiceOggetto; }

        public double getImportoOfferta() { return importoOfferta; }
        public void setImportoOfferta(double importoOfferta) { this.importoOfferta = importoOfferta; }

        public String getUsernameUtente() { return usernameUtente; }
        public void setUsernameUtente(String usernameUtente) { this.usernameUtente = usernameUtente; }
    }


    public static DatiOffertaInput getDatiOfferta(String usernameUtenteLoggato) throws IOException, IllegalArgumentException {
        DatiOffertaInput dati = new DatiOffertaInput();
        dati.setUsernameUtente(usernameUtenteLoggato);

        System.out.println("\n--- EFFETTUA UN'OFFERTA ---");

        System.out.print("Codice dell'asta (es. A001): ");
        dati.setCodiceOggetto(reader.readLine().trim());

        System.out.print("Importo della tua offerta: ");
        double importo = readDoubleInput(); // Questo metodo va bene per l'importo base
        if (importo <= 0) {
            throw new IllegalArgumentException("L'importo dell'offerta deve essere positivo.");
        }
        dati.setImportoOfferta(importo);

        // *** LOGICA MODIFICATA PER LA CONTROFFERTA AUTOMATICA ***
        System.out.print("Importo per controfferta automatica (premi INVIO per nessuna controfferta, o inserisci 0 se vuoi esplicitamente zero): ");
        // Usiamo readDoubleInputNullable per leggere un Double che può essere null
        Double controfferta = readDoubleInputNullable();

        // Se l'utente ha inserito qualcosa che si è convertito a un numero
        if (controfferta != null) {
            if (controfferta < 0) {
                throw new IllegalArgumentException("L'importo della controfferta automatica non può essere negativo.");
            }
        }
        // Se controfferta è null, significa che l'utente non ha inserito nulla,
        // quindi il valore sarà null nel DatiOffertaInput e nel modello Offerta.
        dati.setControffertaAutomatica(controfferta);

        return dati;
    }

    // Metodo esistente per leggere double non null
    private static double readDoubleInput() throws IOException {
        while (true) {
            try {
                String inputLine = reader.readLine();
                return Double.parseDouble(inputLine);
            } catch (NumberFormatException e) {
                System.out.print("Input non valido. Inserisci un numero valido: ");
            }
        }
    }

    // *** NUOVO METODO: readDoubleInputNullable ***
    private static Double readDoubleInputNullable() throws IOException {
        while (true) {
            String inputLine = reader.readLine().trim();
            if (inputLine.isEmpty()) {
                return null; // L'utente ha premuto INVIO, quindi è null
            }
            try {
                return Double.parseDouble(inputLine);
            } catch (NumberFormatException e) {
                System.out.print("Input non valido. Premi INVIO per lasciare vuoto o inserisci un numero valido: ");
            }
        }
    }


    public static void displayOffertaSuccesso() {
        System.out.println("Offerta effettuata con successo!");
    }

    public static void displayOffertaError(String message) {
        System.err.println("Errore nell'offerta: " + message);
    }

    public static void displayError(String message) {
        System.err.println(message);
    }

    public static void closeReader() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.err.println("Errore durante la chiusura del reader: " + e.getMessage());
        }
    }
}