package RegrasDeValidaçoes;
import java.util.Scanner;

public class TesteMetodos {

	public static void main(String[] args) {
		ValidacaoTelefone validacaoTel = new ValidacaoTelefone();
		ValidacaoCPF validacaoCpf = new ValidacaoCPF();
		ValidacaoCNH validacaoCnh = new ValidacaoCNH();
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("CPF:");
		String cpf = "14413227603";
		if(ValidacaoCPF.isCPF(cpf) == true) {
			validacaoCpf.printCPF(cpf);
		}else {
			System.out.println("CPF inválido!");
		}
		
		System.out.println("CNH:");
		String chn = "12438798740";
		if(ValidacaoCNH.isCNH(chn) == true) {
			validacaoCnh.printCNH(chn);
		}else {
			System.out.println("CNH inválida!");
		}
		
		System.out.println("Telefone:");
		String telefone = "44897876231";
		if(ValidacaoTelefone.isTelefone(telefone) == true) {
			validacaoTel.printTelefone(telefone);
		}else {
			System.out.println("Telefone inválido!");
		}
		
		System.out.println("Email:");
		String email = scnr.nextLine();
		if(ValidacaoEmail.isEmail(email) == true) {
			System.out.println(email);
		}else {
			System.out.println("Email inválido!");
		}
		
		System.out.println("Finalizado");
		scnr.close();
	}

}
