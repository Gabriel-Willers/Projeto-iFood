package com.example;

public abstract class Produto {

    private String nome;
    private String descricao;
    private double preco;
    /** tempo de preparo em minutos */
    private int tempoPreparoMin;

    public Produto(String nome, String descricao, double preco, int tempoPreparoMin) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tempoPreparoMin = tempoPreparoMin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getTempoPreparoMin() {
        return tempoPreparoMin;
    }

    public void setTempoPreparoMin(int tempoPreparoMin) {
        this.tempoPreparoMin = tempoPreparoMin;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + preco + " (" + tempoPreparoMin + " min)";
    }
}