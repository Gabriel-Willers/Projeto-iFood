package com.example;

import java.util.List;

public interface GerenciadorDeProdutos {

    void adicionarProduto(Produto p);

    void removerProduto(Produto p);

    //void editarProduto(Produto antigo, Produto novo);
    List<Produto> listarProdutos();
}
