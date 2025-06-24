package javafxtest.Controladores;

import java.net.URL;
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
import javafx.scene.text.Text;

public class CarrinhoController implements Initializable {

    @FXML
    private VBox vboxItens;
    @FXML
    private Label numeroDias;
    @FXML
    private Text totalPagar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<CarroGetSet> carros = Carrinho.getInstance().getCarros();
        for (CarroGetSet carro : carros) {
            Label label = new Label(carro.getNomeCarro() + " - R$" + String.format("%.2f", carro.getValorDiaria()) + "/dia");
            vboxItens.getChildren().add(label);
        }
    }    

    int quantia = 1;
    int dias = 5;
    
    @FXML
    private void diminuir(ActionEvent event) {
        if(quantia > 0){
            quantia--;
            String qntdDias = String.valueOf(quantia);
            numeroDias.setText(qntdDias);
        }
    }
    @FXML
    private void aumentar(ActionEvent event) {
        if(quantia < dias){ 
            quantia++;
            String qntdDias = String.valueOf(quantia);
            numeroDias.setText(qntdDias);
        }
    }
    
    @FXML
    private void confirmarReserva(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reserva Confirmada");
        alert.setHeaderText(null);
        alert.setContentText("A reserva foi confirmada com sucesso!");
        alert.showAndWait();
        Carrinho.getInstance().limpar();
        ((Stage) vboxItens.getScene().getWindow()).close();
        }
    
}
