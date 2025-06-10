package it.uniroma2.dicii.bd.model.domain;

public class Offerte {


    private double controffertaMassima;
    private double valoreEffettivo;
    private String cliente;
    private String oggettoInVendita;



    public Offerte(String cliente, String oggettoInVendita, double valoreEffettivo, double controffertaMassima) {
        this.cliente = cliente;
        this.oggettoInVendita = oggettoInVendita;
        this.valoreEffettivo = valoreEffettivo;
        this.controffertaMassima = controffertaMassima;
    }


    public double getControffertaMassima() {
        return controffertaMassima;
    }

    public void setControffertaMassima(double controffertaMassima) {
        this.controffertaMassima = controffertaMassima;
    }

    public double getValoreEffettivo() {
        return valoreEffettivo;
    }

    public void setValoreEffettivo(double valoreEffettivo) {
        this.valoreEffettivo = valoreEffettivo;
    }



    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getOggettoInVendita() {
        return oggettoInVendita;
    }

    public void setOggettoInVendita(String oggettoInVendita) {
        this.oggettoInVendita = oggettoInVendita;
    }
}
