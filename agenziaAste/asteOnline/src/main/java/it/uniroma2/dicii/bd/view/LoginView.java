package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.Credenziali;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginView {
    public static Credenziali authenticate() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("username: ");
        String username = reader.readLine();
        System.out.print("password: ");
        String password = reader.readLine();

        return new Credenziali(username, password, null);
    }
}
