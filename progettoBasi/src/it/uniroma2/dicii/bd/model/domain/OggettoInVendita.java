package it.uniroma2.dicii.bd.model.domain;

public class OggettoInVendita {

    private int codice;


    private final double prezzoBase;
    private String descrizione;


    private final String stato;
    private final double larghezza;
    private final double lunghezza;
    private final double altezza;


    private final String inizioAsta;

    private  String categoria;
    private int numeroOfferte;
    private String tempoMancante;
    private double importoOffertaMassima;


    private final int durata;

    private String proprietario;


    public OggettoInVendita(int codice, double prezzoBase, String descrizione, String stato, double larghezza, double lunghezza, double altezza, String inizioAsta, String categoria, int durata) {
        this.codice = codice;
        this.prezzoBase = prezzoBase;
        this.descrizione = descrizione;
        this.stato = stato;
        this.larghezza = larghezza;
        this.lunghezza = lunghezza;
        this.altezza = altezza;
        this.inizioAsta = inizioAsta;
        this.categoria = categoria;
        this.durata = durata;
    }

    public String getInizioAsta() {
        return inizioAsta;
    }
    public int getDurata() {
        return durata;
    }

    public int getCodice(){
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


    public void setCodice(int codice){
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






}