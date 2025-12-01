package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import java.io.IOException;

public class DonoController {

    @FXML private Label lblRestaurante;
    @FXML private Label lblMsgDono;
    @FXML private TextField textNomeProduto;
    @FXML private TextField textDescricao;
    @FXML private TextField textPreco;
    @FXML private TextField textTempo;
    @FXML private TextField textTipoCozinha;
    @FXML private CheckBox checkVegetariano;
    @FXML private CheckBox checkVegano;
    @FXML private TextField textTamanhoMl;
    @FXML private CheckBox checkAlcoolica;
    @FXML private ListView<Produto> listCardapio;

    private DonoR dono;
    private Restaurante restaurante;

    public void setDono(DonoR dono) {
        this.dono = dono;
        this.restaurante = dono.getRestaurante();
        if (restaurante != null) {
            lblRestaurante.setText("Restaurante: " + restaurante.getNome() + " - " + restaurante.getEndereco());
            atualizarLista();
        } else {
            lblRestaurante.setText("Restaurante: (não definido)");
        }
    }

    private void atualizarLista() {
        if (restaurante == null) return;
        listCardapio.getItems().setAll(restaurante.listarProdutos());
    }

    @FXML
    private void adicionarComida() {
        if (restaurante == null) {
           lblMsgDono.setText("Restaurante não definido para este dono.");
            return;
        }

        try {
            lblMsgDono.setText("");
            String nome = textNomeProduto.getText();
            String desc = textDescricao.getText();
            double preco = Double.parseDouble(textPreco.getText());
            int tempo = Integer.parseInt(textTempo.getText());
            String tipoCozinha = textTipoCozinha.getText();
            boolean veg = checkVegetariano.isSelected();
            boolean vegan = checkVegano.isSelected();

            Comida c = new Comida(nome, desc, preco, tempo, tipoCozinha, veg, vegan);
            restaurante.adicionarProduto(c);
            atualizarLista();
            limparCampos();
            lblMsgDono.setText("Comida adicionada com sucesso!");
        } catch (NumberFormatException e) {
            lblMsgDono.setText("Erro de formato em preço/tempo.");
        }
    }

    @FXML
    private void adicionarBebida() {
        if (restaurante == null) {
            lblMsgDono.setText("Restaurante não definido para este dono.");
            return;
        }

        try {
            String nome = textNomeProduto.getText();
            String desc = textDescricao.getText();
            double preco = Double.parseDouble(textPreco.getText());
            int tempo = Integer.parseInt(textTempo.getText());
            int tamanhoMl = Integer.parseInt(textTamanhoMl.getText());
            boolean alcool = checkAlcoolica.isSelected();

            Bebida b = new Bebida(nome, desc, preco, tempo, tamanhoMl, alcool);
            restaurante.adicionarProduto(b);
            atualizarLista();
            limparCampos();
            lblMsgDono.setText("Bebida adicionada com sucesso!");
        } catch (NumberFormatException e) {
            lblMsgDono.setText("Erro de formato em preço/tempo/tamanho.");
        }
    }
@FXML
    public void initialize() {
        // Quando trocar o item selecionado na lista, carrega nos campos
        listCardapio.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, selecionado) -> carregarCamposDoProduto(selecionado)
        );
    }

    private void carregarCamposDoProduto(Produto p) {
        if (p == null) return;

        // Campos comuns
        textNomeProduto.setText(p.getNome());
        textDescricao.setText(p.getDescricao());
        textPreco.setText(String.valueOf(p.getPreco()));
        textTempo.setText(String.valueOf(p.getTempoPreparoMin()));

        // Limpa campos específicos
        textTipoCozinha.clear();
        checkVegetariano.setSelected(false);
        checkVegano.setSelected(false);
        textTamanhoMl.clear();
        checkAlcoolica.setSelected(false);

        // Se for Comida, preenche os específicos de comida
        if (p instanceof Comida c) {
            textTipoCozinha.setText(c.getTipoCozinha());
            checkVegetariano.setSelected(c.isVegetariano());
            checkVegano.setSelected(c.isVegano());
        }

        // Se for Bebida, preenche os específicos de bebida
        if (p instanceof Bebida b) {
            textTamanhoMl.setText(String.valueOf(b.getTamanhoMl()));
            checkAlcoolica.setSelected(b.isAlcoolica());
        }
    }
    @FXML
    private void limparCampos() {
        textNomeProduto.clear();
        textDescricao.clear();
        textPreco.clear();
        textTempo.clear();
        textTipoCozinha.clear();
        checkVegetariano.setSelected(false);
        checkVegano.setSelected(false);
        textTamanhoMl.clear();
        checkAlcoolica.setSelected(false);
    }
@FXML
private void atualizarItem() {
    if (restaurante == null) {
        lblMsgDono.setText("Restaurante não definido para este dono.");
        return;
    }

    Produto selecionado = listCardapio.getSelectionModel().getSelectedItem();
    if (selecionado == null) {
        lblMsgDono.setText("Nenhum item selecionado para atualizar.");
        return;
    }

    try {
        // Atualiza os campos comuns
        selecionado.setNome(textNomeProduto.getText());
        selecionado.setDescricao(textDescricao.getText());
        selecionado.setPreco(Double.parseDouble(textPreco.getText()));
        selecionado.setTempoPreparoMin(Integer.parseInt(textTempo.getText()));

        // Se for Comida, atualiza os campos de comida
        if (selecionado instanceof Comida c) {
            c.setTipoCozinha(textTipoCozinha.getText());
            c.setVegetariano(checkVegetariano.isSelected());
            c.setVegano(checkVegano.isSelected());
        }

        // Se for Bebida, atualiza os campos de bebida
        if (selecionado instanceof Bebida b) {
            int tamanhoMl = 0;
            if (!textTamanhoMl.getText().isBlank()) {
                tamanhoMl = Integer.parseInt(textTamanhoMl.getText());
            }
            b.setTamanhoMl(tamanhoMl);
            b.setAlcoolica(checkAlcoolica.isSelected());
        }

        // Recarrega a lista para refletir o toString atualizado
        atualizarLista();

        System.out.println("Item atualizado: " + selecionado);

    } catch (NumberFormatException e) {
        lblMsgDono.setText("Erro de formato em preço/tempo/tamanho. Verifique os valores.");
    }
}

    @FXML
    private void removerItem() {
        if (restaurante == null) {
            lblMsgDono.setText("Restaurante não definido para este dono.");
            return;
        }
        Produto selecionado = listCardapio.getSelectionModel().getSelectedItem();
        if (selecionado == null) return;

        restaurante.removerProduto(selecionado);
        lblMsgDono.setText("Item removido com sucesso.");
        atualizarLista();
        limparCampos();
    }
    @FXML
    private void voltarAoLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/primary.fxml"));
        Parent root = loader.load();
        // pega cena atual por qualquer nó
        lblRestaurante.getScene().setRoot(root);
    }
}
