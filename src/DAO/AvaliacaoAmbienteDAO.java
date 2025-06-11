package DAO;

import util.AvaliacaoAmbiente;
import java.sql.*;
import java.util.ArrayList;

public class AvaliacaoAmbienteDAO implements BaseDAO {
    private final Connection connection;

    public AvaliacaoAmbienteDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(Object entity) {
        if (!(entity instanceof AvaliacaoAmbiente)) {
            throw new IllegalArgumentException("Objeto deve ser do tipo AvaliacaoAmbiente.");
        }
        AvaliacaoAmbiente avaliacao = (AvaliacaoAmbiente) entity;
        String sql = "INSERT INTO avaliacao_ambiente (nota_ambiente) VALUES (?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setFloat(1, avaliacao.getNotaAmbiente());
            pstm.executeUpdate();
            try (ResultSet rst = pstm.getGeneratedKeys()) {
                if (rst.next()) {
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object findById(int id) {
        String sql = "SELECT id, nota_ambiente FROM avaliacao_ambiente WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            try (ResultSet rst = pstm.executeQuery()) {
                if (rst.next()) {
                    AvaliacaoAmbiente a = new AvaliacaoAmbiente(rst.getFloat("nota_ambiente"));
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
        String sql = "SELECT id, nota_ambiente FROM avaliacao_ambiente";
        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {
            while (rst.next()) {
                AvaliacaoAmbiente a = new AvaliacaoAmbiente(rst.getFloat("nota_ambiente"));
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
        if (!(entity instanceof AvaliacaoAmbiente)) {
            throw new IllegalArgumentException("Objeto deve ser do tipo AvaliacaoAmbiente.");
        }
        AvaliacaoAmbiente avaliacao = (AvaliacaoAmbiente) entity;
        String sql = "UPDATE avaliacao_ambiente SET nota_ambiente = ? WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setFloat(1, avaliacao.getNotaAmbiente());
            pstm.setInt(2, 0); // Substitua 0 pelo id real
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void excluir(Object entity) {
        excluir(0);
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM avaliacao_ambiente WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}