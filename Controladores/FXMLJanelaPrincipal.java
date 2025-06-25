package javafxtest.Controladores;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafxtest.CarroGetSet;
import javafxtest.IntegracaoParaCarros;

public class FXMLJanelaPrincipal implements Initializable {

    @FXML
    private TilePane tilePaneCarros;
    @FXML
    private ToggleButton btnPickup;
    @FXML
    private ToggleButton btnHatch;
    @FXML
    private ToggleButton btnSUV;
    @FXML
    private ToggleButton btnSedan;
    @FXML
    private ComboBox<String> qntdAssentos;
    @FXML
    private ComboBox<String> tipoComb;
    @FXML
    private ComboBox<String> capMotor;
    @FXML
    private DatePicker dataRet;
    @FXML
    private DatePicker dataDev;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tilePaneCarros.setHgap(20);
        tilePaneCarros.setVgap(15);
        tilePaneCarros.setPrefWidth(1100);
        carregarCarros();
        
        qntdAssentos.setItems(FXCollections.observableArrayList("2", "3", "4", "5"));
        tipoComb.setItems(FXCollections.observableArrayList("", "Gasolina", "Etanol", "Diesel", "Flex"));
        capMotor.setItems(FXCollections.observableArrayList("1.0", "1.3", "1.4", "1.6", "1.8", "2.0", "2.4", "3.0"));
    }
    
    public void btnFiltrar(ActionEvent event) {
        String categoriaSelecionada = null;
        if (btnHatch.isSelected()) {
            categoriaSelecionada = "Hatch";
        } 
        else if (btnSUV.isSelected()) {
            categoriaSelecionada = "SUV";
        } 
        else if (btnSedan.isSelected()) {
            categoriaSelecionada = "Sedan";
        } 
        else if (btnPickup.isSelected()) {
            categoriaSelecionada = "Pickup";
        }

        String assentosStr = qntdAssentos.getValue() != null ? qntdAssentos.getValue().trim() : "";
        String combustivel = tipoComb.getValue() != null ? tipoComb.getValue().trim() : "";
        String motor = capMotor.getValue() != null ? capMotor.getValue().trim() : "";

        Integer qntAssentos = null;
        if (!assentosStr.isEmpty()) {
            try {
                qntAssentos = Integer.parseInt(assentosStr);
            } 
            catch (NumberFormatException e) {
                qntAssentos = null;
            }
        }

        List<CarroGetSet> carros = IntegracaoParaCarros.listarTodos();
        tilePaneCarros.getChildren().clear();
        for (CarroGetSet carro : carros) {
            boolean condicao = true;
            if (categoriaSelecionada != null && !categoriaSelecionada.equalsIgnoreCase(carro.getNomeCategoria())) {
                condicao = false;
            }
            if (qntAssentos != null && !qntAssentos.equals(carro.getQntAssentos())) {
                condicao = false;
            }
            if (!combustivel.isEmpty() && !carro.getTipoCombustivel().toLowerCase().contains(combustivel.toLowerCase())) {
                condicao = false;
            }
            if (!motor.isEmpty() && !carro.getPotenciaMotor().toLowerCase().contains(motor.toLowerCase())) {
                condicao = false;
            }
            if (condicao) {
                VBox card = criarCardCarro(carro);
                tilePaneCarros.getChildren().add(card);
            }
        }
    }
    
    @FXML
    public void btnCompra(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafxtest/arquivosFxml/FXMLJanelaCarrinho.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Carrinho de Compras");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void carregarCarros() {
        List<Integer> categoriasSelecionadas = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        List<CarroGetSet> carros = IntegracaoParaCarros.listarPorCategorias(categoriasSelecionadas);

        for (CarroGetSet carro : carros) {
            VBox card = criarCardCarro(carro);
            tilePaneCarros.getChildren().add(card);
        }
    }
    
    private void abrirDetalhesCarro(CarroGetSet carro) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafxtest/arquivosFxml/FXMLJanelaVerMais.fxml"));
            Parent root = loader.load();
            FXMLJanelaVerMaisController controller = loader.getController();
            controller.setCarro(carro);
            Stage stage = new Stage();
            stage.setTitle("Detalhes do Carro");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private VBox criarCardCarro(CarroGetSet carro) {
        ImageView imagem = new ImageView();
        String imgPath = "/javafxtest/img/modelo_" + carro.getIdModelo() + ".png";
        imagem.setImage(new Image(getClass().getResourceAsStream(imgPath)));
        imagem.setFitWidth(150);
        imagem.setFitHeight(100);
        imagem.setPreserveRatio(true);
        
        Label titulo = new Label(carro.getNomeCarro());
        titulo.getStyleClass().add("titulo");
        titulo.setWrapText(true);
        titulo.setTextAlignment(TextAlignment.CENTER);

        String subtituloStr = "Cor: " + carro.getCor() + " | Combustível: " + carro.getTipoCombustivel();
        Label subtitulo = new Label(subtituloStr);
        subtitulo.getStyleClass().add("subtitulo");

        Label preco1 = new Label("A partir de");
        preco1.getStyleClass().add("subtitulo");
        Label preco2 = new Label(String.format("R$ %.2f / Dia", carro.getValorDiaria()));
        preco2.getStyleClass().add("preco");

        VBox precoBox = new VBox(5, preco1, preco2);
        precoBox.getStyleClass().add("preco-box");

        Button verMais = new Button("Ver mais");
        verMais.getStyleClass().add("button-vermais");
        verMais.setOnAction(e -> abrirDetalhesCarro(carro));

        VBox vbox = new VBox(15, titulo, subtitulo, imagem, precoBox, verMais);
        vbox.getStyleClass().add("cardCarro");
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefWidth(275);
        vbox.setPadding(new Insets(15));

        if (!tilePaneCarros.getStylesheets().contains(getClass().getResource("/javafxtest/arquivosFxml/fxmljanelalogin.css").toExternalForm())) {
            tilePaneCarros.getStylesheets().add(getClass().getResource("/javafxtest/arquivosFxml/fxmljanelalogin.css").toExternalForm());
        }

        return vbox;
    }
}