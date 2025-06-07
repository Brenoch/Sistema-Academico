package DAO;

import util.AvaliacaoLocalizacao;
import java.sql.*;
import java.util.ArrayList;

public class AvaliacaoLocalizacaoDAO implements BaseDAO {
    private final Connection connection;

    public AvaliacaoLocalizacaoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(Object entity) {
        if (!(entity instanceof AvaliacaoLocalizacao)) {
            throw new IllegalArgumentException("Objeto deve ser do tipo AvaliacaoLocalizacao.");
        }
        AvaliacaoLocalizacao avaliacao = (AvaliacaoLocalizacao) entity;
        String sql = "INSERT INTO avaliacao_localizacao (nota_localizacao) VALUES (?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setFloat(1, avaliacao.getNotaLocalizacao());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object findById(int id) {
        String sql = "SELECT id, nota_localizacao FROM avaliacao_localizacao WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            try (ResultSet rst = pstm.executeQuery()) {
                if (rst.next()) {
                    AvaliacaoLocalizacao a = new AvaliacaoLocalizacao(rst.getFloat("nota_localizacao"));
                    // Se houver campo id, setar aqui: a.setId(rst.getInt("id"));
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
        String sql = "SELECT id, nota_localizacao FROM avaliacao_localizacao";
        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {
            while (rst.next()) {
                AvaliacaoLocalizacao a = new AvaliacaoLocalizacao(rst.getFloat("nota_localizacao"));
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
        if (!(entity instanceof AvaliacaoLocalizacao)) {
            throw new IllegalArgumentException("Objeto deve ser do tipo AvaliacaoLocalizacao.");
        }
        AvaliacaoLocalizacao avaliacao = (AvaliacaoLocalizacao) entity;
        String sql = "UPDATE avaliacao_localizacao SET nota_localizacao = ? WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setFloat(1, avaliacao.getNotaLocalizacao());
            pstm.setInt(2, 0);
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
        String sql = "DELETE FROM avaliacao_localizacao WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}