package it.uniroma2.dicii.bd.model.domain;

import java.util.ArrayList;
import java.util.List;

public class ListaCategorie {

    List<Categoria> lista = new ArrayList<>();

    public List<Categoria> getLista(){
        return this.lista;
    }

    public void addOggettoCategoria(Categoria categoria){
        this.lista.add(categoria);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Categoria categoria : lista){
            stringBuilder.append(categoria);
        }
        return stringBuilder.toString();
    }
    public boolean check(String stringa) {
        for (Categoria cat : lista) {
            if (cat.getNome() == stringa) {
                return true;
            }
        }
        return false;
    }
    public boolean isEmpty() {
            return lista.isEmpty();
    }
}
