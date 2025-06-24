package javafxtest;

import java.sql.*;
import java.util.*;

public class IntegracaoParaCarros {
    private static final String url = "jdbc:mysql://localhost:3306/applocadora?useSSL=false&serverTimezone=UTC";
    private static final String usuario = "root";
    private static final String senha = "";

    public static List<CarroGetSet> listarPorCategorias(List<Integer> categoriasSelecionadas) {
        List<CarroGetSet> carros = new ArrayList<>();
        if (categoriasSelecionadas.isEmpty()) return carros;

        String placeholders = String.join(",", Collections.nCopies(categoriasSelecionadas.size(), "?"));

        String sql = "SELECT c.*, cat.* " + //se alguem tiver com duvida cat é abreviacao pra categoria e c é de carro /---------------/
                     "FROM carro c " +
                     "JOIN categoria cat ON c.id_categoria = cat.id_categoria " +
                     "WHERE c.id_categoria IN (" + placeholders + ")";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            for (int i = 0; i < categoriasSelecionadas.size(); i++) {
                stmt.setInt(i + 1, categoriasSelecionadas.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CarroGetSet carro = new CarroGetSet();
                carro.setIdCarro(rs.getInt("id_carro"));
                carro.setIdCategoria(rs.getInt("id_categoria"));
                carro.setIdModelo(rs.getInt("id_modelo"));
                carro.setNomeCarro(rs.getString("nome_carro"));
                carro.setPlaca(rs.getString("placa"));
                carro.setQntAssentos(rs.getInt("qnt_assentos"));
                carro.setQntPortas(rs.getInt("qnt_portas"));
                carro.setQuilometragem(rs.getDouble("quilometragem"));
                carro.setTipoCombustivel(rs.getString("tipo_combustivel"));
                carro.setPotenciaMotor(rs.getString("potencia_motor"));
                carro.setCambio(rs.getString("cambio"));
                carro.setCapacidadeTanque(rs.getDouble("capacidade_tanque"));
                carro.setArCondicionado(rs.getBoolean("arCondicionado"));
                carro.setAirbag(rs.getBoolean("airbag"));
                carro.setCor(rs.getString("cor"));
                carro.setDisponibilidade(rs.getString("disponibilidade"));
                carro.setValorDiaria(rs.getDouble("valor_diaria"));
                carro.setNomeCategoria(rs.getString("nome_categoria"));
                carro.setDescricao(rs.getString("descricao"));

                carros.add(carro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar carros: " + e.getMessage());
            e.printStackTrace();
        }
        return carros;
    }
    
        public static List<CarroGetSet> filtrarBusca(
        Integer idCategoria, Integer qntAssentos, String tipoCombustivel, String potenciaMotor){
        List<CarroGetSet> carros = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT c.*, cat.* FROM carro c JOIN categoria cat ON c.id_categoria = cat.id_categoria WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (idCategoria != null) {
            sql.append(" AND c.id_categoria = ?");
            parametros.add(idCategoria);
        }
        if (qntAssentos != null) {
            sql.append(" AND c.qnt_assentos = ?");
            parametros.add(qntAssentos);
        }
        if (tipoCombustivel != null) {
            sql.append(" AND c.tipo_combustivel LIKE ?");
            parametros.add("%" + tipoCombustivel + "%");
        }
        if (potenciaMotor != null) {
            sql.append(" AND c.potencia_motor LIKE ?");
            parametros.add("%" + potenciaMotor + "%");
        }

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conexao.prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CarroGetSet carro = new CarroGetSet();
                carro.setIdCarro(rs.getInt("id_carro"));
                carro.setIdCategoria(rs.getInt("id_categoria"));
                carro.setIdModelo(rs.getInt("id_modelo"));
                carro.setNomeCarro(rs.getString("nome_carro"));
                carro.setPlaca(rs.getString("placa"));
                carro.setQntAssentos(rs.getInt("qnt_assentos"));
                carro.setQntPortas(rs.getInt("qnt_portas"));
                carro.setQuilometragem(rs.getDouble("quilometragem"));
                carro.setTipoCombustivel(rs.getString("tipo_combustivel"));
                carro.setPotenciaMotor(rs.getString("potencia_motor"));
                carro.setCambio(rs.getString("cambio"));
                carro.setCapacidadeTanque(rs.getDouble("capacidade_tanque"));
                carro.setArCondicionado(rs.getBoolean("arCondicionado"));
                carro.setAirbag(rs.getBoolean("airbag"));
                carro.setCor(rs.getString("cor"));
                carro.setDisponibilidade(rs.getString("disponibilidade"));
                carro.setValorDiaria(rs.getDouble("valor_diaria"));
                carro.setNomeCategoria(rs.getString("nome_categoria"));
                carro.setDescricao(rs.getString("descricao"));
                carros.add(carro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar carros com filtros: " + e.getMessage());
            e.printStackTrace();
        }
        return carros;
    }
    public static List<CarroGetSet> listarTodos() {
        return listarPorCategorias(Arrays.asList(1, 2, 3, 4));
    }
}