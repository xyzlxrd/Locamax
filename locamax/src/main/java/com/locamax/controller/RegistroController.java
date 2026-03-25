package com.locamax.controller;

import com.locamax.repository.ClienteRepository;
import com.locamax.util.SceneManager;
import com.locamax.util.Validacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegistroController implements Initializable {

    @FXML private TextField  txtNome;
    @FXML private Label      lblErroNome;
    @FXML private TextField  txtCPF;
    @FXML private Label      lblErroCPF;
    @FXML private TextField  txtTelefone;
    @FXML private Label      lblErroTelefone;
    @FXML private TextField  txtCNH;
    @FXML private Label      lblErroCNH;
    @FXML private DatePicker dpNascimento;
    @FXML private Label      lblErroNascimento;
    @FXML private TextField  txtCEP;
    @FXML private Label      lblErroCEP;
    @FXML private TextField  txtPais;
    @FXML private Label      lblErroPais;
    @FXML private TextField  txtEstado;
    @FXML private Label      lblErroEstado;
    @FXML private TextField  txtCidade;
    @FXML private Label      lblErroCidade;
    @FXML private TextField  txtBairro;
    @FXML private Label      lblErroBairro;
    @FXML private TextField  txtRua;
    @FXML private Label      lblErroRua;
    @FXML private TextField  txtNumeroResid;
    @FXML private Label      lblErroNumeroResid;

    @FXML
    public void onRegistrar(ActionEvent event) throws IOException {
        boolean valido = validarCampos();
        if (!valido) return;

        String nascimento = dpNascimento.getValue().toString();
        int idPessoa = ClienteRepository.registrarCliente(
                txtNome.getText(), txtCPF.getText(), txtTelefone.getText(),
                txtCEP.getText(), txtCidade.getText(), txtRua.getText(),
                txtNumeroResid.getText(), txtBairro.getText(), nascimento,
                txtEstado.getText(), txtPais.getText(), txtCNH.getText());

        if (idPessoa != 0) {
            FXMLLoader loader = SceneManager.abrirNovaJanelaComLoader(
                    "/fxml/RegistroCredenciaisView.fxml", "LOCAMAX - Criar Acesso");
            RegistroCredenciaisController controller = loader.getController();
            controller.setIdPessoa(idPessoa);
            ((Stage) txtNome.getScene().getWindow()).close();
        } else {
            new Alert(Alert.AlertType.ERROR, "Erro ao realizar o cadastro. Verifique os dados e tente novamente.").showAndWait();
        }
    }

    private boolean validarCampos() {
        boolean ok = true;

        ok &= validar(Validacao.isNome(txtNome.getText()),         lblErroNome,       "Nome inválido.");
        ok &= validar(Validacao.isCPF(txtCPF.getText()),           lblErroCPF,        "CPF inválido.");
        ok &= validar(Validacao.isTelefone(txtTelefone.getText()), lblErroTelefone,   "Telefone inválido.");
        ok &= validar(Validacao.isCNH(txtCNH.getText()),           lblErroCNH,        "CNH inválida.");
        ok &= validar(Validacao.isCEP(txtCEP.getText()),           lblErroCEP,        "CEP inválido.");
        ok &= validar(Validacao.isNomeLocal(txtPais.getText()),    lblErroPais,       "País inválido.");
        ok &= validar(Validacao.isNomeLocal(txtEstado.getText()),  lblErroEstado,     "Estado inválido.");
        ok &= validar(Validacao.isNomeLocal(txtCidade.getText()),  lblErroCidade,     "Cidade inválida.");
        ok &= validar(Validacao.isEnderecoTexto(txtRua.getText()), lblErroRua,        "Rua inválida.");
        ok &= validar(Validacao.isEnderecoTexto(txtBairro.getText()), lblErroBairro,  "Bairro inválido.");
        ok &= validar(Validacao.isEnderecoTexto(txtNumeroResid.getText()), lblErroNumeroResid, "Número inválido.");

        LocalDate nascimento = dpNascimento.getValue();
        if (nascimento == null) {
            lblErroNascimento.setText("Selecione a data de nascimento.");
            ok = false;
        } else {
            ok &= validar(Validacao.isIdadeValida(nascimento.toString()), lblErroNascimento, "Data de nascimento inválida.");
        }

        return ok;
    }

    private boolean validar(boolean condicao, Label label, String mensagemErro) {
        label.setText(condicao ? "" : mensagemErro);
        return condicao;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
}
