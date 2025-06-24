package javafxtest.Controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxtest.IntegracaoBancoDados;
import javafxtest.Validacao;

public class FXMLJanelaRegistroController implements Initializable {
    @FXML
    private TextField textNome;
    @FXML
    private TextField numCEP;
    @FXML
    private TextField textRua;
    @FXML
    private TextField numCPF;
    @FXML
    private TextField textEstado;
    @FXML
    private TextField numNumeroResid;
    @FXML
    private TextField selectPais;
    @FXML
    private TextField textBairro;
    @FXML
    private TextField numTelefone;
    @FXML
    private TextField numCNH;
    @FXML
    private TextField textCidade;
    @FXML
    private DatePicker numNascimento;
    
    
    
    @FXML
    public void botaoRegistro(ActionEvent event) throws IOException {
        String nome = textNome.getText();
        String cpf = numCPF.getText();
        String telefone = numTelefone.getText();
        String cidade = textCidade.getText();
        String cep = numCEP.getText();
        String rua = textRua.getText();
        String numero = numNumeroResid.getText();
        String bairro = textBairro.getText();
        String estado = textEstado.getText();
        String pais = selectPais.getText();
        String cnh = numCNH.getText();
        String nascimento = numNascimento.getValue().toString();
        
        boolean nomefunc = Validacao.isNome(nome);
        Validacao.isNome(nome);
        boolean cepfunc = Validacao.isCEP(cep);
        Validacao.isCEP(cep);
        boolean telecofunc = Validacao.isTelefone(telefone);
        Validacao.isTelefone(telefone);
        boolean cnhfunc = Validacao.isCNH(cnh);
        Validacao.isCNH(cnh);
        boolean cpffunc = Validacao.isCPF(cpf);
        Validacao.isCPF(cpf);
        boolean paisfunc = Validacao.isEndereco1(pais);
        Validacao.isEndereco1(pais);
        boolean estadofunc = Validacao.isEndereco1(estado);
        Validacao.isEndereco1(estado);
        boolean cidadefunc = Validacao.isEndereco1(cidade);
        Validacao.isEndereco1(cidade);
        boolean ruafunc = Validacao.isEndereco2(rua);
        Validacao.isEndereco2(rua);
        boolean bairrofunc = Validacao.isEndereco2(bairro);
        Validacao.isEndereco2(bairro);
        boolean numerofunc = Validacao.isEndereco2(numero);
        Validacao.isEndereco2(numero);
        boolean nascfunc = Validacao.getAno(nascimento);
        Validacao.getAno(nascimento);
        
        
        if(nomefunc == true && cepfunc == true && telecofunc == true && cnhfunc == true &&
        cpffunc == true && paisfunc == true && estadofunc == true && cidadefunc == true && 
        ruafunc == true && bairrofunc == true && numerofunc == true && nascfunc == true ){
            int idPessoa = IntegracaoBancoDados.registroUsuario(
            nome, cpf, telefone, cep, cidade, rua, numero, bairro, nascimento, estado, pais, cnh
            );
            if (idPessoa != 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafxtest/arquivosFxml/FXMLJanelaRegistro2.fxml"));
                Parent root = loader.load();
                FXMLJanelaRegistro2 controller2 = loader.getController();
                controller2.setIdPessoa(idPessoa);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } else {
                System.out.println("Erro ao registrar etapa 1.");
            }
        }
    }

        

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}