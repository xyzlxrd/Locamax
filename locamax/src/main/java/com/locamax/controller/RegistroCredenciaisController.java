package com.locamax.controller;

import com.locamax.repository.ClienteRepository;
import com.locamax.util.SceneManager;
import com.locamax.util.Validacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroCredenciaisController {

    @FXML private TextField    txtUsuario;
    @FXML private PasswordField txtSenha;
    @FXML private TextField    txtEmail;
    @FXML private Label        lblErroEmail;

    private int idPessoa;

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    @FXML
    public void onFinalizar(ActionEvent event) throws IOException {
        String email   = txtEmail.getText();
        String usuario = txtUsuario.getText();
        String senha   = txtSenha.getText();

        if (!Validacao.isEmail(email)) {
            lblErroEmail.setText("E-mail inválido.");
            return;
        }
        lblErroEmail.setText("");

        boolean sucesso = ClienteRepository.registrarCredenciais(idPessoa, usuario, senha, email);

        if (sucesso) {
            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            SceneManager.trocarCena(stage, "/fxml/LoginView.fxml", "LOCAMAX - Login");
        } else {
            new Alert(Alert.AlertType.ERROR, "Erro ao criar as credenciais. Tente novamente.").showAndWait();
        }
    }
}
