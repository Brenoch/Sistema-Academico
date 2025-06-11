package DAO;

import java.sql.*;
import java.util.ArrayList;
import util.Cliente;
import util.Avaliacao;

public class AvaliacaoDAO implements BaseDAO {

    private final Connection connection;

    public AvaliacaoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(Object objeto) {
        if (!(objeto instanceof Avaliacao)) {
            throw new IllegalArgumentException("Objeto deve ser do tipo Avaliacao.");
        }
        Avaliacao avaliacao = (Avaliacao) objeto;
        String sql = "INSERT INTO avaliacao (nota, fk_cliente) VALUES (?, ?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setFloat(1, avaliacao.getNota());
            pstm.setNull(2, Types.INTEGER);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Float> listAll(Cliente cliente) {
        ArrayList<Float> avaliacoes = new ArrayList<>();
        String sql = "SELECT nota FROM avaliacao WHERE fk_cliente = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, cliente.getIdcliente());
            try (ResultSet rst = pstm.executeQuery()) {
                while (rst.next()) {
                    avaliacoes.add(rst.getFloat("nota"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return avaliacoes;
    }

    @Override
    public Object findById(int id) {
        String sql = "SELECT nota FROM avaliacao WHERE idavaliacao = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            try (ResultSet rst = pstm.executeQuery()) {
                if (rst.next()) {
                    return rst.getFloat("nota");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Object> findAllLazyLoading() {
        ArrayList<Object> avaliacoes = new ArrayList<>();
        String sql = "SELECT nota FROM avaliacao";
        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {
            while (rst.next()) {
                avaliacoes.add(rst.getFloat("nota"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return avaliacoes;
    }

    @Override
    public ArrayList<Object> findAllEagerLoading() {
        return findAllLazyLoading();
    }

    @Override
    public void atualizar(Object objeto) {
        if (!(objeto instanceof Avaliacao)) {
            throw new IllegalArgumentException("Objeto deve ser do tipo Avaliacao.");
        }
        Avaliacao avaliacao = (Avaliacao) objeto;
        String sql = "UPDATE avaliacao SET nota = ? WHERE idavaliacao = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setFloat(1, avaliacao.getNota());
            pstm.setInt(2, 0);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void excluir(Object objeto) {
        if (!(objeto instanceof Avaliacao)) {
            throw new IllegalArgumentException("Objeto deve ser do tipo Avaliacao.");
        }
        excluir(0);
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM avaliacao WHERE idavaliacao = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}