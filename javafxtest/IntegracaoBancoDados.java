package javafxtest;

import java.sql.*;

public class IntegracaoBancoDados {
    private static final String url = "jdbc:mysql://localhost:3306/applocadora?useSSL=false&serverTimezone=UTC";

    public static int registroUsuario(String nome, String cpf, String telefone,
        String cep, String cidade, String rua, String numero, String bairro, String nascimento,
        String estado, String pais, String cnh){
        String sqlPessoa = "INSERT INTO pessoa (cpf, nome, telefone, dataNascimento, cnh) VALUES (?, ?, ?, ?, ?)";
        String sqlEndereco = "INSERT INTO endereco (id_pessoa, numero, rua, cep, bairro, cidade, estado, pais) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlCliente = "INSERT INTO cliente (id_pessoa) VALUES (?)";

        try (Connection conexao = DriverManager.getConnection(url, "root", "")) {
            conexao.setAutoCommit(false);
            PreparedStatement stmtPessoa = conexao.prepareStatement(sqlPessoa, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtPessoa.setString(1, cpf);
            stmtPessoa.setString(2, nome);
            stmtPessoa.setString(3, telefone);
            stmtPessoa.setString(4, nascimento);
            stmtPessoa.setString(5, cnh);
            stmtPessoa.executeUpdate();

            ResultSet rsPessoa = stmtPessoa.getGeneratedKeys();
            int idPessoa = -1;
            if (rsPessoa.next()) {
                idPessoa = rsPessoa.getInt(1);
            } 
            else {
                conexao.rollback();
                System.out.println("Erro: não foi possível obter o ID da pessoa.");
                return 0;
            }

            PreparedStatement stmtEndereco = conexao.prepareStatement(sqlEndereco);
            stmtEndereco.setInt(1, idPessoa);
            stmtEndereco.setString(2, numero);
            stmtEndereco.setString(3, rua);
            stmtEndereco.setString(4, cep);
            stmtEndereco.setString(5, bairro);
            stmtEndereco.setString(6, cidade);
            stmtEndereco.setString(7, estado);
            stmtEndereco.setString(8, pais);
            stmtEndereco.executeUpdate();

            PreparedStatement stmtCliente = conexao.prepareStatement(sqlCliente);
            stmtCliente.setInt(1, idPessoa);
            stmtCliente.executeUpdate();

            conexao.commit();
            return idPessoa;

        } 
        catch (SQLException e) {
            System.out.println("Erro ao realizar cadastro completo: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean registroDeLogin(int idPessoa, String usuario, String senha, String email) {
        String sql = "UPDATE cliente SET usuario = ?, senha = ?, email = ? WHERE id_pessoa = ?";
        try (Connection conexao = DriverManager.getConnection(url, "root", "");
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            stmt.setString(3, email);
            stmt.setInt(4, idPessoa);
            return stmt.executeUpdate() > 0;
        } 
        catch (SQLException e) {
            System.out.println("Erro ao registrar login: " + e.getMessage());
            return false;
        }
    }

    public static boolean verificarLogin(String username, String password) {
        String verificaLogin = "SELECT * FROM cliente WHERE usuario = ? AND senha = ?";
        try (Connection conexao = DriverManager.getConnection(url, "root", "");
            PreparedStatement stmt = conexao.prepareStatement(verificaLogin)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } 
        catch (SQLException e) {
            System.out.println("Erro na verificação de login: " + e.getMessage());
            return false;
        }
    }
}