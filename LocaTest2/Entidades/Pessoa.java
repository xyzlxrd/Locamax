package Entidades;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Pessoa {
    
    protected int idPessoa;
    protected String cpf;
    protected String cnh;
    protected String nome;
    protected LocalDate dataNascimento;
    protected String telefone;
    protected String email;
    protected Endereco endereco;
    protected String sexo;

    public Pessoa() {} //Construtor sem Parâmetros (Criar objeto vazio e implementar os dados depois com setters)

    public Pessoa(String cpf, String cnh, String nome, LocalDate dataNascimento, String telefone, String email, Endereco endereco,
    String sexo) {

        this.cpf = cpf;
        this.cnh = cnh;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.sexo = sexo;
    }

        public void getDados(){       
        System.out.println("CPF: "+cpf);
        System.out.println("NOME: "+nome);
        System.out.println("SEXO: "+sexo);
        System.out.println("DATA DE NASCIMENTO: "+dataNascimento);
        System.out.println("CNH: "+cnh);
        System.out.println("TELEFONE: "+telefone);
        System.out.println("E-MAIL: "+email);
    }

    public int getIdPessoa() {
        return idPessoa;
    }
    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String novoCpf) {
        this.cpf = novoCpf;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String novoNome) {
        this.nome = novoNome;
    }
    
    public String getSexo() {
        return sexo;
    }
    public void setSexo (String novoSexo) {
        this.sexo = novoSexo;
    }
    
    public LocalDate getNascimento() {

        return dataNascimento;
    }
    public void setNascimento(String novaDataNascimento) {

        try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataNascimento = LocalDate.parse(novaDataNascimento, formatter);
            } catch (DateTimeParseException exception) {
        System.out.println("Data de Nascimento Inválida. Siga a formatação (dd/MM/yyyy).");
        this.dataNascimento = null;
        }
    }
    
    public String getCNH() {
        return cnh;
    }
    public void setCNH(String novoCNH) {
        this.cnh = novoCNH;
    }
    
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String novoTelefone) {
        this.telefone = novoTelefone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String novoEmail) {
        this.email = novoEmail;  
    }
    
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco novoEndereco) {
        this.endereco = novoEndereco;
    }
}