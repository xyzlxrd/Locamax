package regras_validacao;

public class ValidaTelefone {
	
	public static boolean isTelefone(String telefone) {
		//Retira todos os caracteres especiais
		telefone = telefone.replaceAll("[()\\s-]","");
		
		//Verifica se o comprimento da string é 10 ou 11
		return telefone.matches("\\d{10,11}");
	}
	
	public void printTelefone(String telefone) {
		telefone = telefone.replaceAll("[()\\s-]","");
		//Verifica o comprimento da string e imprime com a formatação adequada ao tamanho
		if(telefone.length() == 11) {
			System.out.println("("+telefone.substring(0, 2)+")"+telefone.substring(2, 7)+"-"+telefone.substring(7, 11));
		}else {
			System.out.println("("+telefone.substring(0, 2)+")"+telefone.substring(2, 6)+"-"+telefone.substring(6, 10));
		}
	}

}
