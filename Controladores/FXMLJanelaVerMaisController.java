package javafxtest.Controladores;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafxtest.Carrinho;
import javafxtest.CarroGetSet;

public class FXMLJanelaVerMaisController{
    
    private CarroGetSet carro;
    @FXML
    private ImageView imgCarro;
    @FXML
    private Text numPortas;
    @FXML
    private Text boolArCond;
    @FXML
    private Text numAssentos;
    @FXML
    private Text textCambio;
    @FXML
    private Text boolAirbag;
    @FXML
    private Text numCapTanque;
    @FXML
    private Text numTipoMotor;
    @FXML
    private Text numPreco;
    @FXML
    private Text txtNome;
    @FXML
    private Text txtTipo;
    @FXML
    private Text txtDesc;

    public void setCarro(CarroGetSet carro) {
        txtDesc.setWrappingWidth(375);
        this.carro = carro;
        txtNome.setText(carro.getNomeCarro());
        txtTipo.setText(carro.getNomeCategoria());
        txtDesc.setText(carro.getDescricao());
        numPreco.setText("R$ " + String.format("%.2f", carro.getValorDiaria()));
        numPortas.setText(String.valueOf(carro.getQntPortas()));
        boolArCond.setText(carro.isArCondicionado() ? "Sim" : "Não");
        numAssentos.setText(String.valueOf(carro.getQntAssentos()));
        textCambio.setText(carro.getCambio());
        boolAirbag.setText(carro.isAirbag() ? "Sim" : "Não");
        numCapTanque.setText(String.valueOf(carro.getCapacidadeTanque()) + "L");
        numTipoMotor.setText(carro.getPotenciaMotor());
        imgCarro.setImage(new Image(getClass().getResourceAsStream("/javafxtest/img/modelo_" + carro.getIdModelo() + ".png")));
    }

    @FXML
    private void btnCompra(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafxtest/arquivosFxml/FXMLJanelaCarrinho.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Carrinho de Compras");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void btnAlugar(ActionEvent event) {
        Carrinho.getInstance().adicionar(carro);
        ((Stage) txtNome.getScene().getWindow()).close();
    }
}