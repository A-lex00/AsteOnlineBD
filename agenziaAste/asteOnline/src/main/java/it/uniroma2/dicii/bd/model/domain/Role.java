package it.uniroma2.dicii.bd.model.domain;

public enum Role {

    CLIENTE("cliente"), // Ora accetta una stringa
    AMMINISTRATORE("amministratore"); // Ora accetta una stringa

    private final String dbRoleName; // Campo per memorizzare il nome del ruolo come nel DB

    private Role(String dbRoleName){
        this.dbRoleName = dbRoleName;
    }

    public static Role fromString(String roleString){
        if (roleString == null || roleString.trim().isEmpty()) {
            throw new IllegalArgumentException("La stringa del ruolo non può essere null o vuota.");
        }
        for(Role type : values()){

            if (type.getDbRoleName().equalsIgnoreCase(roleString.trim())){
                return type;
            }
        }

        throw new IllegalArgumentException("Ruolo non valido o sconosciuto: '" + roleString + "'");
    }
    public String getDbRoleName(){
        return dbRoleName;
    }
}