package it.uniroma2.dicii.bd.model.domain;

public enum Ruolo {

    CLIENTE("cliente"),
    AMMINISTRATORE("amministratore");

    private final String ruolo;

    private Ruolo(String ruolo){
        this.ruolo = ruolo;
    }

    public static Ruolo fromString(String role){
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Il ruolo non può essere nullo.");
        }
        for(Ruolo type : values()){

            if (type.getRuolo().equalsIgnoreCase(role.trim())){
                return type;
            }
        }

        throw new IllegalArgumentException("Ruolo non valido");
    }
    public String getRuolo(){
        return ruolo;
    }
}