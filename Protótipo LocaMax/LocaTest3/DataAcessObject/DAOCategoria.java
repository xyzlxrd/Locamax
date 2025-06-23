package DataAcessObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexao.ConnectorJC;
import Entidades.Categoria;

public class DAOCategoria {
   
    public void cadastrarCategoria(Categoria categoria) {//TESTAR


        String sql = "INSERT INTO Categoria(nome_categoria, valor_diaria) VALUES (?, ?)";

        PreparedStatement ps = null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql);

            ps.setString(1, categoria.getNomeCategoria());
            ps.setDouble(2, categoria.getValorDiaria());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Categoria buscarPorId(int idCategoria) {
        Categoria categoria = null;
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";

        try (PreparedStatement ps = ConnectorJC.getConexao().prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNomeCategoria(rs.getString("nome_categoria"));
                categoria.setValorDiaria(rs.getDouble("valor_diaria"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar categoria por ID: " + e.getMessage());
        }

        return categoria;
    }

}
