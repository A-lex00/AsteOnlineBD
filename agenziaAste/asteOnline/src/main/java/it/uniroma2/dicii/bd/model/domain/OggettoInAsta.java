package it.uniroma2.dicii.bd.model.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class OggettoInAsta {

    private String codice;


    private  double prezzoBase;
    private String descrizione;


    private  String stato;



    private String statoAsta;
    private  double larghezza;
    private  double lunghezza;
    private  double altezza;


    private Date inizioAsta;

    private String fineAsta;

    private  String categoria;
    private int numeroOfferte;
    private String tempoMancante;
    private double importoOffertaMassima;
    private int durata;
    private String proprietario;
    public OggettoInAsta(Timestamp inizioAsta, int durata, String descrizione, double prezzoBase, double altezza, double lunghezza, double larghezza, String stato, String categoria, String proprietario, double offertaMassima,String codice) {
    }
    public OggettoInAsta(){}
    public Date getInizioAsta() {
        return inizioAsta;
    }
    public int getDurata() {
        return durata;
    }

    public String getCodice(){
            return codice;
    }
    public double getPrezzoBase() {
        return prezzoBase;
    }
    public String getDescrizione(){
            return descrizione;
    }

    public String getStato() {
        return stato;
    }

    public double getLarghezza() {
        return larghezza;
    }

    public double getLunghezza() {
        return lunghezza;
    }

    public double getAltezza() {
        return altezza;
    }




    public String getCategoria() {
        return categoria;
    }

    public int getNumeroOfferte() {
        return numeroOfferte;
    }
    public String getTempoMancante() {
        return tempoMancante;
    }
    public double getImportoOffertaMassima() {
        return importoOffertaMassima;
    }
    public String getProprietario() {
        return proprietario;
    }


    public void setCodice(String codice){
        this.codice = codice; // tecnicamente non la uso perchè il codice è relativo al DB
    }
    public void setDescrizione(String descrizione){
            this.descrizione = descrizione;

    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public void setNumeroOfferte(int numeroOfferte) {
        this.numeroOfferte = numeroOfferte;
    }



    public void setTempoMancante(String tempoMancante) {
        this.tempoMancante = tempoMancante;
    }


    public void setImportoOffertaMassima(double importoOffertaMassima) {
        this.importoOffertaMassima = importoOffertaMassima;
    }


    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }


    public void setPrezzoBase(double prezzoBase) {
        this.prezzoBase = prezzoBase;
    }

    public void setAltezza(Double altezza) {
        this.altezza = altezza;
    }

    public void setLunghezza(double lunghezza) {
        this.lunghezza = lunghezza;
    }

    public void setLarghezza(double larghezza) {
        this.larghezza = larghezza;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
    public String getStatoAsta() {
        return statoAsta;
    }

    public void setStatoAsta(String statoAsta) {
        this.statoAsta = statoAsta;
    }

    public void setAltezza(double altezza) {
        this.altezza = altezza;
    }

    public void setInizioAsta(Date inizioAsta) {
        this.inizioAsta = inizioAsta;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getFineAsta() {
        return fineAsta;
    }

    public void setFineAsta(String fineAsta) {
        this.fineAsta = fineAsta;
    }

    public double getOffertaMassima() {
        return importoOffertaMassima;
    }
}