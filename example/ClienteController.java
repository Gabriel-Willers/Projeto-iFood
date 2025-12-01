package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;

public class ClienteController {

    @FXML private Label lblCliente;
    @FXML private ComboBox<Restaurante> comboRestaurantes;
    @FXML private ListView<Produto> listCardapio;
    @FXML private ListView<Produto> listPedido;
    @FXML private Label lblTotal;
    @FXML private Label lblTempo;
    @FXML private Label lblMsgCliente;

    private Cliente cliente;

    // Pedido concreto (pra calcular total/tempo)
    private Pedido pedidoAtual;

    // Interface para adicionar/remover/listar
    private GerenciadorDeProdutos gerenciadorPedido;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        lblCliente.setText("Cliente: " + cliente.getNome());

        comboRestaurantes.getItems().setAll(Restaurantes.get().listarTodos());
    }

    @FXML
    private void selecionarRestaurante() {
        Restaurante selecionado = comboRestaurantes.getSelectionModel().getSelectedItem();
        if (selecionado == null) return;

        listCardapio.getItems().setAll(selecionado.listarProdutos());
        listPedido.getItems().clear();

        // Cria o pedido e associa à interface
        pedidoAtual = new Pedido(cliente, selecionado);
        gerenciadorPedido = pedidoAtual; // polimorfismo aqui

        atualizarResumo();
    }

    @FXML
    private void adicionarItem() {
            lblMsgCliente.setText("");
        if (gerenciadorPedido == null) {
           lblMsgCliente.setText("Selecione um restaurante primeiro.");
            return;
        }
        Produto selecionado = listCardapio.getSelectionModel().getSelectedItem();
        if (selecionado == null){
        lblMsgCliente.setText("Selecione um item do cardápio.");
        return;}

        gerenciadorPedido.adicionarProduto(selecionado);
        listPedido.getItems().setAll(gerenciadorPedido.listarProdutos());
        atualizarResumo();
    }

    @FXML
    private void removerItem() {
        if (gerenciadorPedido == null) return;
        Produto selecionado = listPedido.getSelectionModel().getSelectedItem();
        if (selecionado == null) return;

        gerenciadorPedido.removerProduto(selecionado);
        listPedido.getItems().setAll(gerenciadorPedido.listarProdutos());
        atualizarResumo();
    }

    private void atualizarResumo() {
        if (pedidoAtual == null) {
            lblTotal.setText("Total: R$ 0,00");
            lblTempo.setText("Tempo estimado: 0 min");
            return;
        }

        double total = pedidoAtual.calcularTotal();
        int tempo = pedidoAtual.calcularTempoEstimadoMin();
        lblTotal.setText(String.format("Total: R$ %.2f", total));
        lblTempo.setText("Tempo estimado: " + tempo + " min");
    }

    @FXML
    private void voltarAoLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/primary.fxml"));
        Parent root = loader.load();
        lblCliente.getScene().setRoot(root);
    }

    @FXML
private void finalizarPedido() {
     lblMsgCliente.setText("");
    if (pedidoAtual == null) {
        lblMsgCliente.setText("Selecione um restaurante e monte um pedido primeiro.");
        return;
    }

    if (gerenciadorPedido.listarProdutos().isEmpty()) {
         lblMsgCliente.setText("Não é possível finalizar pedido vazio.");
        return;
    }

    // Finaliza o pedido 
    pedidoAtual.finalizarPedido();

    // Armazena o pedido no repositório global
    Pedidos.get().registrar(pedidoAtual);

    // Feedback visual na interface
    lblMsgCliente.setText("Pedido finalizado com sucesso!");
    lblTotal.setText("Total: R$ 0,00");
    lblTempo.setText("Tempo estimado: 0 min");

    // Limpa lista da interface
    listPedido.getItems().clear();

    // "Zera" o pedido
    pedidoAtual = null;
    gerenciadorPedido = null;

    System.out.println("Pedido salvo com sucesso!");
}

}
