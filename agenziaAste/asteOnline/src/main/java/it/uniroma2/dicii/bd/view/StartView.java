package it.uniroma2.dicii.bd.view;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartView {

        private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        public static int getTypeOfUser() throws IOException {
            int choice = -1;
            boolean validInput = false;

            while (!validInput) {
                System.out.println("************************************");
                System.out.println("*   WELCOME IN AUCTIONS AGENCY     *");
                System.out.println("************************************");
                System.out.println("--- Seleziona la tipologia di utente ---");
                System.out.println("1. Cliente");
                System.out.println("2. Amministratore");
                System.out.println("0. Esci");
                System.out.print("La tua scelta: ");

                String input = reader.readLine();
                try {
                    choice = Integer.parseInt(input);
                    if (choice >= 0 && choice <= 2) {
                        validInput = true;
                    } else {
                        System.out.println("Scelta non valida. Inserisci 1 per Cliente, 2 per Amministratore o 0 per uscire.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero.");
                }
            }
            return choice;
        }
        public static void displayInvalidInputError() {
            System.err.println("Errore: Input non valido. Riprova.");
        }
        public static void displayExitMessage() {
            System.out.println("Benvenuto! ");
        }

    public static void displayError(String s) {
        System.err.println("Errore: " +s);
    }
}

