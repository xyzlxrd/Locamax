package br.com.locamax;

import br.com.regras_validacao.ValidaCEP;
import br.com.regras_validacao.ValidaEndereco;
import br.com.regras_validacao.ValidaEndereco2;

public class Endereco {
	private int idPessoa;
    private String cep;
    private String pais;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    
	public int getIdPessoa() {
		return idPessoa;
	}

	public String getCep() {
		String mensagem = cep.substring(0, 5)+"-"+cep.substring(5, 9);
		return mensagem;
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
