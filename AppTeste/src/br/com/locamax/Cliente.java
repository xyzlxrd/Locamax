package br.com.locamax;

import java.time.LocalDate;

import br.com.regras_validacao.ValidaEmail;

public class Cliente extends Pessoa {
	
	private int idCliente;
	private String email;
	private String usuario;
	private String senha;
	
	//Construtor Completo
	public Cliente (String email, String usuario, String senha, String cpf, String cnh, String nome, LocalDate dataNascimento, 
		   String telefone, String cep, String pais, String estado, String cidade, String bairro, String rua, String numero) {
		super (cpf, cnh, nome, dataNascimento, telefone, cep, pais, estado, cidade, bairro, rua, numero);
		this.email = email;
		this.usuario = usuario;
		this.senha = senha;
		}
	
	public int getIdCliente() {
		return idCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(ValidaEmail.isEmail(email)==true) {
			this.email = email;
		}else {
			this.email = email;
			String mensagem = "E-mail inválido!";
			System.out.println(mensagem);
		}
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
