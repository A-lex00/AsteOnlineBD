package it.uniroma2.dicii.bd.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegistrazioneAmmView {


    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static class DatiRegistrazioneInput {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static void mostra() {
        System.out.println("\n--- REGISTRAZIONE NUOVO AMMINISTRATORE ---");
        System.out.println("Inserisci le credenziali per il nuovo account amministratore.");
    }

    public static DatiRegistrazioneInput getDatiRegistrazione() throws IOException, IllegalArgumentException {
        DatiRegistrazioneInput dati = new DatiRegistrazioneInput();

        System.out.print("Inserisci Username Amministratore: ");
        String username = reader.readLine();
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("L'username non può essere vuoto.");
        }
        dati.setUsername(username);

        System.out.print("Inserisci Password Amministratore: ");
        String password = reader.readLine();
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("La password non può essere vuota.");
        }
        dati.setPassword(password);

        return dati;
    }

    public static void messaggioSuccesso(String message) {
        System.out.println("\n" + message);
    }

    public static void mostraErrore(String message) {
        System.err.println("ERRORE: " + message);
    }


}