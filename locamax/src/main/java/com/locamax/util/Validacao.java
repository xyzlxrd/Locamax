package com.locamax.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public final class Validacao {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final Pattern REGEX_CEP      = Pattern.compile("^[0-9]{8}$");
    private static final Pattern REGEX_PAIS      = Pattern.compile("^[a-zA-ZÀ-ÿ\\s]+$");
    private static final Pattern REGEX_ENDERECO  = Pattern.compile("^[A-Za-zÀ-ÿ0-9\\s]+$");
    private static final Pattern REGEX_EMAIL     = Pattern.compile("^[A-Za-z0-9\\-+_.]+@[A-Za-z0-9.\\-]+$");
    private static final Pattern REGEX_NOME      = Pattern.compile("^[A-Za-zÀ-ÿ0-9\\s]+$");
    private static final Pattern REGEX_TELEFONE  = Pattern.compile("^\\d{10,11}$");
    private static final Pattern REGEX_CNH       = Pattern.compile("^\\d{11}$");

    private Validacao() {}

    public static int calcularDias(String dataInicio, String dataFim) {
        try {
            LocalDate inicio = LocalDate.parse(dataInicio, FORMATTER);
            LocalDate fim    = LocalDate.parse(dataFim,    FORMATTER);
            int dias = Period.between(inicio, fim).getDays();
            return dias >= 1 ? dias : 0;
        } catch (DateTimeParseException e) {
            return 0;
        }
    }

    public static boolean isIdadeValida(String nascimento) {
        try {
            LocalDate data  = LocalDate.parse(nascimento, FORMATTER);
            int anos = Period.between(data, LocalDate.now()).getYears();
            return anos > 16 && anos < 150;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isCPF(String cpf) {
        cpf = cpf.replaceAll("[()\\s\\-.]", "");
        if (cpf.length() != 11 || cpf.chars().distinct().count() == 1) return false;

        int[] d = new int[11];
        for (int i = 0; i < 11; i++) d[i] = cpf.charAt(i) - '0';

        int soma = 0;
        for (int i = 0; i < 9; i++) soma += d[i] * (10 - i);
        int v1 = (soma % 11 < 2) ? 0 : 11 - (soma % 11);

        soma = 0;
        for (int i = 0; i < 10; i++) soma += d[i] * (11 - i);
        int v2 = (soma % 11 < 2) ? 0 : 11 - (soma % 11);

        return v1 == d[9] && v2 == d[10];
    }

    public static boolean isCNH(String cnh) {
        return REGEX_CNH.matcher(cnh.replaceAll("[()\\s\\-]", "")).matches();
    }

    public static boolean isTelefone(String telefone) {
        return REGEX_TELEFONE.matcher(telefone.replaceAll("[()\\s\\-]", "")).matches();
    }

    public static boolean isCEP(String cep) {
        return REGEX_CEP.matcher(cep.replaceAll("[()\\s\\-]", "")).matches();
    }

    public static boolean isEmail(String email) {
        return REGEX_EMAIL.matcher(email).matches();
    }

    public static boolean isNome(String nome) {
        return nome != null && nome.length() > 1 && nome.length() < 50
                && REGEX_NOME.matcher(nome).matches();
    }

    public static boolean isNomeLocal(String valor) {
        return valor != null && valor.length() > 1 && valor.length() < 50
                && REGEX_PAIS.matcher(valor).matches();
    }

    public static boolean isEnderecoTexto(String valor) {
        return valor != null && valor.length() > 1 && valor.length() < 50
                && REGEX_ENDERECO.matcher(valor).matches();
    }
}
