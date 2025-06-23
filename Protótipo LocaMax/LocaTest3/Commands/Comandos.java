package Commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Conexao.ConnectorJC;
import DataAcessObject.DAOCategoria;
import DataAcessObject.DAOModelo;
import Entidades.Carro;
import Entidades.Carro.Cambio;
import Entidades.Carro.Disponibilidade;
import Entidades.Carro.PotenciaMotor;
import Entidades.Carro.TipoCombustivel;

public class Comandos {

/*


package DataAcessObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Conexao.ConnectorJC;
import Entidades.Carro;
import Entidades.Colaborador;
import Entidades.Carro.Cambio;
import Entidades.Carro.Disponibilidade;
import Entidades.Carro.PotenciaMotor;
import Entidades.Carro.TipoCombustivel;

public class DAOCarro {

    /*public static Disponibilidade converterDisponibilidade(String disponibilidade) {
        switch (disponibilidade.toUpperCase()) {
            case "DISPONÍVEL":
            case "DISPONIVEL":
                return Disponibilidade.Disponivel;

            case "ALUGADO":
                return Disponibilidade.Alugado;

            case "EM MANUTENÇÃO":
            case "EM MANUTENCAO":
                return Disponibilidade.Manutencao;

        default:
            throw new IllegalArgumentException("Status inválido no banco: " + disponibilidade);
        }
    }

        public static String disponibilidadeParaBanco(Disponibilidade disponibilidade) {
            if (disponibilidade == null) return null;

            switch (disponibilidade) {
                case Disponivel:
                    return "Disponível";

                case Alugado:
                    return "Alugado";

                case Manutencao:
                    return "Em manutenção";

                default:
                    return "Indefinido";
        }
    }

    public void atualizarDisponibilidade(int idCarro, Disponibilidade novoStatus) {
        String sql = "UPDATE carro SET disponibilidade = ? WHERE id_carro = ?";

        PreparedStatement ps = null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql);

            // Converte o enum para o texto do banco ("Disponível", etc.)
            ps.setString(1, disponibilidadeParaBanco(novoStatus));
            ps.setInt(2, idCarro);

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas > 0) {
            System.out.println("Status do carro atualizado com sucesso!");
            } else {
            System.out.println("Nenhum carro foi encontrado com o ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar status do carro: " + e.getMessage());
        }
    }

        public static String tipoCombustivelParaBanco(TipoCombustivel tipoCombustivel) {
            if (tipoCombustivel == null) return null;

            switch (tipoCombustivel) {
                case Gasolina:
                    return "Gasolina";

                case Etanol:
                    return "Etanol";

                case Diesel:
                    return "Diesel";

                case Hibrido:
                    return "Híbrido";

                case Flex:
                    return "Flex";

                case Eletrico:
                    return "Elétrico";

                default:
                    return "Indefinido";
        }
    }

        public static String potenciaMotorParaBanco(PotenciaMotor potenciaMotor) {
            if (potenciaMotor == null) return null;

            switch (potenciaMotor) {
                case Potencia1_0:
                    return "1.0";

                case Potencia1_3:
                    return "1.3";

                case Potencia1_4:
                    return "1.4";

                case Potencia1_5:
                    return "1.5";

                case Potencia1_6:
                    return "1.6";

                case Potencia1_8:
                    return "1.8";

                case Potencia2_0:
                    return "2.0";

                case Potencia2_0a2_9:
                    return "2.0 - 2.9";
                default:
                    return "Indefinido";
        }
    }
    
            public static String cambioParaBanco(Cambio cambio) {
            if (cambio == null) return null;

            switch (cambio) {
                case Manual:
                    return "Manual";

                case Automatico:
                    return "Automático";

                case CVT:
                    return "CVT";

                default:
                    return "Indefinido";
        }
    }

    public void cadastrarCarro(Carro carro) {

        String sql = "INSERT INTO Carro (id_categoria, id_modelo, placa, qnt_assentos, qnt_portas, quilometragem, tipo_combustivel, potencia_motor, cambio, capacidade_tanque, arCondicionado, airbag, cor, disponibilidade) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, carro.getCategoria().getIdCategoria());
            ps.setInt(2, carro.getModelo().getIdModelo());

            ps.setString(3, carro.getPlaca());
            ps.setInt(4, carro.getQntAssentos());
            ps.setInt(5, carro.getQntPortas());
            ps.setInt(6, carro.getQuilometragem());
            ps.setString(7, tipoCombustivelParaBanco(carro.getTipoCombustivel()));
            ps.setString(8, potenciaMotorParaBanco(carro.getPotenciaMotor()));
            ps.setString(9, cambioParaBanco(carro.getCambio()));
            ps.setInt(10, carro.getCapacidadeTanque());
            ps.setBoolean(11, carro.getArCondicionado());
            ps.setBoolean(12, carro.getAirbag());
            ps.setString(13, carro.getCor());
            ps.setString(14, disponibilidadeParaBanco(carro.getDisponibilidade()));

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                carro.setIdCarro(idGerado);
            }

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public List<Carro> listarDisponiveis() {
        List<Carro> carrosDisponiveis = new ArrayList<>();

        String sql = "SELECT * FROM carro WHERE disponibilidade = 'Disponível'";

        try (PreparedStatement ps = ConnectorJC.getConexao().prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Carro carro = new Carro();
            carro.setIdCarro(rs.getInt("id_carro"));
            carro.setPlaca(rs.getString("placa"));

            int idModelo = rs.getInt("id_modelo");
            int idCategoria = rs.getInt("id_categoria");

            carro.setModelo(DAOModelo.buscarPorId(idModelo));
            carro.setCategoria(DAOCategoria.buscarPorId(idCategoria));
            carro.setDisponibilidade(Disponibilidade.valueOf(rs.getString("disponibilidade")));

            carrosDisponiveis.add(carro);
        }
        } catch (SQLException e) {
        System.out.println("Erro ao listar carros disponíveis: " + e.getMessage());
        }

        return carrosDisponiveis;
    }
}*/














/*
package ConversorENUM;

import Entidades.Carro.Cambio;
import Entidades.Carro.PotenciaMotor;
import Entidades.Carro.TipoCombustivel;

public class EnumHelper {
    
    public static TipoCombustivel converterTipoCombustivel(String banco) {

    switch(banco.toUpperCase()) {
        case "GASOLINA": 
            return TipoCombustivel.Gasolina;

        case "ETANOL":
            return TipoCombustivel.Etanol;

        case "DIESEL":
            return TipoCombustivel.Diesel;

        case "HÍBRIDO":
        case "HIBRIDO":
            return TipoCombustivel.Hibrido;

        case "FLEX":
            return TipoCombustivel.Flex;

        case "ELÉTRICO":
        case "ELETRICO":
            return TipoCombustivel.Eletrico;
            
        default:
            throw new IllegalArgumentException("Tipo de Combustível Inválido(a) no Banco: " + banco);
        }
    }

    public static PotenciaMotor converterPotenciaMotor(String banco) {
        switch (banco) {
            case "1.0":
                return PotenciaMotor.Potencia1_0;

            case "1.3":
                return PotenciaMotor.Potencia1_3;

            case "1.4":
                return PotenciaMotor.Potencia1_4;

            case "1.5":
                return PotenciaMotor.Potencia1_5;

            case "1.6":
                return PotenciaMotor.Potencia1_6;

            case "1.8":
                return PotenciaMotor.Potencia1_8;

            case "2.0":
                return PotenciaMotor.Potencia2_0;

            case "2.0 - 2.9":
                return PotenciaMotor.Potencia2_0a2_9;

            default:
                throw new IllegalArgumentException("Potência do Motor Inválido(a) no Banco: " + banco);
        }

    }

    public static Cambio converterCambio(String banco) {
        switch (banco.toUpperCase()) {
            case "MANUAL":
                return Cambio.Manual;

            case "AUTOMÁTICO":
            case "AUTOMATICO":
                return Cambio.Automatico;

            case "CVT":
                return Cambio.CVT;
        
            default:
                throw new IllegalArgumentException("Câmbio do Motor Inválido(a) no Banco: " + banco); 
        }

    }
}
 */


























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
