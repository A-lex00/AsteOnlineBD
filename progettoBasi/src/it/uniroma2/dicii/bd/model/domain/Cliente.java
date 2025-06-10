package it.uniroma2.dicii.bd.model.domain;

import java.util.List;

public class Cliente {
    private final String nome;
    private final String cognome;
    private final String cf;

    private final String cittàNascita;
    private final String dataNascita;

    private final String nomeUtente;

    private final String via;

    private String cap;


    private String città;

    private final String civico;

    private String cvv;
    private String numeroCarta;
    private final String dataScadenza;

    private String password;

    private List<OggettoInVendita> oggettiPosseduti;


    public Cliente(String nome, String cognome, String cf, String cittàNascita, String dataNascita, String nomeUtente, String via, String cap, String città, String civico, String cvv, String numeroCarta, String dataScadenza) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.cittàNascita = cittàNascita;
        this.dataNascita = dataNascita;
        this.nomeUtente = nomeUtente;
        this.via = via;
        this.cap = cap;
        this.città = città;
        this.civico = civico;
        this.cvv = cvv;
        this.numeroCarta = numeroCarta;
        this.dataScadenza = dataScadenza;
    }



    public String getCittà() {
        return città;
    }
    public void setCittà(String città){
            this.città = città;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCF() {
        return cf;
    }

    public String getCittàNascita() {
        return cittàNascita;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public String getVia() {
        return via;
    }

    public String getCAP() {
        return cap;
    }

    public void setCAP(String CAP) {
        this.cap = CAP;
    }

    public String getCivico() {
        return civico;
    }

    public String getCVV() {
        return cvv;
    }

    public void setCVV(String CVV) {
        this.cvv = CVV;
    }

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public String getPassword(){
            return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return this.nome + " " + this.cognome;
    }

    public List<OggettoInVendita> getOggettiPosseduti() {
        return oggettiPosseduti;
    }

    public void setOggettiPosseduti(OggettoInVendita oggetto) {
        this.oggettiPosseduti.add(oggetto);
    }
}
