package DAO;

import util.Restaurante;
import java.sql.*;
import java.util.ArrayList;

public class RestauranteDAO implements BaseDAO {
    private final Connection connection;

    public RestauranteDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(Object entity) {
        if (!(entity instanceof Restaurante)) {
            throw new IllegalArgumentException("Objeto deve ser do tipo Restaurante.");
        }
        Restaurante restaurante = (Restaurante) entity;
        String sql = "INSERT INTO restaurante (nome, local, datasql) VALUES (?, ?, ?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, restaurante.getNome());
            pstm.setString(2, restaurante.getLocal());
            pstm.setDate(3, restaurante.getDatasql());
            pstm.executeUpdate();
            try (ResultSet rst = pstm.getGeneratedKeys()) {
                if (rst.next()) {
                    restaurante.setIdrestaurante(rst.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizar(Object entity) {
        if (!(entity instanceof Restaurante)) {
            throw new IllegalArgumentException("Objeto deve ser do tipo Restaurante.");
        }
        Restaurante restaurante = (Restaurante) entity;
        String sql = "UPDATE restaurante SET nome = ?, local = ?, datasql = ? WHERE idrestaurante = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, restaurante.getNome());
            pstm.setString(2, restaurante.getLocal());
            pstm.setDate(3, restaurante.getDatasql());
            pstm.setInt(4, restaurante.getIdrestaurante());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void excluir(Object entity) {
        if (!(entity instanceof Restaurante)) {
            throw new IllegalArgumentException("Objeto deve ser do tipo Restaurante.");
        }
        Restaurante restaurante = (Restaurante) entity;
        excluir(restaurante.getIdrestaurante());
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM restaurante WHERE idrestaurante = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object findById(int id) {
        String sql = "SELECT idrestaurante, nome, local, datasql FROM restaurante WHERE idrestaurante = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            try (ResultSet rst = pstm.executeQuery()) {
                if (rst.next()) {
                    Restaurante restaurante = new Restaurante();
                    restaurante.setIdrestaurante(rst.getInt("idrestaurante"));
                    restaurante.setNome(rst.getString("nome"));
                    restaurante.setLocal(rst.getString("local"));
                    restaurante.setDatasql(rst.getDate("datasql"));
                    return restaurante;
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
        String sql = "SELECT idrestaurante, nome, local, datasql FROM restaurante";
        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {
            while (rst.next()) {
                Restaurante restaurante = new Restaurante();
                restaurante.setIdrestaurante(rst.getInt("idrestaurante"));
                restaurante.setNome(rst.getString("nome"));
                restaurante.setLocal(rst.getString("local"));
                restaurante.setDatasql(rst.getDate("datasql"));
                lista.add(restaurante);
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
}