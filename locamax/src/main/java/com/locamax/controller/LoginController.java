package com.locamax.controller;

import com.locamax.repository.ClienteRepository;
import com.locamax.util.SceneManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private TextField    txtUsuario;
    @FXML private PasswordField txtSenha;
    @FXML private Text          txtMensagemErro;

    @FXML
    public void onLogin(ActionEvent event) throws IOException {
        String usuario = txtUsuario.getText();
        String senha   = txtSenha.getText();

        if (ClienteRepository.autenticar(usuario, senha)) {
            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            SceneManager.trocarCena(stage, "/fxml/PrincipalView.fxml", "LOCAMAX");
        } else {
            txtMensagemErro.setText("Usuário ou senha incorretos.");
        }
    }

    @FXML
    public void onIrParaCadastro(ActionEvent event) throws IOException {
        Stage stage = (Stage) txtUsuario.getScene().getWindow();
        SceneManager.trocarCena(stage, "/fxml/RegistroView.fxml", "LOCAMAX - Cadastro");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
}
