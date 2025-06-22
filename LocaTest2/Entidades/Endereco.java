package Entidades;

public class Endereco {

    private Pessoa pessoa;

    private int idEndereco;
    private int idPessoa;
    private String cep;
    private String pais;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;

        //Construtor com PK
        public Endereco(int idEndereco, int idPessoa, String cep, String pais, String estado, String cidade,
    String bairro, String rua, String numero) {

        this.idEndereco = idEndereco;
        this.idPessoa = idPessoa;
        this.cep = cep;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

        //Construtor para ENDEREÇO TEMPORARIO
        public Endereco(String cep, String pais, String estado, String cidade, String bairro, String rua, String numero) {

        this.cep = cep;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

        //Construtor Completo
        public Endereco(int idPessoa, String cep, String pais, String estado, String cidade, String bairro, String rua, String numero) {

        this.idPessoa = idPessoa;
        this.cep = cep;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;  
        }
    
    public void getEnderecoCompleto(){
        System.out.println("CEP: "+cep);
        System.out.println("PAIS: "+pais);
        System.out.println("ESTADO: "+estado);
        System.out.println("CIDADE: "+cidade);
        System.out.println("BAIRRO: "+bairro);
        System.out.println("RUA: "+rua);
        System.out.println("NUMERO: "+numero);
    }

    public int getIdEndereco() {
        return idEndereco;
    }
    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public int getIdPessoa() {
        return idPessoa;
    }
    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getCep(){
        return cep;
    }
    public void setCep(String novoCep) {
        this.cep = novoCep;
    }
    
    public String getPais() {
        return pais;
    }
    public void setPais(String novoPais) {
        this.pais = novoPais;
    }
    
    public String getEstado() {
        return estado;
    }
    public void setEstado(String novoEstado) {
        this.estado = novoEstado;
    }
    
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String novaCidade) {
        this.cidade = novaCidade;
    }
    
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String novoBairro) {
        this.bairro = novoBairro;
    }
    
    public String getRua() {
        return rua;
    }
    public void setRua(String novaRua) {
        this.rua = novaRua;
    }
    
    public String getNumero() {
        return numero;
    }
    public void setNumero(String novoNumero) {
        this.numero = novoNumero;
    }
}

