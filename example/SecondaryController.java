package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
public class SecondaryController {

    // Blocos
    @FXML private AnchorPane paneComum;
    @FXML private AnchorPane paneExtraDono;
    @FXML private Label lblMsgCadastro;
    // Comuns
    @FXML private TextField textNome;
    @FXML private TextField textEmail;
    @FXML private TextField textSenha;

    // Extras do Dono
    @FXML private TextField textNomeRest;
    @FXML private TextField textLocal;

    private User user;

    public void setUser(User user) {
        this.user = user;
        configurarPaineis();
    }
    private void configurarPaineis() {
        boolean ehDono = user instanceof DonoR;
        // comuns sempre aparecem
        paneComum.setVisible(true);
        paneComum.setManaged(true);
        // extras só para Dono
        paneExtraDono.setVisible(ehDono);
        paneExtraDono.setManaged(ehDono);
    }

@FXML
private void cadastrarUser() {
    lblMsgCadastro.setText("");

     if (user == null) {
        lblMsgCadastro.setText("Erro interno: tipo de usuário não definido.");
        return;
    }
    String nome = textNome.getText().trim();
    String emailDigitado = textEmail.getText().trim();
    String senha = textSenha.getText().trim();

  
    // 1) Campos básicos obrigatórios
    if (nome.isEmpty() || emailDigitado.isEmpty() || senha.isEmpty()) {
       lblMsgCadastro.setText("Preencha todos os campos.");
        return;
    }

    // 2) Padronizar e validar @gmail.com
    String emailNorm = Usuarios.normalizarEmail(emailDigitado);
    if (emailNorm == null || !emailNorm.endsWith("@gmail.com")) {
      lblMsgCadastro.setText("Use um e-mail válido.");
        return;
    }

    // 3) Impedir e-mail duplicado
    if (Usuarios.get().verificarPorEmail(emailNorm)) {
     lblMsgCadastro.setText("Já existe uma conta com esse e-mail.");
        return;
    }

    // 4) Preenche dados comuns do usuário
    user.setNome(nome);
    user.setEmail(emailNorm);
    user.setSenha(senha);

    // 5) Se for DonoR, campos extras obrigatórios
    if (user instanceof DonoR dono) {
        String nomeRest = textNomeRest.getText().trim();
        String local = textLocal.getText().trim();

        if (nomeRest.isEmpty() || local.isEmpty()) {
         lblMsgCadastro.setText("Preencha todos os campos.");
            return;
        }

        dono.setNomeRest(nomeRest);
        dono.setLocal(local);

        // cria o restaurante associado a esse dono
        Restaurante restaurante = new Restaurante(
                dono.getNomeRest(),
                dono.getLocal(),
                dono
        );
        dono.setRestaurante(restaurante);

        // salva restaurante no repositório global
        Restaurantes.get().add(restaurante);

        System.out.println("Dono: " + dono.getNome() + " | Email: " + dono.getEmail()
                + " | Rest=" + dono.getNomeRest() + " | Local=" + dono.getLocal());
    } else {
        // Cliente (não tem campos extras obrigatórios)
        System.out.println("Cliente: " + user.getNome() + " | Email: " + user.getEmail());
    }

    // 6) Só chega aqui se tudo passou nas validações
    Usuarios.get().add(user);
    lblMsgCadastro.setTextFill(javafx.scene.paint.Color.GREEN);
    lblMsgCadastro.setText("Cadastro concluído com sucesso!");
}

    @FXML
    private void switchToPrimary() throws IOException {
        System.out.println(">> Voltar clicado");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/primary.fxml"));
        Parent root = loader.load();
        // pega a cena atual por QUALQUER nó visível
        paneComum.getScene().setRoot(root);
    }
}
