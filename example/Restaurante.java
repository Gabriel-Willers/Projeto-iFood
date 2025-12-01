package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurante implements GerenciadorDeProdutos {

    private String nome;
    private String endereco;
    private DonoR dono;
    private final List<Produto> cardapio = new ArrayList<>();

    public Restaurante(String nome, String endereco, DonoR dono) {
        this.nome = nome;
        this.endereco = endereco;
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public DonoR getDono() {
        return dono;
    }

    public void setDono(DonoR dono) {
        this.dono = dono;
    }

    @Override
    public void adicionarProduto(Produto p) {
        cardapio.add(p);
    }

    @Override
    public void removerProduto(Produto p) {
        cardapio.remove(p);
    }

    @Override
    public List<Produto> listarProdutos() {
        return Collections.unmodifiableList(cardapio);
    }
   
    @Override
    public String toString() {
        return "Restaurante: " + nome + " - " + endereco;
    }
}
