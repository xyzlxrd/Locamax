package DataAcessObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import Conexao.ConnectorJC;
import Entidades.Categoria;
import Entidades.Cliente;
import Entidades.Colaborador;
import Entidades.Modelo;

public class DAOColaborador {

    public static void cadastrarColaborador(Colaborador colaborador) {
        String sql = "INSERT INTO Colaborador (id_pessoa, matricula, senha) VALUES (?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, colaborador.getIdPessoa());
            ps.setString(2, colaborador.getMatricula());
            ps.setString(3, colaborador.getSenha());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                colaborador.setIdColaborador(idGerado);
            }

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public static Colaborador efetuarLogin(String matricula, String senha) {
        Colaborador colaboradorLogado = null;

        String sql = "SELECT * FROM colaborador WHERE matricula = ? AND senha = ?";

        PreparedStatement ps = null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql);

            ps.setString(1, matricula);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                colaboradorLogado = new Colaborador();

                colaboradorLogado.setIdColaborador(rs.getInt("id_colaborador"));
                colaboradorLogado.setIdPessoa(rs.getInt("id_pessoa"));
                colaboradorLogado.setMatricula(rs.getString("matricula"));
                colaboradorLogado.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro na verificação do login/Colaborador: "+e.getMessage());
        }
            return colaboradorLogado;
        }




        //--- MÉTODOS DO COLABORADOR COM A CLASSE/TABELA "CATEGORIA" ----

    public static void cadastrarCategoria(Categoria categoria, Colaborador colaborador) {
        String sql = "INSERT INTO Categoria(nome_categoria, valor_diaria) VALUES (?, ?)";

        PreparedStatement ps = null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, categoria.getNomeCategoria());
            ps.setDouble(2, categoria.getValorDiaria());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                categoria.setIdCategoria(idGerado);
            }

            colaborador.adicionarCategoria(categoria);
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removerCategoria(int idCategoria) {
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";

        try {
            
            PreparedStatement ps = null;

            ps = ConnectorJC.getConexao().prepareStatement(sql);

            ps.setInt(1, idCategoria);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Categoria> listarCategoriasDoBanco() {
        List<Categoria> categorias = new ArrayList<>();

        String sql = "SELECT * FROM Categoria";

        PreparedStatement ps =null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Categoria c = new Categoria();
            c.setIdCategoria(rs.getInt("id_categoria"));
            c.setNomeCategoria(rs.getString("nome_categoria"));
            c.setValorDiaria(rs.getDouble("valor_diaria"));
            categorias.add(c);
        }

        } catch (SQLException e) {
        e.printStackTrace();
        }

    return categorias;
    }



        //--- MÉTODOS DO COLABORADOR COM A CLASSE/TABELA "MODELO" ----

    public static void cadastrarModelo(Modelo modelo, Colaborador colaborador) {

        String sql = "INSERT INTO Modelo (nome_modelo, fabricante, ano_fabricacao) VALUES (?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, modelo.getNomeModelo());
            ps.setString(2, modelo.getFabricante());
            ps.setInt(3, modelo.getAnoFabricacao().getValue());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                modelo.setIdModelo(idGerado);
            }

            colaborador.adicionarModelo(modelo);
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    public static void removerModelo(int idModelo) {
        String sql = "DELETE FROM modelo WHERE id_modelo = ?";

        try {
            
            PreparedStatement ps = null;

            ps = ConnectorJC.getConexao().prepareStatement(sql);

            ps.setInt(1, idModelo);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Modelo> listarModelosDoBanco() {
        List<Modelo> modelos = new ArrayList<>();

        String sql = "SELECT * FROM modelo";

        PreparedStatement ps =null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Modelo m = new Modelo();
            m.setIdModelo(rs.getInt("id_modelo"));
            m.setNomeModelo(rs.getString("nome_modelo"));
            m.setFabricante(rs.getString("fabricante"));

            int ano = rs.getInt("ano_fabricacao");
            m.setAnoFabricacao(Year.of(ano));

            modelos.add(m);
        }

        } catch (SQLException e) {
        e.printStackTrace();
        }

    return modelos;
    }

        //--- MÉTODOS DO COLABORADOR COM A CLASSE/TABELA "CARRO" ----
}
