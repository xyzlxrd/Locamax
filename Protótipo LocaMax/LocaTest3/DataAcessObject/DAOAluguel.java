package DataAcessObject;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Conexao.ConnectorJC;
import Entidades.Aluguel;
import Entidades.Aluguel.FormaPagamento;
import Entidades.Carro.Disponibilidade;

public class DAOAluguel {

    public static FormaPagamento converterFormaPagamento(String valor) {
        if (valor == null) return null;

        switch (valor.toUpperCase()) {
            case "CARTÃO DE CRÉDITO":
            case "CARTAO DE CREDITO":
                return FormaPagamento.CREDITO;

            case "CARTÃO DE DÉBITO":
            case "CARTAO DE DEBITO":
            case "CARTÃO DÉBITO":
            case "CARTAO DEBITO":
                return FormaPagamento.DEBITO;

            case "PIX":
                return FormaPagamento.PIX;

            case "TRANSFERÊNCIA":
            case "TRANSFERENCIA":
                return FormaPagamento.TRANSFERENCIA;

            case "BOLETO":
                return FormaPagamento.BOLETO;

            default:
                throw new IllegalArgumentException("Forma de pagamento inválida no banco: " + valor);
        }
    }

    public static String formaPagamentoParaBanco(FormaPagamento forma) {
        if (forma == null) return null;

        switch (forma) {
            case CREDITO:
                return "Cartão de Crédito";

            case DEBITO:
                return "Cartão Débito";

            case PIX:
                return "PIX";

            case TRANSFERENCIA:
                return "Transferência";

            case BOLETO:
                return "Boleto";
            default:
                return "Indefinido";
        }
}

        public void cadastrarAluguel(Aluguel aluguel) {
        String sql = "INSERT INTO aluguel (id_cliente, id_carro, data_retirada, data_devolucao, valor_contrato, forma_de_pagamento) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, aluguel.getCliente().getIdCliente());
            ps.setInt(2, aluguel.getCarro().getIdCarro());

            ps.setDate(3, java.sql.Date.valueOf(aluguel.getDataRetirada()));
            ps.setDate(4, java.sql.Date.valueOf(aluguel.getDataDevolucao()));
            ps.setTime(5, java.sql.Time.valueOf(aluguel.getHoraRetirada()));
            ps.setTime(6, java.sql.Time.valueOf(aluguel.getHoraDevolucao()));
            ps.setDouble(7, aluguel.getValorContrato());
            ps.setString(6, formaPagamentoParaBanco(aluguel.getFormaPagamento()));

            ps.executeUpdate();
            System.out.println("Aluguel registrado com sucesso!");

            // Atualiza o status do carro para ALUGADO
            DAOCarro daoCarro = new DAOCarro();
            daoCarro.atualizarDisponibilidade(aluguel.getCarro().getIdCarro(), Disponibilidade.Alugado);

            ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                aluguel.setIdAluguel(idGerado);
                }

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
