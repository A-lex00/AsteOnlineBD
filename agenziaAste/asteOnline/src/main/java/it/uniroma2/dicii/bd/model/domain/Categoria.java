package it.uniroma2.dicii.bd.model.domain;

public class Categoria {

    private String nome;
    private String macrocategoria;

    public Categoria() {
    }

    public Categoria(String nome, String macrocategoria) {
        this.nome = nome;
        this.macrocategoria = macrocategoria;
    }

    public Categoria(String nomeCategoria) {
        this.nome = nomeCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMacrocategoria() {
        return macrocategoria;
    }

    public void setMacrocategoria(String macrocategoria) {
        this.macrocategoria = macrocategoria;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.nome);
        return this.nome;
    }
}
