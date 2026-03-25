package com.locamax.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public final class SceneManager {

    private static final String ICON_PATH = "/img/LOCAMAXLOGO.png";

    private SceneManager() {}

    public static void trocarCena(Stage stage, String fxmlPath, String titulo) throws IOException {
        Parent root = FXMLLoader.load(SceneManager.class.getResource(fxmlPath));
        stage.setScene(new Scene(root));
        stage.setTitle(titulo);
        aplicarIcone(stage);
        stage.show();
    }

    public static Stage abrirNovaJanela(String fxmlPath, String titulo) throws IOException {
        Parent root = FXMLLoader.load(SceneManager.class.getResource(fxmlPath));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(titulo);
        aplicarIcone(stage);
        stage.show();
        return stage;
    }

    public static FXMLLoader abrirNovaJanelaComLoader(String fxmlPath, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(titulo);
        aplicarIcone(stage);
        stage.show();
        return loader;
    }

    private static void aplicarIcone(Stage stage) {
        Image icone = new Image(SceneManager.class.getResourceAsStream(ICON_PATH));
        stage.getIcons().add(icone);
    }
}
