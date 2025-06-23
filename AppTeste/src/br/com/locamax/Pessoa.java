package br.com.locamax;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import br.com.regras_validacao.*;

public class Pessoa {
	private int idPessoa;
	private String cpf;
	private String cnh;
    private String nome;
    private LocalDate dataNascimento;
    private String telefone;

	public int getIdPessoa() {
		return idPessoa;
	}
	
	public String getCpf() {
		return (cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11));
	}
	public void setCpf(String cpf) {
		if(ValidaCPF.isCPF(cpf) == true) {
			this.cpf = cpf;
		}else {
			String mensagem = "CPF inválido!";
			System.out.println(mensagem);
		}
	}
	
	public String getCnh() {
		return cnh ;
	}
	public void setCnh(String cnh) {
		if(ValidaCNH.isCNH(cnh) == true) {
			this.cnh = cnh;
		}else {
			String mensagem = "CNH inválida!";
			System.out.println(mensagem);
		}
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(ValidaNome.isNome(nome) == true) {
			this.nome = nome;
		}else {
			String mensagem = "Nome inválido!";
			System.out.println(mensagem);
		}
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String nascimento) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate data = LocalDate.parse(nascimento, formatter);
			LocalDate hoje = LocalDate.now();
			Period idade = Period.between(data, hoje);
			if(idade.getYears() > 16 && idade.getYears() <150) {
				dataNascimento = data;}
			else { 
				System.out.println("Usuário não pode ter mais que 150 anos");
				dataNascimento = null;}
		} catch (DateTimeParseException e) {
	        System.out.println("Data de Nascimento Inválida. Siga a formatação (dd/MM/yyyy).");
			dataNascimento = null;}
	}
	
	public String getTelefone() {
		//Verifica o comprimento da string e imprime com a formatação adequada ao tamanho
		if(telefone.length() == 11) {
			String mensage = "("+telefone.substring(0, 2)+")"+telefone.substring(2, 7)+"-"+telefone.substring(7, 11);
			return mensage;
		}else {
			String mensage = "("+telefone.substring(0, 2)+")"+telefone.substring(2, 6)+"-"+telefone.substring(6, 10);
			return mensage;
		}
	}
	public void setTelefone(String telefone) {
		if(ValidaTelefone.isTelefone(telefone)==true) {
			this.telefone = telefone;
		}else {
			String mensagem = "Telefone invalido!";
			System.out.println(mensagem);
		}
	}
}
