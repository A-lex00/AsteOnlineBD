package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class RegistrationView {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static Cliente getRegistrationData() throws IOException {
        Cliente cliente = new Cliente();
        System.out.println("--- MODULO DI REGISTRAZIONE ---");

        System.out.print("Nome: ");
        cliente.setNome(reader.readLine());

        System.out.print("Cognome: ");
        cliente.setCognome(reader.readLine());

        System.out.print("Codice Fiscale (16 caratteri): ");
        cliente.setCf(reader.readLine());
        java.sql.Date dataNascita = null;
        while (dataNascita == null) {
            System.out.print("Data di Nascita (YYYY-MM-DD): ");
            String dateStr = reader.readLine();
            try {
                dataNascita = java.sql.Date.valueOf(dateStr);
            } catch (DateTimeParseException e) {
                System.out.println("Formato data non valido. Inserisci YYYY-MM-DD.");
            }
        }
        cliente.setDataNascita(dataNascita);

        System.out.print("Città di Nascita: ");
        cliente.setCittàNascita(reader.readLine());

        System.out.print("Username: ");
        cliente.setNomeUtente(reader.readLine());

        System.out.print("Password (almeno 8 caratteri, una maiuscola, una minuscola, un numero, un carattere speciale): ");
        cliente.setPassword(reader.readLine());

        System.out.print("Via: ");
        cliente.setVia(reader.readLine());

        System.out.print("Numero Civico: ");
        cliente.setCivico(reader.readLine());

        System.out.print("Città: ");
        cliente.setCittà(reader.readLine());

        System.out.print("CAP: ");
        cliente.setCAP(reader.readLine());

        System.out.println("--- Fine Modulo ---");
        return cliente;
    }

    public static void displaySuccess() {
        System.out.println("Registrazione utente avvenuta con successo!");
    }

    public static void displayError(String message) {
        System.err.println("Errore di Registrazione: " + message);
    }
}
