package DAO;

import util.Classificacao;
import java.sql.*;
import java.util.ArrayList;

public class ClassificacaoDAO implements BaseDAO {
    private final Connection connection;

    public ClassificacaoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(Object entity) {
        if (!(entity instanceof Classificacao)) {
            throw new IllegalArgumentException("Objeto deve ser do tipo Classificacao.");
        }
        Classificacao classificacao = (Classificacao) entity;
        String sql = "INSERT INTO classificacao (avaliacao_localizacao, avaliacao_ambiente, avaliacao_comida, avaliacao_atendimento) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setFloat(1, classificacao.avaliacaoLocalizacao.getNota());
            pstm.setFloat(2, classificacao.avaliacaoAmbiente.getNota());
            pstm.setFloat(3, classificacao.avaliacaoComida.getNota());
            pstm.setFloat(4, classificacao.avaliacaoAtendimento.getNota());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object findById(int id) {
        String sql = "SELECT id, avaliacao_localizacao, avaliacao_ambiente, avaliacao_comida, avaliacao_atendimento FROM classificacao WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            try (ResultSet rst = pstm.executeQuery()) {
                if (rst.next()) {
                    Classificacao c = new Classificacao(
                        rst.getFloat("avaliacao_localizacao"),
                        rst.getFloat("avaliacao_ambiente"),
                        rst.getFloat("avaliacao_comida"),
                        rst.getFloat("avaliacao_atendimento")
                    );
                    return c;
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
        String sql = "SELECT id, avaliacao_localizacao, avaliacao_ambiente, avaliacao_comida, avaliacao_atendimento FROM classificacao";
        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {
            while (rst.next()) {
                Classificacao c = new Classificacao(
                    rst.getFloat("avaliacao_localizacao"),
                    rst.getFloat("avaliacao_ambiente"),
                    rst.getFloat("avaliacao_comida"),
                    rst.getFloat("avaliacao_atendimento")
                );
                lista.add(c);
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
        if (!(entity instanceof Classificacao)) {
            throw new IllegalArgumentException("Objeto deve ser do tipo Classificacao.");
        }
        Classificacao classificacao = (Classificacao) entity;
        String sql = "UPDATE classificacao SET avaliacao_localizacao = ?, avaliacao_ambiente = ?, avaliacao_comida = ?, avaliacao_atendimento = ? WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setFloat(1, classificacao.avaliacaoLocalizacao.getNota());
            pstm.setFloat(2, classificacao.avaliacaoAmbiente.getNota());
            pstm.setFloat(3, classificacao.avaliacaoComida.getNota());
            pstm.setFloat(4, classificacao.avaliacaoAtendimento.getNota());
            pstm.setInt(5, 0);
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
        String sql = "DELETE FROM classificacao WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
