package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.Cliente;
import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.Role;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClienteRegistrationView {
        public static Credentials register() throws IOException{

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Inserisci un username: ");
            String nomeUtente = reader.readLine();
            System.out.println("Inserisci una password: ");
            String password = reader.readLine();

            return new Credentials(nomeUtente, password, Role.CLIENTE);
        }

        public static Cliente setUserInfo(Credentials credentials) throws IOException {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Inserisci il codice fiscale: ");
            String cf = reader.readLine();
            System.out.println("Inserisci il tuo nome: ");
            String nome = reader.readLine();
            System.out.println("Inserisci il tuo cognome: ");
            String cognome = reader.readLine();

            java.sql.Date dataNascita = null;

            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false);
                System.out.println("Inserisci la tua data di nascita: [yyyy-mm-dd]");
                Date date = dateFormat.parse(reader.readLine());
                dataNascita = new java.sql.Date(date.getTime());
            } catch (ParseException e) {
                System.out.println("Errore nell'inserimento della data di nascita");
                e.printStackTrace();
                System.exit(1);
            }

            System.out.println("Inserisci la tua città di nascita: ");
            String cittàNascita = reader.readLine();
            System.out.println("Inserisci il numero della tua carta di credito: ");
            String numeroCarta = reader.readLine();

            java.sql.Date dataScadenza = null;

            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false);
                System.out.println("Inserisci la data di scadenza della carta di credito: [yyyy-mm-dd]");
                Date date = dateFormat.parse(reader.readLine());
                dataScadenza = new java.sql.Date(date.getTime());
            } catch (ParseException e) {
                System.out.println("Errore nell'inserimento della data di scadenza della carta di credito: ");
                e.printStackTrace();
                System.exit(1);
            }

            System.out.println("Inserisci il cvv della carta di credito: ");
            String cvv = reader.readLine();
            System.out.println("Inserisci la via dove consegnare i tuoi acquisti: ");
            String via = reader.readLine();
            System.out.println("Inserisci il numero civico:");
            String civico = reader.readLine();
            System.out.println("Inseirsci la città dove consegnare i tuoi acquisti: ");
            String città = reader.readLine();
            System.out.println("Inserisci il cap del comune della consegna: ");
            String cap = reader.readLine();


            Cliente cliente = new Cliente(
                    nome,
                    cognome,
                    cf,
                    cittàNascita,
                    credentials.getPassword(),
                    dataNascita,
                    credentials.getUsername(),
                    via,
                    cap,
                    città,
                    civico,
                    cvv,
                    numeroCarta,
                    dataScadenza
        );
            return cliente;
        }

}
