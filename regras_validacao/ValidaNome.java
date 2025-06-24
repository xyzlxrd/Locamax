package br.com.regras_validacao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaNome {
	public static boolean isNome(String nome) {
		if(nome.length() >1 && nome.length()<100) {
			String rgxNome = "^[A-Za-z]$";
			Pattern padrao = Pattern.compile(rgxNome);
			Matcher igual = padrao.matcher(nome);
			return igual.matches();
		}else {
			return false;
		}
	}
}
