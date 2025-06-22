package Entidades;
import java.time.LocalDate;

public class Cliente extends Pessoa{
    
    private int idCliente;
    private int idPessoa;
    private String usuario;
    private String senha;

    public Cliente() {} //Construtor sem Parâmetros (Criar objeto vazio e implementar os dados depois com setters)

    public Cliente(String cpf, String cnh, String nome,LocalDate dataNascimento, String telefone, String email, 
    String sexo, Endereco endereco, String usuario, String senha) {

        super(cpf, cnh, nome, dataNascimento, telefone, email, endereco, sexo);
        this.usuario = usuario;
        this.senha = senha;

    }
    
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String novoUsuario) {
        this.usuario = novoUsuario;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String novaSenha) {
        this.senha = novaSenha;
    }

}
