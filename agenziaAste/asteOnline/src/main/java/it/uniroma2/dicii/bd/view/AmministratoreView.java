package it.uniroma2.dicii.bd.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AmministratoreView {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static int showMenu() throws IOException {
       System.out.println("****************************************");
       System.out.println("*  AUCTIONS AGENCY DASHBOARD : ADMIN   *");
       System.out.println("****************************************\n");
       System.out.println("*** Seleziona un comando ***\n");
       System.out.println("1) Crea Asta");
       System.out.println("2) Gestisci Categoria");
       System.out.print("3) Mostra Categoria \n");
       System.out.println("4) Quit");

        int choice = 0;
        boolean validInput = false;
        while (true) {
            System.out.print("Please enter your choice: ");
            String inputLine = reader.readLine(); //
            try {
                choice = Integer.parseInt(inputLine);
                if (choice >= 1 && choice <= 4) {
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

    public static void displayWelcomeMessage(String username) {
        System.out.println("\nBenvenuto nella dashboard amministratore, " + username + "!");
    }

    public static void displayLogoutMessage() {
        System.out.println("Effettuato il logout. Arrivederci!");
    }

    public static void displayGenericError(String message) {
        System.err.println("Errore: " + message);
    }

    public static void closeResources() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.err.println("Errore durante la chiusura delle risorse di input: " + e.getMessage());
        }
    }
}
