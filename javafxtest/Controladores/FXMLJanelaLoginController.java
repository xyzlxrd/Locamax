package javafxtest.Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafxtest.IntegracaoBancoDados;

public class FXMLJanelaLoginController implements Initializable {
    @FXML
    private TextField textUsername;
    @FXML
    private PasswordField textPassword;
    @FXML
    private Text textId;

    @FXML
    public void botaoLogin(ActionEvent event) throws IOException {
        String user = textUsername.getText();
        String pass = textPassword.getText();

        boolean logado = IntegracaoBancoDados.verificarLogin(user, pass);

        if (logado) {
            Stage stage = (Stage) textUsername.getScene().getWindow();
            stage.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("/javafxtest/arquivosFxml/FXMLJanelaPrincipal.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("LOCAMAX");
        
            Image icone = new Image("/javafxtest/img/LOCAMAXLOGO.png");
            stage.getIcons().add(icone);

        } 
        else {
            textId.setText("Credenciais erradas. Tente novamente!");
        }
    }
    
    @FXML
    public void botaoCadastro(ActionEvent event) throws IOException{
        Stage stage = (Stage) textUsername.getScene().getWindow();
        stage.close();
            
        Parent root = FXMLLoader.load(getClass().getResource("/javafxtest/arquivosFxml/FXMLJanelaRegistro.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("LOCAMAX");
        
        Image icone = new Image("/javafxtest/img/LOCAMAXLOGO.png");
        stage.getIcons().add(icone);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}