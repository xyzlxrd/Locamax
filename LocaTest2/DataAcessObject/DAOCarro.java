package DataAcessObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Conexao.ConnectorJC;
import Entidades.Carro;
import Entidades.Colaborador;

public class DAOCarro {

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
            ps.setString(7, carro.getTipoCombustivel());
            ps.setString(8, carro.getPotenciaMotor());
            ps.setString(9, carro.getCambio());
            ps.setString(10, carro.getCapacidadeTanque());
            ps.setString(11, carro.getArCondicionado());
            ps.setBoolean(12, carro.getAirbag());
            ps.setBoolean(13, carro.getCor());
            ps.setString(14, carro.getDisponibilidade());

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
}
