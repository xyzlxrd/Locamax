package javafxtest.Controladores;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafxtest.IntegracaoBancoDados;
import javafxtest.Validacao;

public class FXMLJanelaRegistro2 {
    @FXML
    private TextField textUsername;
    @FXML
    private PasswordField textPassword;
    @FXML
    private TextField textEmail;
    private int idPessoa;
    
    public void setIdPessoa(int id) {
        this.idPessoa = id;
    }
    
    @FXML
    private void botaoRegistro(ActionEvent event) throws IOException {
        System.out.println("Botão Finalizar Cadastro clicado!");
    }

    public void botaoFinalizarRegistro(ActionEvent event) throws IOException {
        String username = textUsername.getText();
        String password = textPassword.getText();
        String email = textEmail.getText();
        
        Validacao.isEmail(email);
        
        boolean sucesso = IntegracaoBancoDados.registroDeLogin(
            idPessoa, username, password, email
        );
        
        if (sucesso) {
            System.out.println("Conta criada com sucesso!");
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close(); 
            Stage stage = (Stage) textUsername.getScene().getWindow();
            stage.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("/javafxtest/arquivosFxml/FXMLJanelaLogin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("LOCAMAX - Login");
            Image icone = new Image("/javafxtest/img/LOCAMAXLOGO.png");
            stage.getIcons().add(icone);
        } 
        else {
            System.out.println("Erro ao criar conta.");
        }
    }   
}