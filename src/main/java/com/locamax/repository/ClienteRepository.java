package com.locamax.repository;

import java.sql.*;

public class ClienteRepository {
    public static int registrarCliente(String nome, String cpf, String telefone,
            String cep, String cidade, String rua, String numero,
            String bairro, String nascimento, String estado, String pais, String cnh) {

        String sqlPessoa   = "INSERT INTO pessoa (cpf, nome, telefone, dataNascimento, cnh) VALUES (?, ?, ?, ?, ?)";
        String sqlEndereco = "INSERT INTO endereco (id_pessoa, numero, rua, cep, bairro, cidade, estado, pais) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlCliente  = "INSERT INTO cliente (id_pessoa) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS)) {
                stmtPessoa.setString(1, cpf);
                stmtPessoa.setString(2, nome);
                stmtPessoa.setString(3, telefone);
                stmtPessoa.setString(4, nascimento);
                stmtPessoa.setString(5, cnh);
                stmtPessoa.executeUpdate();

                ResultSet keys = stmtPessoa.getGeneratedKeys();
                if (!keys.next()) {
                    conn.rollback();
                    return 0;
                }
                int idPessoa = keys.getInt(1);

                try (PreparedStatement stmtEndereco = conn.prepareStatement(sqlEndereco)) {
                    stmtEndereco.setInt(1, idPessoa);
                    stmtEndereco.setString(2, numero);
                    stmtEndereco.setString(3, rua);
                    stmtEndereco.setString(4, cep);
                    stmtEndereco.setString(5, bairro);
                    stmtEndereco.setString(6, cidade);
                    stmtEndereco.setString(7, estado);
                    stmtEndereco.setString(8, pais);
                    stmtEndereco.executeUpdate();
                }

                try (PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente)) {
                    stmtCliente.setInt(1, idPessoa);
                    stmtCliente.executeUpdate();
                }

                conn.commit();
                return idPessoa;

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao registrar cliente: " + e.getMessage());
            return 0;
        }
    }

    public static boolean registrarCredenciais(int idPessoa, String usuario, String senha, String email) {
        String sql = "UPDATE cliente SET usuario = ?, senha = ?, email = ? WHERE id_pessoa = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            stmt.setString(3, email);
            stmt.setInt(4, idPessoa);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao registrar credenciais: " + e.getMessage());
            return false;
        }
    }

    public static boolean autenticar(String usuario, String senha) {
        String sql = "SELECT 1 FROM cliente WHERE usuario = ? AND senha = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            return stmt.executeQuery().next();

        } catch (SQLException e) {
            System.err.println("Erro ao autenticar: " + e.getMessage());
            return false;
        }
    }
}
