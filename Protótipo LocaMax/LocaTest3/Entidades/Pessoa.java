package Entidades;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import regras_validacao.ValidaCNH;
import regras_validacao.ValidaCPF;
import regras_validacao.ValidaNome;
import regras_validacao.ValidaTelefone;

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

    public int getIdPessoa() {
        return idPessoa;
    }
    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

	public String getCpf() {

        if (cpf != null && cpf.length() == 11) {
        return (cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11));
        } else {
            return "CPF definido incorretamente.";
        }
	}
	public void setCpf(String cpf) {
		if(ValidaCPF.isCPF(cpf) == true) {
			this.cpf = cpf;
		} else {
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

    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
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