package DAO;
 import java.util.ArrayList;

public interface BaseDAO {
    void salvar(Object entity);
    void atualizar(Object entity);
    void excluir(Object entity);
    Object findById(int id);
    ArrayList<Object> findAllLazyLoading();
    ArrayList<Object> findAllEagerLoading();
    void excluir(int id);
}
