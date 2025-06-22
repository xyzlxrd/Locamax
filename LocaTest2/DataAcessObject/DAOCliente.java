package DataAcessObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Conexao.ConnectorJC;
import Entidades.Cliente;

public class DAOCliente {

    public static void cadastrarCliente(Cliente cliente) {//CRIAR MÉTODO PRA ESCOLHER E/OU ALUGAR O CARRO

        String sql = "INSERT INTO Cliente (id_pessoa, usuario, senha) VALUES (?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, cliente.getIdPessoa());
            ps.setString(2, cliente.getUsuario());
            ps.setString(3, cliente.getSenha());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                cliente.setIdCliente(idGerado);
            }

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }

    public static Cliente efetuarLogin(String usuario, String senha) {

        Cliente clienteLogado = null;

        String sql = "SELECT * FROM cliente WHERE usuario = ? AND senha = ?";

        PreparedStatement ps = null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql);

            ps.setString(1, usuario);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                clienteLogado = new Cliente();

                clienteLogado.setIdCliente(rs.getInt("id_cliente"));
                clienteLogado.setIdPessoa(rs.getInt("id_pessoa"));
                clienteLogado.setUsuario(rs.getString("usuario"));
                clienteLogado.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro na verificação do login/Cliente: "+e.getMessage());
        }
            return clienteLogado;
        }
    }