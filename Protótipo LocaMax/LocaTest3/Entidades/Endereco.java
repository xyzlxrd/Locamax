package Entidades;

import regras_validacao.ValidaCEP;
import regras_validacao.ValidaEndereco;
import regras_validacao.ValidaEndereco2;

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

        public Endereco() {} //Construtor sem Parâmetros (Criar objeto vazio e implementar os dados depois com setters)
    
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

	public String getCep() {

        if (cep != null && cep.length() == 8) { //If adicionado
		String mensagem = cep.substring(0, 5)+"-"+cep.substring(5, 8); //5,9 > 5,8
		return mensagem;
        } else {
            return "CEP Inválido.";
        }
	}
	public void setCep(String cep) {
		if(ValidaCEP.isCEP(cep)==true) {
			this.cep = cep;
		}
	}
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		if(ValidaEndereco.isEndereco(pais) == true){
			this.pais = pais;
		}else{
			String mensagem = "País inválido!";
			System.out.println(mensagem);
		}
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		if(ValidaEndereco.isEndereco(estado) == true){
			this.estado = estado;
		}else{
			String mensagem = "Estado inválido!";
			System.out.println(mensagem);
		}
		}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		if(ValidaEndereco.isEndereco(cidade) == true){
			this.cidade = cidade;
		}else{
			String mensagem = "Cidade inválida!";
			System.out.println(mensagem);
		}
	}
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		if(ValidaEndereco2.isEndereco(bairro) == true){
			this.bairro = bairro;
		}else{
			String mensagem = "Bairro inválido!";
			System.out.println(mensagem);
		}
	}
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		if(ValidaEndereco2.isEndereco(rua) == true){
			this.rua = rua;
		}else{
			String mensagem = "Rua inválida!";
			System.out.println(mensagem);
		}
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		if(ValidaEndereco2.isEndereco(numero) == true){
			this.numero = numero;
		}else{
			String mensagem = "Número inválido!";
			System.out.println(mensagem);
		}
	}
}

