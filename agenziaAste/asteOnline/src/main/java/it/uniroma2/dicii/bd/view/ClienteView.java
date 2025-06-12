package it.uniroma2.dicii.bd.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ClienteView {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static int mostra() throws IOException {
        System.out.println("****************************************");
        System.out.println("* AUCTIONS AGENCY DASHBOARD: CLIENTE   *");
        System.out.println("****************************************\n");
        System.out.println("*** Seleziona un comando ***\n");
        System.out.println("1) Vedi aste aperte");
        System.out.println("2) Vedi aste in corso");
        System.out.println("3) Fai Offerta");
        System.out.println("4) Vedi oggetti acquistati");
        System.out.println("5) Quit");

        int choice = 0;
        boolean validInput = false;
        while (true) {
            System.out.print("Please enter your choice: ");
            String inputLine = reader.readLine(); //

            try {
                choice = Integer.parseInt(inputLine);
                if (choice >= 1 && choice <= 5) {
                    validInput = true;
                    break;
                } else {
                    System.out.println("Scelta non valida. Inserisci un numero tra 1 e 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Inserisci un numero intero.");
            }
        }

        return choice;
    }

    public static void messaggioBenvenuto(String username) {
        System.out.println("\nBenvenuto nella dashboard cliente, " + username + "!");
    }

    public static void messaggioArrivederci() {
        System.out.println("Effettuato il logout. Arrivederci!");
    }

    public static void mostraErrore(String message) {
        System.err.println("Errore: " + message);
    }

    public static void closeResources() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.err.println("Errore durante la chiusura delle risorse di input: " );
        }
    }
}