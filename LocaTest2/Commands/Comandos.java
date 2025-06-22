package Commands;

public class Comandos {
    
        /*
        try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataDevolucao = LocalDate.parse(dataDevolucaoStr, formatter);
            } catch (DateTimeParseException exception) {
        System.out.println("Data de Devolucao Inválida. Siga a formatação (dd/MM/yyyy).");
        this.dataDevolucao = null;
        }*/

        /*
        try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.horaDevolucao = LocalTime.parse(horaDevolucaoStr, formatter);
            } catch (DateTimeParseException exception) {
        System.out.println("Data de Devolucao Inválida. Siga a formatação (HH:mm:ss).");
        this.horaDevolucao = null;
        }
        */

/*     public enum FormaDePagamento {
        CDC("Cartão de Crédito"),
        CD("Cartão Débito"),
        PIX("Pix"),
        TRF("Transferência"),
        BLT("Boleto");

        private final String dscString;
        FormaDePagamento(String descricaoString) {
            this.dscString = descricaoString;
        }

        public String getFormaPagamento() {
            return dscString;
        }
    }*/

    /*public static FormaDePagamento formaPagamento(String metodoPagamento) {
        for (FormaDePagamento metodo : FormaDePagamento.values()) {
            if (metodo.getFormaPagamento().equalsIgnoreCase(metodoPagamento)) {
                return metodo;
            }
        }
        return null;
    }*/

/*   

/*
    Pessoa pessoa = new Pessoa(null, null, null, null, null, null, null, null)

    System.out.print("CPF: ");
    String cpf = scanner.nextLine();

    System.out.print("CNH: ");
    String cnh = scanner.nextLine();

    System.out.print("NOME: ");
    String nome = scanner.nextLine();

    System.out.print("DATA NASCIMENTO (yyyy-MM-dd): ");
    String dataString = scanner.nextLine();
    LocalDate dataNascimento = LocalDate.parse(dataString);

    System.out.print("TELEFONE: ");
    String telefone = scanner.nextLine();

    System.out.print("E-MAIL: ");
    String email = scanner.nextLine();

    System.out.print("SEXO: ");
    String sexo = scanner.nextLine();

    pessoa.setCpf(cpf);
    pessoa.setCNH(cnh);
    pessoa.setNome(nome);
    pessoa.setNascimento(dataNascimento);
    pessoa.setTelefone(telefone);
    pessoa.setEmail(email);
    pessoa.setSexo(sexo);

    new DAOPessoa().cadastrarPessoa(pessoa);
*/

/*  -----------------TESTE DA CLASSE COLABORADOR-----------------

    Colaborador colaborador = new Colaborador(null, null, null, null, null, null, null, null, null, null);

    colaborador.setIdColaborador(pessoa.getIdPessoa());
    new DAOColaborador().cadastrarColaborador(colaborador);*/



/*  -----------------TESTE DA CLASSE ALUGUEL-----------------

    Aluguel aluguel = new Aluguel(null, null, null, null, 0, null);

    String respostaUsuario = null;
    String respostaHoraUsuario = null;

        System.out.print("A Data da Retirada do Veículo foi marcada? (Y/N): ");
        respostaUsuario = scanner.nextLine();

        if (respostaUsuario.equalsIgnoreCase("Y")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataMarcada;

            while (true) {
                System.out.print("Digite a data marcada (dd/MM/yyyy): ");
                dataMarcada = scanner.nextLine();

                try {
                    LocalDate dataConvertida = LocalDate.parse(dataMarcada, formatter);

                    if (dataConvertida.isBefore(LocalDate.now())) {
                        System.out.println("Data de Retirada Inválida (data no passado). Tente novamente.");
                    } else {
                        break;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de data inválido. Use o padrão dd/MM/yyyy.");
                }
            }
            aluguel.setDataRetirada(dataMarcada);
        }
            
    System.out.print("Data de Devolução: ");
    String dataDevolucao = scanner.nextLine();

    System.out.print("A Hora da Retirada do Veículo foi marcada? (Y/N): ");
    respostaHoraUsuario = scanner.nextLine();

        if (respostaHoraUsuario.equalsIgnoreCase("Y")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String horaMarcada;

            while (true) {
                System.out.print("Digite a Hora marcada (HH:mm): ");
                horaMarcada = scanner.nextLine();

                try {
                    LocalTime horaConvertida = LocalTime.parse(horaMarcada, formatter);

                    if (horaConvertida.isBefore(LocalTime.now())) {
                        System.out.println("Data de Retirada Inválida (data no passado). Tente novamente.");
                    } else {
                        break;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Formato inválido. Use o padrão HH:mm.");
                }
            }
            aluguel.setHoraRetirada(horaMarcada);
        }

    System.out.print("Hora de Devolucão: ");
    String horaDevolucao = scanner.nextLine();

    System.out.print("Valor da Contratação: ");
    double valorContratacao = scanner.nextDouble();

    System.out.println("Digite a forma de Pagamento");
    System.out.println("1) Cartão de Crédito");
    System.out.println("2) Cartão Débito");
    System.out.println("3) PIX");
    System.out.println("4) Transferência");
    System.out.println("5) Boleto");
    System.out.print("Opção: ");
    int opcaoPagamento = scanner.nextInt();

    String formaDePagamento = null;

    switch (opcaoPagamento) {
        case 1:
            formaDePagamento = "Cartão de Crédito";
        break;

        case 2:
            formaDePagamento = "Cartão Débito";
        break;

        case 3:
            formaDePagamento = "Pix";
        break;

        case 4:
            formaDePagamento = "Transferência";
        break;

        case 5:
            formaDePagamento = "Boleto";
        break;

        default:
            System.out.println("Opção Inválida.");
        break;
    }

    aluguel.setDataDevolucao(dataDevolucao);
    aluguel.setHoraDevolucao(horaDevolucao);
    aluguel.setValorContrato(valorContratacao);
    aluguel.setFormaPagamento(formaDePagamento);

    System.out.println("INFORMAÇÕES DO ALUGUEL:");
    System.out.println("Data de Retirada: "+(aluguel.getDataRetirada()));
    System.out.println("Data de Devolução: "+(aluguel.getDataDevolucao()));
    System.out.println("Hora de Retirada: "+(aluguel.getHoraRetirada()));
    System.out.println("Hora de Devolução: "+(aluguel.getHoraDevolucao()));
    System.out.println("Valor do Contrato: "+(aluguel.getValorContrato()));
    System.out.println("Forma de Pagamento: "+(aluguel.getFormaPagamento()));
    scanner.close();
    */

}
