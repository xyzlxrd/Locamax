package DataAcessObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import Conexao.ConnectorJC;
import Entidades.Carro;
import Entidades.Carro.Cambio;
import Entidades.Carro.Disponibilidade;
import Entidades.Carro.PotenciaMotor;
import Entidades.Carro.TipoCombustivel;
import Entidades.Categoria;
import Entidades.Cliente;
import Entidades.Colaborador;
import Entidades.Modelo;

public class DAOColaborador {

    public void cadastrarColaborador(Colaborador colaborador) {
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

    public static Disponibilidade converterDisponibilidade(String disponibilidade) {
        if (disponibilidade == null) return null;

        switch (disponibilidade.toUpperCase()) {
            case "DISPONÍVEL":
            case "DISPONIVEL":
                return Disponibilidade.Disponivel;

        case "ALUGADO":
            return Disponibilidade.Alugado;

            case "EM MANUTENÇÃO":
            case "EM MANUTENCAO":
                return Disponibilidade.Manutencao;

        default:
            throw new IllegalArgumentException("Status inválido no banco: " + disponibilidade);
        }
    }

    public static String disponibilidadeParaBanco(Disponibilidade disponibilidade) {
            if (disponibilidade == null) return null;

            switch (disponibilidade) {
                case Disponivel:
                    return "Disponível";

                case Alugado:
                    return "Alugado";

                case Manutencao:
                    return "Em manutenção";

                default:
                    return "Indefinido";
        }
    }

    public void atualizarDisponibilidade(int idCarro, Disponibilidade novoStatus) {
        String sql = "UPDATE carro SET disponibilidade = ? WHERE id_carro = ?";

        PreparedStatement ps = null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql);

            // Converte o enum para o texto do banco ("Disponível", etc.)
            ps.setString(1, disponibilidadeParaBanco(novoStatus));
            ps.setInt(2, idCarro);

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas > 0) {
            System.out.println("Status do carro atualizado com sucesso!");
            } else {
            System.out.println("Nenhum carro foi encontrado com o ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar status do carro: " + e.getMessage());
        }
    }

    public static String tipoCombustivelParaBanco(TipoCombustivel tipoCombustivel) {
            if (tipoCombustivel == null) return null;

            switch (tipoCombustivel) {
                case Gasolina:
                    return "Gasolina";

                case Etanol:
                    return "Etanol";

                case Diesel:
                    return "Diesel";

                case Hibrido:
                    return "Híbrido";

                case Flex:
                    return "Flex";

                case Eletrico:
                    return "Elétrico";

                default:
                    return "Indefinido";
        }
    }

    public static String potenciaMotorParaBanco(PotenciaMotor potenciaMotor) {
            if (potenciaMotor == null) return null;

            switch (potenciaMotor) {
                case Potencia1_0:
                    return "1.0";

                case Potencia1_3:
                    return "1.3";

                case Potencia1_4:
                    return "1.4";

                case Potencia1_5:
                    return "1.5";

                case Potencia1_6:
                    return "1.6";

                case Potencia1_8:
                    return "1.8";

                case Potencia2_0:
                    return "2.0";

                case Potencia2_0a2_9:
                    return "2.0 - 2.9";
                default:
                    return "Indefinido";
        }
    }
    
    public static String cambioParaBanco(Cambio cambio) {
            if (cambio == null) return null;

            switch (cambio) {
                case Manual:
                    return "Manual";

                case Automatico:
                    return "Automático";

                case CVT:
                    return "CVT";

                default:
                    return "Indefinido";
        }
    }

    public static TipoCombustivel converterTipoCombustivel(String banco) {

    switch(banco.toUpperCase()) {
        case "GASOLINA": 
            return TipoCombustivel.Gasolina;

        case "ETANOL":
            return TipoCombustivel.Etanol;

        case "DIESEL":
            return TipoCombustivel.Diesel;

        case "HÍBRIDO":
        case "HIBRIDO":
            return TipoCombustivel.Hibrido;

        case "FLEX":
            return TipoCombustivel.Flex;

        case "ELÉTRICO":
        case "ELETRICO":
            return TipoCombustivel.Eletrico;
            
        default:
            throw new IllegalArgumentException("Tipo de Combustível Inválido(a) no Banco: " + banco);
        }
    }

    public static PotenciaMotor converterPotenciaMotor(String banco) {
        switch (banco) {
            case "1.0":
                return PotenciaMotor.Potencia1_0;

            case "1.3":
                return PotenciaMotor.Potencia1_3;

            case "1.4":
                return PotenciaMotor.Potencia1_4;

            case "1.5":
                return PotenciaMotor.Potencia1_5;

            case "1.6":
                return PotenciaMotor.Potencia1_6;

            case "1.8":
                return PotenciaMotor.Potencia1_8;

            case "2.0":
                return PotenciaMotor.Potencia2_0;

            case "2.0 - 2.9":
                return PotenciaMotor.Potencia2_0a2_9;

            default:
                throw new IllegalArgumentException("Potência do Motor Inválido(a) no Banco: " + banco);
        }

    }

    public static Cambio converterCambio(String banco) {
        switch (banco.toUpperCase()) {
            case "MANUAL":
                return Cambio.Manual;

            case "AUTOMÁTICO":
            case "AUTOMATICO":
                return Cambio.Automatico;

            case "CVT":
                return Cambio.CVT;
        
            default:
                throw new IllegalArgumentException("Câmbio do Motor Inválido(a) no Banco: " + banco); 
        }

    }

    public static void cadastrarCarro(Carro carro, Colaborador colaborador) {

        String sql = "INSERT INTO Carro (id_categoria, id_modelo, nome_carro, placa, qnt_assentos, qnt_portas, quilometragem, tipo_combustivel, potencia_motor, cambio, capacidade_tanque, arCondicionado, airbag, cor, disponibilidade) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = null;

        try {
            ps = ConnectorJC.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, carro.getCategoria().getIdCategoria());
            ps.setInt(2, carro.getModelo().getIdModelo());

            ps.setString(3, carro.getNomeCarro());
            ps.setString(4, carro.getPlaca());
            ps.setInt(5, carro.getQntAssentos());
            ps.setInt(6, carro.getQntPortas());
            ps.setInt(7, carro.getQuilometragem());
            ps.setString(8, tipoCombustivelParaBanco(carro.getTipoCombustivel()));
            ps.setString(9, potenciaMotorParaBanco(carro.getPotenciaMotor()));
            ps.setString(10, cambioParaBanco(carro.getCambio()));
            ps.setInt(11, carro.getCapacidadeTanque());
            ps.setBoolean(12, carro.getArCondicionado());
            ps.setBoolean(13, carro.getAirbag());
            ps.setString(14, carro.getCor());
            ps.setString(15, disponibilidadeParaBanco(carro.getDisponibilidade()));

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                carro.setIdCarro(idGerado);
            }

            colaborador.adicionarCarro(carro);
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public static void removerCarro(int idCarro) {
        String sql = "DELETE FROM carro WHERE id_carro = ?";

        try (PreparedStatement ps = ConnectorJC.getConexao().prepareStatement(sql)) {
            ps.setInt(1, idCarro);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Carro> listarCarrosDoBanco() {
    List<Carro> carros = new ArrayList<>();
    String sql = "SELECT * FROM carro";

        try (PreparedStatement ps = ConnectorJC.getConexao().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Carro c = new Carro();

                c.setIdCarro(rs.getInt("id_carro"));

                //c.setIdCategoria(rs.getInt("id_categoria"));
                int idCategoria = rs.getInt("id_categoria");
                Categoria categoria = DAOCategoria.buscarPorId(idCategoria);
                c.setIdCategoria(idCategoria);
                c.setCategoria(categoria);

                //c.setIdModelo(rs.getInt("id_modelo"));
                int idModelo = rs.getInt("id_modelo");
                Modelo modelo = DAOModelo.buscarPorId(idModelo);
                c.setIdModelo(idModelo);
                c.setModelo(modelo);

                c.setNomeCarro(rs.getString("nome_carro"));
                c.setPlaca(rs.getString("placa"));
                c.setQntAssentos(rs.getInt("qnt_assentos"));
                c.setQntPortas(rs.getInt("qnt_portas"));
                c.setQuilometragem(rs.getInt("quilometragem"));
                c.setTipoCombustivel(converterTipoCombustivel(rs.getString("tipo_combustivel")));
                c.setPotenciaMotor(converterPotenciaMotor(rs.getString("potencia_motor")));
                c.setCambio(converterCambio(rs.getString("cambio")));
                c.setCapacidadeTanque(rs.getInt("capacidade_tanque"));
                c.setArCondicionado(rs.getBoolean("arCondicionado"));
                c.setAirbag(rs.getBoolean("airbag"));
                c.setCor(rs.getString("cor"));
                c.setDisponibilidade(converterDisponibilidade(rs.getString("disponibilidade")));

                carros.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carros;
    }

}
