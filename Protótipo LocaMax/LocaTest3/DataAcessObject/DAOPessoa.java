package DataAcessObject;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Conexao.ConnectorJC;
import Entidades.Pessoa;

public class DAOPessoa {

    public void cadastrarPessoa(Pessoa pessoa) {

        String sql = "INSERT INTO Pessoa(CPF, CNH, NOME, DATANASCIMENTO, TELEFONE, EMAIL, SEXO) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, pessoa.getCpf());
            ps.setString(2, pessoa.getCnh());
            ps.setString(3, pessoa.getNome());
            ps.setDate(4, Date.valueOf(pessoa.getDataNascimento()));
            ps.setString(5, pessoa.getTelefone());
            ps.setString(6, pessoa.getEmail());
            ps.setString(7, pessoa.getSexo());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                pessoa.setIdPessoa(idGerado);
            }
            
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        }
}
