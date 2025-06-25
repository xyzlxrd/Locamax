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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafxtest.IntegracaoBancoDados;
import javafxtest.Validacao;

public class FXMLJanelaRegistroController implements Initializable {
    @FXML
    private TextField textNome;
    @FXML
    private Label textErroNome;
    @FXML
    private TextField numCEP;
    @FXML
    private Label textErroCEP;
    @FXML
    private TextField textRua;
    @FXML
    private Label textErroRua;
    @FXML
    private TextField numCPF;
    @FXML
    private Label textErroCPF;
    @FXML
    private TextField textEstado;
    @FXML
    private Label textErroEstado;
    @FXML
    private TextField numNumeroResid;
    @FXML
    private Label textErroNumResid;
    @FXML
    private TextField selectPais;
    @FXML
    private Label textErroPais;
    @FXML
    private TextField textBairro;
    @FXML
    private Label textErroBairro;
    @FXML
    private TextField numTelefone;
    @FXML
    private Label textErroTelefone;
    @FXML
    private TextField numCNH;
    @FXML
    private Label textErroCNH;
    @FXML
    private TextField textCidade;
    @FXML
    private Label textErroCidade;
    @FXML
    private DatePicker numNascimento;
    @FXML
    private Label textErroNascimento;
    @FXML
    private Button botaoRegistro;
    @FXML
    private ToggleGroup genero;
    
    
    
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
        
        boolean nomefunc = Validacao.isNome(nome);
        boolean cepfunc = Validacao.isCEP(cep);
        boolean telecofunc = Validacao.isTelefone(telefone);
        boolean cnhfunc = Validacao.isCNH(cnh);
        boolean cpffunc = Validacao.isCPF(cpf);
        boolean paisfunc = Validacao.isEndereco1(pais);
        boolean estadofunc = Validacao.isEndereco1(estado);
        boolean cidadefunc = Validacao.isEndereco1(cidade);
        boolean ruafunc = Validacao.isEndereco2(rua);
        boolean bairrofunc = Validacao.isEndereco2(bairro);
        boolean numerofunc = Validacao.isEndereco2(numero);
        
        boolean erro = false;
        if(nomefunc == false){textErroNome.setText("Nome Inválido(a)."); erro = true;} else{textErroNome.setText("");}
        if(cepfunc == false){textErroCEP.setText("CEP Inválido."); erro = true;} else{textErroCEP.setText("");}
        if(cnhfunc == false){textErroCNH.setText("CNH Inválido."); erro = true;} else{textErroCNH.setText("");}
        if(telecofunc == false){textErroTelefone.setText("Telefone Inválido."); erro = true;} else{textErroTelefone.setText("");}
        if(cpffunc == false){textErroCPF.setText("CPF Inexistente/Apenas numeros."); erro = true;} else{textErroCPF.setText("");}
        if(paisfunc == false){textErroPais.setText("País Inválido."); erro = true;} else{textErroPais.setText("");}
        if(estadofunc == false){textErroEstado.setText("Estado Inválido."); erro = true;} else{textErroEstado.setText("");}
        if(cidadefunc == false){textErroCidade.setText("Cidade Inválida."); erro = true;} else{textErroCidade.setText("");}
        if(ruafunc == false){textErroRua.setText("Rua Inválida."); erro = true;} else{textErroRua.setText("");}
        if(bairrofunc == false){textErroBairro.setText("Bairro Inválido."); erro = true;} else{textErroBairro.setText("");}
        if(numerofunc == false){textErroNumResid.setText("Numero Inválido."); erro = true;} else{textErroNumResid.setText("");}
        
        boolean nascfunc = false;
        String nascimento = null;

        LocalDate nascimentoDate = numNascimento.getValue();
        if (nascimentoDate == null) {
            textErroNascimento.setText("Selecione a data de nascimento.");
        } else {
            nascimento = nascimentoDate.toString();
            nascfunc = Validacao.getAno(nascimento);
            if (!nascfunc) {
                textErroNascimento.setText("Data de nascimento inválida.");
            } else{textErroNascimento.setText("");}
        }
        if(erro){
            return;
        }
        
        
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