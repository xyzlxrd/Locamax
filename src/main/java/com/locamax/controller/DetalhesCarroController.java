package com.locamax.controller;

import com.locamax.model.Carro;
import com.locamax.model.Carrinho;
import com.locamax.util.SceneManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class DetalhesCarroController {

    @FXML private ImageView imgCarro;
    @FXML private Text      txtNome;
    @FXML private Text      txtCategoria;
    @FXML private Text      txtDescricao;
    @FXML private Text      txtPreco;
    @FXML private Text      txtPortas;
    @FXML private Text      txtAssentos;
    @FXML private Text      txtCambio;
    @FXML private Text      txtArCondicionado;
    @FXML private Text      txtAirbag;
    @FXML private Text      txtCapacidadeTanque;
    @FXML private Text      txtMotor;

    private Carro carro;

    public void setCarro(Carro carro) {
        this.carro = carro;

        txtNome.setText(carro.getNome());
        txtCategoria.setText(carro.getNomeCategoria());
        txtDescricao.setText(carro.getDescricao());
        txtDescricao.setWrappingWidth(375);
        txtPreco.setText(String.format("R$ %.2f", carro.getValorDiaria()));
        txtPortas.setText(String.valueOf(carro.getQuantidadePortas()));
        txtAssentos.setText(String.valueOf(carro.getQuantidadeAssentos()));
        txtCambio.setText(carro.getCambio());
        txtArCondicionado.setText(carro.isArCondicionado() ? "Sim" : "Não");
        txtAirbag.setText(carro.isAirbag() ? "Sim" : "Não");
        txtCapacidadeTanque.setText(carro.getCapacidadeTanque() + "L");
        txtMotor.setText(carro.getPotenciaMotor());
        imgCarro.setImage(new Image(getClass().getResourceAsStream("/img/modelo_" + carro.getIdModelo() + ".png")));
    }

    @FXML
    private void onAlugar(ActionEvent event) {
        boolean adicionado = Carrinho.getInstance().adicionar(carro);

        if (adicionado) {
            ((Stage) txtNome.getScene().getWindow()).close();
        } else {
            new Alert(Alert.AlertType.WARNING,
                    "Você já possui uma reserva pendente no carrinho.")
                    .showAndWait();
        }
    }

    @FXML
    private void onVerCarrinho(ActionEvent event) throws IOException {
        SceneManager.abrirNovaJanela("/fxml/CarrinhoView.fxml", "Carrinho de Reserva");
    }
}
