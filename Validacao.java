package javafxtest;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacao {
	static int quantDias;
	
	public static int getDia(String dataRetirada, String dataRetorno){
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate retirada = LocalDate.parse(dataRetirada, formatter);
			LocalDate retorno = LocalDate.parse(dataRetorno, formatter);
			Period periodo = Period.between(retirada, retorno);
			if(periodo.getDays() >= 1) {
				return periodo.getDays();
				
			}
                        else {
				String msm1 = "A data de retirada deve ser maior que a data retorno";}
				
		} 
                catch (DateTimeParseException e) {
                    String msm2 ="Data inválida. Siga a formatação (dd/MM/yyyy).";
		}
                return 0;
	}
        
        public static boolean getAno(String nascimento){
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate data = LocalDate.parse(nascimento, formatter);
			LocalDate hoje = LocalDate.now();
			Period idade = Period.between(data, hoje);
			if(idade.getYears() > 16 && idade.getYears() <150) {
                                System.out.println("Você ta certo boa! ano idade");
				return true;
			}
			else { 
				System.out.println("Usuário não pode ter mais que 150 anos e nem menos de 16");
				return false;}
		} 
		catch (DateTimeParseException e) {
                        System.out.println(e.getMessage());
			return false;
			}
		}
	
	public static boolean isCEP(String cep) {
		cep = cep.replaceAll("[()\\s-]","");
		String rgxCEP = "^[0-9]{8}$";
		Pattern padrao = Pattern.compile(rgxCEP);
		Matcher igual = padrao.matcher(cep);
		
                if(igual.matches() == true){
                    System.out.println("Ta certo");
                    return igual.matches();
                }
                else{
                    System.out.println("Ta errado");
                    return false;
                }
	}
	
	public static boolean isEndereco1(String pais) {
            String rgxPais = "^[a-zA-ZÀ-ÿ\\s]+$"; //REGEX ALTERADO: "^[A-Za-z]$" > "^[A-Za-zÀ-ÿ\\s]+$"
            Pattern padrao = Pattern.compile(rgxPais);
            Matcher igual = padrao.matcher(pais);
            if(pais.length() >1 && pais.length()<50 && igual.matches() == true) {
                System.out.println("Endereço 1 Válido: " + pais);
                return igual.matches();
            }
            else {
                System.out.println("Endereço 1 Inválido: " + pais);
		return false;
            }
	}
	
	public static boolean isEndereco2(String rua) {
		if(rua.length() >1 && rua.length()<50) {
			String rgxEndereco = "^[A-Za-zÀ-ÿ0-9\\s]+$"; //REGEX ALTERADO: ^[A-Za-z0-9]$ > ^[A-Za-zÀ-ÿ0-9\\s]+$
			Pattern padrao = Pattern.compile(rgxEndereco);
			Matcher igual = padrao.matcher(rua);
                        if(igual.matches() == true){
                            System.out.println("Endereço 2 Válido: " + rua);
                            return igual.matches();
                        }
                        return true;
		}
                else {
                    System.out.println("Endereço 2 inválido: " + rua);
			return false;
		}
	}
	public static boolean isEmail(String email) {
		String rgxEmail = "^[A-Za-z0-9-+_.-]+@+[A-Za-z0-9.-]+$";
		Pattern padrao = Pattern.compile(rgxEmail);
		Matcher igual = padrao.matcher(email);
                if(igual.matches()){
                    System.out.println("Email Funcionando");
                    return igual.matches();
                }
                else{
                    System.out.println("Email Errado");
                    return false;
                }
	}
	
	public static boolean isNome(String nome) {
		if(nome.length() >1 && nome.length()<50) {
			String rgxNome = "^[A-Za-zÀ-ÿ0-9\\s]+$"; //REGEX ALTERADO: ^[A-Za-z0-9]$ > ^[A-Za-zÀ-ÿ0-9\\s]+$
			Pattern padrao = Pattern.compile(rgxNome);
			Matcher igual = padrao.matcher(nome);
                        System.out.println("Ta certo!");
			return igual.matches();
		}else {
                    System.out.println("Ta errado!!");
			return false;
		}
	}
	
	public static boolean isCNH(String cnh) {
		cnh = cnh.replaceAll("[()\\s-]","");
		if(cnh.matches("\\d{11}")){
                    System.out.println("Ta certo a cnh");
                    return cnh.matches("\\d{11}");
                }
                else{
                    System.out.println("ta errado a cnh");
                    return false;
                }
	}
	
	public static boolean isTelefone(String telefone) {
		//Retira todos os caracteres especiais
		telefone = telefone.replaceAll("[()\\s-]","");
		//Verifica se o comprimento da string é 10 ou 11
                if(telefone.matches("\\d{10,11}")) {
                        System.out.println("Telefone válido");
                        return telefone.matches("\\d{10,11}");
		}
                else {
			String mensagem = "Telefone invalido!";
			System.out.println(mensagem);
                        return false;
		}
	}
	
	public static boolean isCPF(String cpf) {
		cpf = cpf.replaceAll("[()\\s-]","");
		// Verificando se os digitos não são iguais ou se a quantidade de digitos é diferente de 11.
		if(cpf.equals("00000000000")||cpf.equals("11111111111")||cpf.equals("22222222222")||
			cpf.equals("33333333333")||cpf.equals("44444444444")||cpf.equals("555555555555")||
			cpf.equals("66666666666")||cpf.equals("77777777777")||cpf.equals("88888888888")||
			cpf.equals("99999999999")||cpf.length()!=11) {
                    System.out.println("Esse CPF NAO EXISTE");
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
                            System.out.println("Ta certo o CPF");
                            return true;
			}
			else {
                            System.out.println("Esse CPF NAO EXISTE");
                            return false;
			}
			 
		} catch (InputMismatchException e) {
			
			return false;
		
		}	
	}
	
	
}
