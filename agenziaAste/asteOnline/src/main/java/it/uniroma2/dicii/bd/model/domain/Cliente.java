package it.uniroma2.dicii.bd.model.domain;

import java.sql.Date;
import java.util.List;

public class Cliente {
    private String nome;
    private  String cognome;
    private  String cf;

    private String password;

    private  String cittàNascita;
    private java.sql.Date dataNascita;

    private  String nomeUtente;

    private  String via;

    private String cap;


    private String città;

    private  String civico;

    private String cvv;
    private String numeroCarta;
    private  java.sql.Date dataScadenza;


    private List<OggettoInAsta> oggettiPosseduti;

    public Cliente(String nome, String cognome, String cf, String cittàNascita, String password, Date dataNascita, String username, String via, String cap, String città, String civico, String cvv, String numeroCarta, Date dataScadenza) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.cittàNascita = cittàNascita;
        this.password = password;
        this.dataNascita = dataNascita;
        this.nomeUtente = username;
        this.via = via;
        this.cap = cap;
        this.civico = civico;
        this.città = città;
        this.cvv = cvv;
        this.numeroCarta = numeroCarta;
        this.dataScadenza = dataScadenza;
    }

    public Cliente(){
    }


    public String getCittà() {
        return città;
    }
    public void setCittà(String città){
            this.città = città;
    }

    public void setCf(String CF){
        this.cf = CF;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;

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

    public java.sql.Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(java.sql.Date dataNascita){
            this.dataNascita = dataNascita;
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

    public java.sql.Date getDataScadenza() {
        return dataScadenza;
    }

    public String getPassword(){
            return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<OggettoInAsta> getOggettiPosseduti() {
        return oggettiPosseduti;
    }

    public void setOggettiPosseduti(OggettoInAsta oggetto) {
        this.oggettiPosseduti.add(oggetto);
    }

    @Override
    public String toString(){

        System.out.println(this.getCF()+ " " + this.getNome() + " " +
                this.getCognome() + " " + this.getDataNascita() + " " +
                this.getCittàNascita() + " " + this.getNumeroCarta() + " " +
                this.getDataScadenza() + " " + this.getCVV() + " " +
                this.getVia() + " " + this.getCittà() + " " +
                this.getCAP() + " " + this.getNomeUtente() + " " +
                this.getPassword() + " " + this.getCivico());

        return null;
    }

    public void setCognome(String s) {
    }

    public void setCittàNascita(String cittàNascita) {
        this.cittàNascita = cittàNascita;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }
}
