package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.ListaCategorie;
import it.uniroma2.dicii.bd.model.domain.OggettoInAsta; // Importa il modello OggettoInAsta
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class AggiungiOggettoView {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final ListaCategorie categorie = new ListaCategorie();



    public static class DatiOggettoInput {
        private String codice;

        private String stato;
        private String descrizione;
        private double prezzoBase;
        private double altezza;
        private double lunghezza;
        private double larghezza;
        private String categoria;
        private int durata;

        public String getCodice() { return codice; }
        public String getDescrizione() { return descrizione; }
        public double getPrezzoBase() { return prezzoBase; }
        public double getAltezza() { return altezza; }
        public double getLunghezza() { return lunghezza; }
        public double getLarghezza() { return larghezza; }
        public String getCategoria() { return categoria; }

        public void setStato(String stato) {
            this.stato = stato;
        }
         public String getStato(){
            return stato;
         }
        public int getDurata() { return durata; }

        public void setCodice(String codice) { this.codice = codice; }
        public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
        public void setPrezzoBase(double prezzoBase) { this.prezzoBase = prezzoBase; }
        public void setAltezza(double altezza) { this.altezza = altezza; }
        public void setLunghezza(double lunghezza) { this.lunghezza = lunghezza; }
        public void setLarghezza(double larghezza) { this.larghezza = larghezza; }
        public void setCategoria(String categoria) { this.categoria = categoria; }
        public void setDurata(int durata) { this.durata = durata; }
    }

    public enum ConfirmOption {
        SI, NO
    }

    public static void mostra() {
        System.out.println("\n--- AGGIUNGI NUOVO OGGETTO IN ASTA ---");
        System.out.println("Inserisci i dettagli dell' oggetto");
    }

    public static DatiOggettoInput getDatiOggetto() throws IOException, IllegalArgumentException {
        DatiOggettoInput dati = new DatiOggettoInput();

        System.out.print("Inserisci Codice Oggetto (es. A01): ");
        String codice = reader.readLine();
        if (codice == null || codice.trim().isEmpty()) {
            throw new IllegalArgumentException("Il codice oggetto non può essere vuoto.");
        }
        dati.setCodice(codice);

        System.out.print("Inserisci Descrizione Oggetto: ");
        String descrizione = reader.readLine();
        if (descrizione == null || descrizione.trim().isEmpty()) {
            throw new IllegalArgumentException("La descrizione non può essere vuota.");
        }
        dati.setDescrizione(descrizione);

        System.out.print("Inserisci Prezzo Base dell'asta (es. 100.00): ");
        double prezzoBase;
        try {
            prezzoBase = Double.parseDouble(reader.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input non valido per il prezzo base. Inserisci un numero decimale.");
        }
        if (prezzoBase <= 0) {
            throw new IllegalArgumentException("Il prezzo base deve essere un valore positivo.");
        }
        dati.setPrezzoBase(prezzoBase);

        System.out.print("Inserisci Altezza (in cm, es. 15.5): ");
        double altezza;
        try {
            altezza = Double.parseDouble(reader.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input non valido per l'altezza. Inserisci un numero decimale.");
        }
        if (altezza < 0) {
            throw new IllegalArgumentException("L'altezza non può essere negativa.");
        }
        dati.setAltezza(altezza);

        System.out.print("Inserisci Lunghezza (in cm, es. 20.0): ");
        double lunghezza;
        try {
            lunghezza = Double.parseDouble(reader.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input non valido per la lunghezza. Inserisci un numero decimale.");
        }
        if (lunghezza < 0) {
            throw new IllegalArgumentException("La lunghezza non può essere negativa.");
        }
        dati.setLunghezza(lunghezza);

        System.out.print("Inserisci Larghezza (in cm, es. 5.0): ");
        double larghezza;
        try {
            larghezza = Double.parseDouble(reader.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input non valido per la larghezza. Inserisci un numero decimale.");
        }
        if (larghezza < 0) {
            throw new IllegalArgumentException("La larghezza non può essere negativa.");
        }
        dati.setLarghezza(larghezza);
    repeat:
        System.out.print("Inserisci Categoria (es. 'Elettronica',  'Libri'): ");
        String categoria = reader.readLine();
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoria non può essere vuota.");
        }
        dati.setCategoria(categoria);

        System.out.print("Inserisci Durata dell'asta in giorni (numero intero, es. 7): ");
        int durata;
        try {
            durata = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input non valido per la durata. Inserisci un numero intero.");
        }
        if (durata <= 0) {
            throw new IllegalArgumentException("La durata dell'asta deve essere un numero positivo di giorni.");
        }
        dati.setDurata(durata);
        System.out.println("Inserisci lo stato del oggetto esempio Nuovo,Ottime condizioni.. :");
        String stato;
        try {
            stato = reader.readLine();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input non valido per lo stato.");
        }
        if (stato.isEmpty()) {
            throw new IllegalArgumentException("Lo stato non può essere null! ");
        }
        dati.setStato(stato);
        return dati;


    }


    public static void displayDatiOggetto(OggettoInAsta oggetto) {
        System.out.println("\n--- Riepilogo Oggetto in Asta ---");
        System.out.println("Codice: " + oggetto.getCodice());
        System.out.println("Descrizione: " + oggetto.getDescrizione());
        System.out.println("Prezzo Base: " + String.format("%.2f", oggetto.getPrezzoBase()) + " €");
        System.out.println("Dimensioni (HxLxP): " + oggetto.getAltezza() + "x" + oggetto.getLunghezza() + "x" + oggetto.getLarghezza() + " cm");
        System.out.println("Categoria: " + oggetto.getCategoria());
        System.out.println("Durata Asta: " + oggetto.getDurata() + " giorni");
        System.out.print("Stato oggetto:" + oggetto.getStato());
        System.out.println("----------------------------------");
    }


    public static ConfirmOption getConfirmation(String prompt) throws IOException {
        while (true) {
            System.out.print(prompt + " (s/n): ");
            String input = reader.readLine().trim().toLowerCase();
            if (input.equals("s")) {
                return ConfirmOption.SI;
            } else if (input.equals("n")) {
                return ConfirmOption.NO;
            } else {
                System.out.println("Input non valido. Inserisci 's' per sì o 'n' per no.");
            }
        }
    }

    public static void displaySuccess(String message) {
        System.out.println("\n" + message);
    }


    public static void displayMessage(String message) {
        System.out.println(message);
    }


    public static void displayError(String message) {
        System.err.println("ERRORE: " + message);
    }
}