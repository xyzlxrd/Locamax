package com.locamax.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("LOCAMAX - Login");
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/LOCAMAXLOGO.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
