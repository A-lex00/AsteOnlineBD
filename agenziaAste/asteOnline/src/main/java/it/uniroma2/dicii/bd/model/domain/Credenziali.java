package it.uniroma2.dicii.bd.model.domain;



public class Credenziali {

    private final String username;
    private final String password;
    private final Ruolo ruolo;

    public Credenziali(String username, String password, Ruolo ruolo){

        this.username = username;
        this.password = password;
        this.ruolo = ruolo;

    }
    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

}
