package br.com.regras_validacao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaEndereco2 {
	public static boolean isEndereco(String endereco) {
		if(endereco.length() >1 && endereco.length()<50) {
			String rgxEndereco = "^[A-Za-z0-9]$";
			Pattern padrao = Pattern.compile(rgxEndereco);
			Matcher igual = padrao.matcher(endereco);
			return igual.matches();
		}else {
			return false;
		}
	}
}
