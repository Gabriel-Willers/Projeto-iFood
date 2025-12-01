package com.example;

public class Bebida extends Produto {

    private int tamanhoMl;
    private boolean alcoolica;

    public Bebida(String nome,
                  String descricao,
                  double preco,
                  int tempoPreparoMin,
                  int tamanhoMl,
                  boolean alcoolica) {
        super(nome, descricao, preco, tempoPreparoMin);
        this.tamanhoMl = tamanhoMl;
        this.alcoolica = alcoolica;
    }

    public int getTamanhoMl() {
        return tamanhoMl;
    }

    public void setTamanhoMl(int tamanhoMl) {
        this.tamanhoMl = tamanhoMl;
    }

    public boolean isAlcoolica() {
        return alcoolica;
    }

    public void setAlcoolica(boolean alcoolica) {
        this.alcoolica = alcoolica;
    }

    @Override
    public String toString() {
        return "[Bebida] " + super.toString()
               + " - " + tamanhoMl + "ml"
               + (alcoolica ? " | Alcoólica" : " | Não alcoólica");
    }
}
