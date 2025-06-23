package TESTES;

import java.util.Random;

public class GerarCPF {

    public static String gerarCPF() {
        Random random = new Random();
        int[] cpf = new int[11];

        // Gera os 9 primeiros dígitos
        for (int i = 0; i < 9; i++) {
            cpf[i] = random.nextInt(10);
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += cpf[i] * (10 - i);
        }
        int resto = soma % 11;
        cpf[9] = (resto < 2) ? 0 : 11 - resto;

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += cpf[i] * (11 - i);
        }
        resto = soma % 11;
        cpf[10] = (resto < 2) ? 0 : 11 - resto;

        // Monta o CPF formatado
        return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d",
            cpf[0], cpf[1], cpf[2],
            cpf[3], cpf[4], cpf[5],
            cpf[6], cpf[7], cpf[8],
            cpf[9], cpf[10]);
    }

    public static void main(String[] args) {
        String cpfValido = gerarCPF();
        System.out.println("CPF válido gerado: " + cpfValido);
    }
}
