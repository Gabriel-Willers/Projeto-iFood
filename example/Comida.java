package com.example;

public class Comida extends Produto {

    private String tipoCozinha; // italiana, japonesa, etc.
    private boolean vegetariano;
    private boolean vegano;

    public Comida(String nome,
                  String descricao,
                  double preco,
                  int tempoPreparoMin,
                  String tipoCozinha,
                  boolean vegetariano,
                  boolean vegano) {
        super(nome, descricao, preco, tempoPreparoMin);
        this.tipoCozinha = tipoCozinha;
        this.vegetariano = vegetariano;
        this.vegano = vegano;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public boolean isVegetariano() {
        return vegetariano;
    }

    public void setVegetariano(boolean vegetariano) {
        this.vegetariano = vegetariano;
    }

    public boolean isVegano() {
        return vegano;
    }

    public void setVegano(boolean vegano) {
        this.vegano = vegano;
    }

    @Override
    public String toString() {
        return "[Comida] " + super.toString()
               + " - " + tipoCozinha
               + (vegetariano ? " | Veg." : "")
               + (vegano ? " | Vegano" : "");
    }
}
