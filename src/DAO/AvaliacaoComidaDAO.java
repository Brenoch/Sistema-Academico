package DAO;

import util.Avaliacao;
import util.AvaliacaoComida;
import java.sql.*;
import java.util.ArrayList;

public class AvaliacaoComidaDAO implements BaseDAO {
    private final Connection connection;

    public AvaliacaoComidaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(Object entity) {
        if (!(entity instanceof AvaliacaoComida)) {
            throw new IllegalArgumentException("Tipo inválido. Esperado AvaliacaoComida.");
        }

        AvaliacaoComida avaliacao = (AvaliacaoComida) entity;
        String sql = "INSERT INTO avaliacao_comida (nota_comida) VALUES (?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setFloat(1, avaliacao.getNotaComida());
            pstm.executeUpdate();

            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    avaliacao.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar avaliação", e);
        }
    }

    @Override
    public void atualizar(Object entity) {
        if (!(entity instanceof AvaliacaoComida)) {
            throw new IllegalArgumentException("Tipo inválido. Esperado AvaliacaoComida.");
        }

        AvaliacaoComida avaliacao = (AvaliacaoComida) entity;
        String sql = "UPDATE avaliacao_comida SET nota_comida = ? WHERE id = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setFloat(1, avaliacao.getNotaComida());
            pstm.setInt(2, avaliacao.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar avaliação", e);
        }
    }

    @Override
    public void excluir(Object entity) {
        if (entity instanceof AvaliacaoComida) {
            excluir(((AvaliacaoComida) entity).getId());
        } else {
            throw new IllegalArgumentException("Tipo inválido. Esperado AvaliacaoComida.");
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM avaliacao_comida WHERE id = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir avaliação", e);
        }
    }

    @Override
    public AvaliacaoComida findById(int id) {
        String sql = "SELECT id, nota_comida FROM avaliacao_comida WHERE id = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);

            try (ResultSet rs = pstm.executeQuery()) {  // Nome da variável: rs
                if (rs.next()) {
                    AvaliacaoComida avaliacao = new AvaliacaoComida(rs.getFloat("nota_comida"));  // Corrigido para 'rs'
                    avaliacao.setId(rs.getInt("id"));
                    avaliacao.setNotaComida(rs.getFloat("nota_comida"));  // Corrigido nome do método (setNotaComida)
                    return avaliacao;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar avaliação por ID", e);
        }
        return null;
    }

    @Override
    public ArrayList<Object> findAllLazyLoading() {
        ArrayList<Object> avaliacoes = new ArrayList<>();
        String sql = "SELECT id, nota_comida FROM avaliacao_comida";

        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                AvaliacaoComida avaliacao = new AvaliacaoComida(rs.getFloat(("nota_comida")));
                avaliacao.setId(rs.getInt("id"));
                avaliacao.setNotaComida(rs.getFloat("nota_comida"));
                avaliacoes.add(avaliacao);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as avaliações", e);
        }
        return avaliacoes;
    }

    @Override
    public ArrayList<Object> findAllEagerLoading() {
        // Neste caso simples, eager loading é igual ao lazy loading
        return findAllLazyLoading();
    }
}