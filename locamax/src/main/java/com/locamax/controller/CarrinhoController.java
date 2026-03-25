package com.locamax.controller;

import com.locamax.model.Carro;
import com.locamax.model.Carrinho;
import com.locamax.util.Validacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class CarrinhoController implements Initializable {

    @FXML private VBox  vboxItens;
    @FXML private Label lblNumeroDias;
    @FXML private Label lblTotalPagar;

    private static final DecimalFormat DF = new DecimalFormat("#.00");
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private double precoDiaria;
    private int    diasMaximos;
    private int    diasSelecionados = 1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Carro> itens = Carrinho.getInstance().getItens();

        for (Carro carro : itens) {
            Label label = new Label(carro.getNome() + " — R$ " +
                    String.format("%.2f", carro.getValorDiaria()) + "/dia");
            vboxItens.getChildren().add(label);

            precoDiaria  = carro.getValorDiaria();
            diasMaximos  = Validacao.calcularDias(
                    carro.getDataRetirada().format(FMT),
                    carro.getDataDevolucao().format(FMT));
        }

        atualizarTotais();
    }

    @FXML
    private void onDiminuir(ActionEvent event) {
        if (diasSelecionados > 1) {
            diasSelecionados--;
            atualizarTotais();
        }
    }

    @FXML
    private void onAumentar(ActionEvent event) {
        if (diasSelecionados < diasMaximos) {
            diasSelecionados++;
            atualizarTotais();
        }
    }

    @FXML
    private void onConfirmarReserva(ActionEvent event) {
        Carrinho.getInstance().limpar();

        new Alert(Alert.AlertType.INFORMATION,
                "Reserva confirmada! Compareça à loja para finalizar o pagamento.")
                .showAndWait();

        ((Stage) vboxItens.getScene().getWindow()).close();
    }

    private void atualizarTotais() {
        lblNumeroDias.setText(String.valueOf(diasSelecionados));
        lblTotalPagar.setText("R$ " + DF.format(diasSelecionados * precoDiaria));
    }
}
