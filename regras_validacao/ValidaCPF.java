package br.com.regras_validacao;

import java.util.InputMismatchException;

public class ValidaCPF {
	
	// ||
	public static boolean isCPF(String cpf) {
		cpf = cpf.replaceAll("[()\\s-]","");
		// Verificando se os digitos não são iguais ou se a quantidade de digitos é diferente de 11.
		if(cpf.equals("00000000000")||cpf.equals("11111111111")||cpf.equals("22222222222")||
			cpf.equals("33333333333")||cpf.equals("44444444444")||cpf.equals("555555555555")||
			cpf.equals("66666666666")||cpf.equals("77777777777")||cpf.equals("88888888888")||
			cpf.equals("99999999999")||cpf.length()!=11) {
			return false;}
		
		int [] digitos;
		int soma, resto, verificador1, verificador2;
		
		try {
			// transformando string em inteiro.
			digitos = new int[11];
		      for (int i = 0; i < 11; i++) {
		          digitos[i] = cpf.charAt(i) - '0';
		      }
		    //Calculo do primeiro digito verificador
		      soma = 0;
		      for (int i = 0; i < 9; i++) {
		          soma += digitos[i] * (10 - i);
		      }
		      resto = soma % 11;
		      verificador1 = (resto < 2) ? 0 : (11 - resto);
			
			//Calculo do segundo digito verificador
		      soma = 0;
		      for (int i = 0; i < 10; i++) {
		          soma += digitos[i] * (11 - i);
		      }
		      resto = soma % 11;
		      verificador2 = (resto < 2) ? 0 : (11 - resto);
		      
			// Verificando se os digitos verificadores calculados é igual ao número armazenado.
			
			if (verificador1 == digitos[9] && verificador2 == digitos[10]) {
				return true;
				}
			else {
				return false;
				}
			 
		} catch (InputMismatchException e) {
			
			return false;
		
		}	
	}
	
	public void printCPF (String cpf) {
		// criando a mascara para impressão do cpf
		
	}
	
}
