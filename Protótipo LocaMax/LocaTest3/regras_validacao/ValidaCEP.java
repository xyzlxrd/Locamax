package regras_validacao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaCEP {
	public static boolean isCEP(String cep) {
		cep = cep.replaceAll("[()\\s-]","");
		String rgxCEP = "^[0-9]{8}$";
		Pattern padrao = Pattern.compile(rgxCEP);
		Matcher igual = padrao.matcher(cep);
		return igual.matches();
	}
	  
}
