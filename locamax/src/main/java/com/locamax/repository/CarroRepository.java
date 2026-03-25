package com.locamax.repository;

import com.locamax.model.Carro;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarroRepository {

    private static final String BASE_SELECT =
            "SELECT c.*, cat.nome_categoria, cat.valor_diaria " +
            "FROM carro c " +
            "JOIN categoria cat ON c.id_categoria = cat.id_categoria";

    public static List<Carro> listarTodos() {
        return executarConsulta(BASE_SELECT, Collections.emptyList());
    }

    public static List<Carro> filtrar(Integer idCategoria, Integer quantidadeAssentos,
                                      String tipoCombustivel, String potenciaMotor) {

        StringBuilder sql = new StringBuilder(BASE_SELECT).append(" WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (idCategoria != null) {
            sql.append(" AND c.id_categoria = ?");
            params.add(idCategoria);
        }
        if (quantidadeAssentos != null) {
            sql.append(" AND c.qnt_assentos = ?");
            params.add(quantidadeAssentos);
        }
        if (tipoCombustivel != null && !tipoCombustivel.isBlank()) {
            sql.append(" AND c.tipo_combustivel LIKE ?");
            params.add("%" + tipoCombustivel + "%");
        }
        if (potenciaMotor != null && !potenciaMotor.isBlank()) {
            sql.append(" AND c.potencia_motor LIKE ?");
            params.add("%" + potenciaMotor + "%");
        }

        return executarConsulta(sql.toString(), params);
    }

    private static List<Carro> executarConsulta(String sql, List<Object> params) {
        List<Carro> carros = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                carros.add(mapearResultado(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao consultar carros: " + e.getMessage());
        }

        return carros;
    }

    private static Carro mapearResultado(ResultSet rs) throws SQLException {
        Carro carro = new Carro();
        carro.setId(rs.getInt("id_carro"));
        carro.setIdCategoria(rs.getInt("id_categoria"));
        carro.setIdModelo(rs.getInt("id_modelo"));
        carro.setNome(rs.getString("nome_carro"));
        carro.setPlaca(rs.getString("placa"));
        carro.setQuantidadeAssentos(rs.getInt("qnt_assentos"));
        carro.setQuantidadePortas(rs.getInt("qnt_portas"));
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

        Date dataRetirada = rs.getDate("data_retirada");
        Date dataDevolucao = rs.getDate("data_devolucao");
        if (dataRetirada != null)  carro.setDataRetirada(dataRetirada.toLocalDate());
        if (dataDevolucao != null) carro.setDataDevolucao(dataDevolucao.toLocalDate());

        return carro;
    }
}
