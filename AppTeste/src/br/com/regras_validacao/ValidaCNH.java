package br.com.regras_validacao;

public class ValidaCNH { 
	
	// |	
	public static boolean isCNH(String cnh) {
		cnh = cnh.replaceAll("[()\\s-]","");
		return cnh.matches("\\d{11}");
	}
	
	public void printCNH (String cnh) {
		// impressão da cnh
		cnh = cnh.replaceAll("[()\\s-]","");
		System.out.println(cnh);
	}
	
}
