package com.locamax.controller;

import com.locamax.model.Carro;
import com.locamax.repository.CarroRepository;
import com.locamax.util.SceneManager;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML private TilePane          tilePaneCarros;
    @FXML private ToggleButton      btnPickup;
    @FXML private ToggleButton      btnHatch;
    @FXML private ToggleButton      btnSUV;
    @FXML private ToggleButton      btnSedan;
    @FXML private ComboBox<String>  cmbAssentos;
    @FXML private ComboBox<String>  cmbCombustivel;
    @FXML private ComboBox<String>  cmbMotor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tilePaneCarros.setHgap(20);
        tilePaneCarros.setVgap(15);
        tilePaneCarros.setPrefWidth(1100);

        cmbAssentos.setItems(FXCollections.observableArrayList("", "2", "3", "4", "5"));
        cmbCombustivel.setItems(FXCollections.observableArrayList("", "Gasolina", "Etanol", "Diesel", "Flex"));
        cmbMotor.setItems(FXCollections.observableArrayList("", "1.0", "1.3", "1.4", "1.6", "1.8", "2.0", "2.4", "3.0"));

        carregarCarros(CarroRepository.listarTodos());
    }

    @FXML
    public void onFiltrar(ActionEvent event) {
        String categoriaStr = obterCategoriaSelecionada();
        String assentosStr  = cmbAssentos.getValue();
        String combustivel  = cmbCombustivel.getValue();
        String motor        = cmbMotor.getValue();

        Integer idCategoria = null; // filtro por nome de categoria aplicado no lado Java
        Integer assentos    = (assentosStr != null && !assentosStr.isBlank())
                ? Integer.parseInt(assentosStr) : null;

        List<Carro> todos = CarroRepository.filtrar(idCategoria, assentos, combustivel, motor);

        if (categoriaStr != null) {
            final String cat = categoriaStr;
            todos = todos.stream()
                    .filter(c -> cat.equalsIgnoreCase(c.getNomeCategoria()))
                    .toList();
        }

        carregarCarros(todos);
    }

    @FXML
    public void onAbrirCarrinho(ActionEvent event) throws IOException {
        SceneManager.abrirNovaJanela("/fxml/CarrinhoView.fxml", "Carrinho de Reserva");
    }

    private void carregarCarros(List<Carro> carros) {
        tilePaneCarros.getChildren().clear();
        for (Carro carro : carros) {
            tilePaneCarros.getChildren().add(criarCard(carro));
        }
    }

    private String obterCategoriaSelecionada() {
        if (btnHatch.isSelected())  return "Hatch";
        if (btnSUV.isSelected())    return "SUV";
        if (btnSedan.isSelected())  return "Sedan";
        if (btnPickup.isSelected()) return "Pickup";
        return null;
    }

    private VBox criarCard(Carro carro) {
        ImageView imagem = new ImageView();
        imagem.setImage(new Image(getClass().getResourceAsStream("/img/modelo_" + carro.getIdModelo() + ".png")));
        imagem.setFitWidth(150);
        imagem.setFitHeight(100);
        imagem.setPreserveRatio(true);

        Label titulo = new Label(carro.getNome());
        titulo.getStyleClass().add("titulo");
        titulo.setWrapText(true);
        titulo.setTextAlignment(TextAlignment.CENTER);

        Label subtitulo = new Label("Cor: " + carro.getCor() + " | Combustível: " + carro.getTipoCombustivel());
        subtitulo.getStyleClass().add("subtitulo");

        Label precoLabel = new Label("A partir de");
        precoLabel.getStyleClass().add("subtitulo");
        Label preco = new Label(String.format("R$ %.2f / Dia", carro.getValorDiaria()));
        preco.getStyleClass().add("preco");

        VBox precoBox = new VBox(5, precoLabel, preco);
        precoBox.getStyleClass().add("preco-box");

        Button btnVerMais = new Button("Ver mais");
        btnVerMais.getStyleClass().add("button-vermais");
        btnVerMais.setOnAction(e -> abrirDetalhesCarro(carro));

        VBox card = new VBox(15, titulo, subtitulo, imagem, precoBox, btnVerMais);
        card.getStyleClass().add("cardCarro");
        card.setAlignment(Pos.CENTER);
        card.setPrefWidth(275);
        card.setPadding(new Insets(15));

        String cssPath = getClass().getResource("/css/main.css").toExternalForm();
        if (!tilePaneCarros.getStylesheets().contains(cssPath)) {
            tilePaneCarros.getStylesheets().add(cssPath);
        }

        return card;
    }

    private void abrirDetalhesCarro(Carro carro) {
        try {
            FXMLLoader loader = SceneManager.abrirNovaJanelaComLoader("/fxml/DetalhesCarroView.fxml", "Detalhes do Veículo");
            DetalhesCarroController controller = loader.getController();
            controller.setCarro(carro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
