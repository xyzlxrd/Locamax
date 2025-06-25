package javafxtest.Controladores;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafxtest.Carrinho;
import javafxtest.CarroGetSet;
import javafxtest.Validacao;

public class CarrinhoController implements Initializable {

    @FXML
    private VBox vboxItens;
    @FXML
    private Label numeroDias;
    @FXML
    private Label totalPagar;
    double preco;
    String dataRetirada;
    String dataRetorno;
    int dias;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<CarroGetSet> carros = Carrinho.getInstance().getCarros();
        for (CarroGetSet carro : carros) {
            Label label = new Label(carro.getNomeCarro() + " - R$" + String.format("%.2f", carro.getValorDiaria()) + "/dia");
            vboxItens.getChildren().add(label);
            preco = carro.getValorDiaria();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dataRetirada = carro.getDataRet().format(formatter);
            dataRetorno = carro.getDataDev().format(formatter);
            dias = Validacao.getDia(dataRetirada, dataRetorno);
            
            //MOSTRAR NA TELA
            DecimalFormat df = new DecimalFormat("#.00");
            double precoTotal = (double)quantia * preco;
            String textoFormatado = df.format(precoTotal);
            totalPagar.setText("R$: " + textoFormatado);
            String qntdDias = String.valueOf(quantia);
            numeroDias.setText(qntdDias);
        }
    }    
    
    int quantia = 1;
    
    @FXML
    private void diminuir(ActionEvent event) {
        if(quantia > 1){
            DecimalFormat df = new DecimalFormat("#.00");
            --quantia;
            String qntdDias = String.valueOf(quantia);
            numeroDias.setText(qntdDias);
            double precoTotal = (double)quantia * preco;
            String textoFormatado = df.format(precoTotal);
            totalPagar.setText("R$: " + textoFormatado);
        }
    }
    
    @FXML
    private void aumentar(ActionEvent event) {
        if(quantia < dias){ 
            DecimalFormat df = new DecimalFormat("#.00");
            quantia++;
            String qntdDias = String.valueOf(quantia);
            numeroDias.setText(qntdDias);
            double precoTotal = (double)quantia * preco;
            String textoFormatado = df.format(precoTotal);
            totalPagar.setText("R$: " + textoFormatado);
        }
    }
    
    @FXML
    private void confirmarReserva(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reserva Confirmada");
        alert.setHeaderText(null);
        alert.setContentText("A reserva foi confirmada com sucesso! Para pagar venha ate loja!");
        alert.showAndWait();
        Carrinho.getInstance().limpar();
        ((Stage)vboxItens.getScene().getWindow()).close();
    }
}
