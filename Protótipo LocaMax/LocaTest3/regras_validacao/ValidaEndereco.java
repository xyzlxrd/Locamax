package regras_validacao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaEndereco {
	public static boolean isEndereco(String pais) {
		if(pais.length() >1 && pais.length()<50) {
			String rgxPais = "^[A-Za-zÀ-ÿ\\s]+$"; //REGEX ALTERADO: "^[A-Za-z]$" > "^[A-Za-zÀ-ÿ\\s]+$"
			Pattern padrao = Pattern.compile(rgxPais);
			Matcher igual = padrao.matcher(pais);
			return igual.matches();
		}else {
			return false;
		}
	}
}
