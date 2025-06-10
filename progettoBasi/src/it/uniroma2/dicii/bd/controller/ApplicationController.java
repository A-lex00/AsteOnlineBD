package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.model.domain.Credentials;

public class ApplicationController implements Controller {
    Credentials cred;

    @Override
    public void start() {
        LoginController loginController = new LoginController();
        loginController.start();
        cred = loginController.getCred();

        if(cred.getRole() == null) {
            throw new RuntimeException("Invalid credentials");
        }

        switch(cred.getRole()) {
            case CLIENTE -> new ClienteController().start();
            case AMMINISTRATORE -> new AmministratoreController().start();
            default -> throw new RuntimeException("Invalid credentials");
        }
    }
}
