package it.uniroma2.dicii.bd.model.domain;

public class Offerta {

    private String cliente;
    private String oggetto;
    private double importo;
    private Double valoreMassimoImporto;

    public Offerta(){}

    public Offerta(String cliente, String oggetto, double importo, Double valoreMassimoImporto){ //

        this.cliente = cliente;
        this.oggetto = oggetto;
        this.importo = importo;
        this.valoreMassimoImporto = valoreMassimoImporto;

    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public void setValoreMassimoImporto(Double valoreMassimoImporto) {
        this.valoreMassimoImporto = valoreMassimoImporto;
    }

    public Double getValoreMassimoImporto() {
        return valoreMassimoImporto;
    }
}