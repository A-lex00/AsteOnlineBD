package it.uniroma2.dicii.bd.view;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class OffertaView {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static class DatiOffertaInput {
        private String codiceOggetto;
        private double importoOfferta;
        private Double controffertaAutomatica;
        private String usernameUtente;

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
        double importo = readDoubleInput();
        if (importo <= 0) {
            throw new IllegalArgumentException("Importo dell'offerta non valido.");
        }
        dati.setImportoOfferta(importo);

        System.out.print("Importo per controfferta automatica (premi INVIO per impostare nessuna  controfferta): ");

        Double controfferta = readDoubleInputNullable();

        if (controfferta != null) {
            if (controfferta < 0) {
                throw new IllegalArgumentException("L'importo della controfferta automatica non valido.");
            }
        }
        dati.setControffertaAutomatica(controfferta);

        return dati;
    }

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


    private static Double readDoubleInputNullable() throws IOException {
        while (true) {
            String inputLine = reader.readLine().trim();
            if (inputLine.isEmpty()) {
                return null;
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

    public static void closeReader() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.err.println("Errore durante la chiusura del reader: ");
        }
    }
}