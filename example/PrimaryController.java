package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class PrimaryController {

    @FXML private Button btnCliente;
    @FXML private Button btnDonoR;
    @FXML private TextField textEmailLogin;
    @FXML private TextField textSenhaLogin;
    @FXML private Label lblMsgLogin;
    private User user; 

   
    @FXML
    private void switchToSecondary(ActionEvent e) {
        try {
            Object src = e.getSource();
            if (src == btnCliente) {
                user = new Cliente();
                System.out.println("Indo para cadastro de cliente...");
            } else if (src == btnDonoR) {
                user = new DonoR();
                System.out.println("Indo para cadastro de dono de restaurante...");
            } else {
                throw new IllegalStateException("Fonte do evento desconhecida");
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/secondary.fxml"));
            Parent root = loader.load();
            SecondaryController secondaryController = loader.getController();

          
            secondaryController.setUser(user);

           
            Scene scene = btnCliente.getScene(); 
            scene.setRoot(root);

        

        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
    }
    @FXML
public void loginUser(){
     String email = Usuarios.normalizarEmail(textEmailLogin.getText());
    String senha = textSenhaLogin.getText();
    lblMsgLogin.setText("");

    boolean ok = Usuarios.get().verificarConta(email, senha);
    if (!ok) {
        lblMsgLogin.setText("Email ou senha incorretos.");
        return;
    }

    User logado = Usuarios.get().buscarPorEmail(email);
    if (logado == null) {
        lblMsgLogin.setText("Erro interno: usuário não encontrado.");
        return;
    }

    try {
        if (logado instanceof Cliente) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cliente.fxml"));
            Parent root = loader.load();
            ClienteController controller = loader.getController();
            controller.setCliente((Cliente) logado);
            Scene scene = btnCliente.getScene();
            scene.setRoot(root);

        } else if (logado instanceof DonoR) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/dono.fxml"));
            Parent root = loader.load();
            DonoController controller = loader.getController();
            controller.setDono((DonoR) logado);
            Scene scene = btnCliente.getScene();
            scene.setRoot(root);

        } else {
            lblMsgLogin.setText("Tipo de usuário não suportado.");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}




}