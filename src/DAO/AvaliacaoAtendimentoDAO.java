// src/DAO/AvaliacaoAtendimentoDAO.java
package DAO;

import util.AvaliacaoAtendimento;
import java.sql.*;
import java.util.ArrayList;

public class AvaliacaoAtendimentoDAO implements BaseDAO {
    private final Connection connection;

    public AvaliacaoAtendimentoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(Object entity) {
        if (!(entity instanceof AvaliacaoAtendimento)) {
            throw new IllegalArgumentException("Objeto deve ser do tipo AvaliacaoAtendimento.");
        }
        AvaliacaoAtendimento avaliacao = (AvaliacaoAtendimento) entity;
        String sql = "INSERT INTO avaliacao_atendimento (nota_atendimento) VALUES (?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setFloat(1, avaliacao.getNotaAtendimento());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object findById(int id) {
        String sql = "SELECT id, nota_atendimento FROM avaliacao_atendimento WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            try (ResultSet rst = pstm.executeQuery()) {
                if (rst.next()) {
                    AvaliacaoAtendimento a = new AvaliacaoAtendimento(rst.getFloat("nota_atendimento"));
                    return a;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Object> findAllLazyLoading() {
        ArrayList<Object> lista = new ArrayList<>();
        String sql = "SELECT id, nota_atendimento FROM avaliacao_atendimento";
        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {
            while (rst.next()) {
                AvaliacaoAtendimento a = new AvaliacaoAtendimento(rst.getFloat("nota_atendimento"));
                lista.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public ArrayList<Object> findAllEagerLoading() {
        return findAllLazyLoading();
    }

    @Override
    public void atualizar(Object entity) {
        if (!(entity instanceof AvaliacaoAtendimento)) {
            throw new IllegalArgumentException("Objeto deve ser do tipo AvaliacaoAtendimento.");
        }
        AvaliacaoAtendimento avaliacao = (AvaliacaoAtendimento) entity;
        String sql = "UPDATE avaliacao_atendimento SET nota_atendimento = ? WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setFloat(1, avaliacao.getNotaAtendimento());
            pstm.setInt(2, 0); // Substitua 0 pelo id real
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void excluir(Object entity) {
        excluir(0); // Substitua 0 pelo id real
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM avaliacao_atendimento WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}