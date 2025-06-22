package RegrasDeValidaçoes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacaoEmail {
	
	public static boolean isEmail(String email) {
		String rgxEmail = "^[A-Za-z0-9-+_.-]+@+[A-Za-z0-9.-]+$";
		Pattern padrao = Pattern.compile(rgxEmail);
		Matcher igual = padrao.matcher(email);
		return igual.matches();
	}
	;
}
