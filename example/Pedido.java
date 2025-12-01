package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido implements GerenciadorDeProdutos {

    private Cliente cliente;
    private Restaurante restaurante;
    private final List<Produto> itens = new ArrayList<>();

    public Pedido(Cliente cliente, Restaurante restaurante) {
        this.cliente = cliente;
        this.restaurante = restaurante;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    // ====== IMPLEMENTAÇÃO DA INTERFACE ======
    @Override
    public void adicionarProduto(Produto p) {
        itens.add(p);
    }

    @Override
    public void removerProduto(Produto p) {
        itens.remove(p);
    }

    @Override
    public List<Produto> listarProdutos() {
        return Collections.unmodifiableList(itens);
    }

    // ====== MÉTODOS ESPECÍFICOS DO PEDIDO ======

    public double calcularTotal() {
        double total = 0.0;
        for (Produto p : itens) {
            total += p.getPreco();
        }
        return total;
    }
public void finalizarPedido() {
    System.out.println("----- Pedido Finalizado -----");
    System.out.println("Cliente: " + cliente.getNome());
    System.out.println("Restaurante: " + restaurante.getNome());

    System.out.println("Itens:");
    for (Produto p : itens) {
        System.out.println(" - " + p.getNome() + " | R$ " + p.getPreco());
    }

    System.out.println("Total: R$ " + calcularTotal());
    System.out.println("Tempo estimado: " + calcularTempoEstimadoMin() + " min");
    System.out.println("------------------------------");
}
    public int calcularTempoEstimadoMin() {
        int max = 0;
        for (Produto p : itens) {
            if (p.getTempoPreparoMin() > max) {
                max = p.getTempoPreparoMin();
            }
        }
        return max;
    }

    @Override
    public String toString() {
        return "Pedido de " + cliente.getNome()
                + " no restaurante " + restaurante.getNome()
                + " | itens=" + itens.size()
                + " | total=R$ " + calcularTotal()
                + " | espera ~ " + calcularTempoEstimadoMin() + " min";
    }
}
