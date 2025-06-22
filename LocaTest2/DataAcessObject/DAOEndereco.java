package DataAcessObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Conexao.ConnectorJC;
import Entidades.Endereco;

public class DAOEndereco {
    
    public void cadastrarEndereco (Endereco endereco) {

        String sql = "INSERT INTO Endereco(id_pessoa, CEP, PAIS, ESTADO, CIDADE, BAIRRO, RUA, NUMERO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

    try {
        ps = ConnectorJC.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1,endereco.getPessoa().getIdPessoa());
        ps.setString(2, endereco.getCep());
        ps.setString(3, endereco.getPais());
        ps.setString(4, endereco.getEstado());
        ps.setString(5, endereco.getCidade());
        ps.setString(6, endereco.getBairro());
        ps.setString(7, endereco.getRua());
        ps.setString(8, endereco.getNumero());

        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
            int idGerado = rs.getInt(1);
            endereco.setIdEndereco(idGerado);
            }

        ps.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }

    }
}
