package DataAcessObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;

import Conexao.ConnectorJC;
import Entidades.Modelo;

public class DAOModelo {

    public void cadastrarModelo(Modelo modelo) {

        String sql = "INSERT INTO Modelo (nome_modelo, fabricante, ano_fabricacao) VALUES (?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, modelo.getIdModelo());
            ps.setString(2, modelo.getFabricante());
            ps.setInt(3, modelo.getAnoFabricacao().getValue());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                modelo.setIdModelo(idGerado);
            }

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public static Modelo buscarPorId(int idModelo) {
        Modelo modelo = null;
        String sql = "SELECT * FROM modelo WHERE id_modelo = ?";

        try (PreparedStatement ps = ConnectorJC.getConexao().prepareStatement(sql)) {
            ps.setInt(1, idModelo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                modelo = new Modelo();
                modelo.setIdModelo(rs.getInt("id_modelo"));
                modelo.setNomeModelo(rs.getString("nome_modelo"));
                modelo.setFabricante(rs.getString("fabricante"));

                // Tratando o ano, assumindo que é armazenado como INT ou YEAR
                int ano = rs.getInt("ano_fabricacao");
                modelo.setAnoFabricacao(Year.of(ano));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar modelo por ID: " + e.getMessage());
        }

        return modelo;
    }

}
