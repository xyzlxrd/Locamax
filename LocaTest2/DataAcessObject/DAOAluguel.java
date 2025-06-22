package DataAcessObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Conexao.ConnectorJC;
import Entidades.Aluguel;

public class DAOAluguel {
    
    public void cadastrarAluguel(Aluguel aluguel) {

        String sql = "INSERT INTO Aluguel (id_cliente, id_carro, data_retirada, data_devolucao, hora_retirada, hora_devolucao, valor_contrato, forma_de_pagamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
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
            ps.setString(8, aluguel.getFormaPagamento());

            ps.executeUpdate();

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
