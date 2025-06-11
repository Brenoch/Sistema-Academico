package DAO;
                        import util.Avaliacao;
                        import util.AvaliacaoComida;
                        import util.Cliente;
                        import java.sql.*;
                        import java.util.ArrayList;

                        public class ClienteDAO implements BaseDAO {
                            private final Connection connection;

                            public ClienteDAO(Connection connection) {
                                this.connection = connection;
                            }

                            @Override
                            public void salvar(Object objeto) {
                                if (!(objeto instanceof Cliente)) {
                                    throw new IllegalArgumentException("Objeto deve ser do tipo Cliente.");
                                }
                                Cliente cliente = (Cliente) objeto;
                                String sql = "INSERT INTO cliente (cpf, nome, email, senha) VALUES (?, ?, ?, ?)";
                                try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                                    pstm.setString(1, cliente.getCpf());
                                    pstm.setString(2, cliente.getNome());
                                    pstm.setString(3, cliente.getEmail());
                                    pstm.setString(4, cliente.getSenha());
                                    pstm.executeUpdate();
                                    try (ResultSet rst = pstm.getGeneratedKeys()) {
                                        if (rst.next()) {
                                            cliente.setId(rst.getInt(1));
                                        }
                                    }
                                    AvaliacaoDAO tdao = new AvaliacaoDAO(connection);
                                    for (Avaliacao avaliacao : Avaliacao.getAvaliacao()) {
                                        tdao.salvar(avaliacao);
                                    }
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            @Override
                            public Object findById(int id) {
                                String sql = "SELECT idcliente, cpf, nome, email, senha FROM cliente WHERE idcliente = ?";
                                try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                                    pstm.setInt(1, id);
                                    try (ResultSet rst = pstm.executeQuery()) {
                                        if (rst.next()) {
                                            int idcliente = rst.getInt("idcliente");
                                            String cpf = rst.getString("cpf");
                                            String nome = rst.getString("nome");
                                            String email = rst.getString("email");
                                            String senha = rst.getString("senha");
                                            return new Cliente(idcliente, cpf, nome, email, senha);
                                        }
                                    }
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                return null;
                            }

                            @Override
                            public ArrayList<Object> findAllLazyLoading() {
                                ArrayList<Object> clientes = new ArrayList<>();
                                String sql = "SELECT idcliente, cpf, nome, email, senha FROM cliente";
                                try (PreparedStatement pstm = connection.prepareStatement(sql);
                                     ResultSet rst = pstm.executeQuery()) {
                                    while (rst.next()) {
                                        int idcliente = rst.getInt("idcliente");
                                        String cpf = rst.getString("cpf");
                                        String nome = rst.getString("nome");
                                        String email = rst.getString("email");
                                        String senha = rst.getString("senha");
                                        clientes.add(new Cliente(idcliente, cpf, nome, email, senha));
                                    }
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                return clientes;
                            }

                            @Override
                            public ArrayList<Object> findAllEagerLoading() {
                                ArrayList<Object> clientes = new ArrayList<>();
                                String sql = "SELECT c.idcliente, c.cpf, c.nome, c.email, c.senha, a.idavaliacao, a.nota " +
                                        "FROM cliente AS c " +
                                        "LEFT JOIN avaliacao AS a ON c.idcliente = a.fk_cliente";
                                try (PreparedStatement pstm = connection.prepareStatement(sql);
                                     ResultSet rst = pstm.executeQuery()) {
                                    Cliente ultimo = null;
                                    while (rst.next()) {
                                        int idcliente = rst.getInt("idcliente");
                                        if (ultimo == null || ultimo.getIdcliente() != idcliente) {
                                            String cpf = rst.getString("cpf");
                                            String nome = rst.getString("nome");
                                            String email = rst.getString("email");
                                            String senha = rst.getString("senha");
                                            ultimo = new Cliente(idcliente, cpf, nome, email, senha);
                                            clientes.add(ultimo);
                                        }
                                        int idAvaliacao = rst.getInt("idavaliacao");
                                        if (idAvaliacao != 0) {
                                            float nota = rst.getFloat("nota");
                                            Avaliacao a = new Avaliacao() {
                                                @Override
                                                protected void comentar() {}
                                                @Override
                                                public float getNota() { return nota; }
                                            };
                                        }
                                    }
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                return clientes;
                            }

                            @Override
                            public void atualizar(Object objeto) {
                                if (!(objeto instanceof Cliente)) {
                                    throw new IllegalArgumentException("Objeto deve ser do tipo Cliente.");
                                }
                                Cliente cliente = (Cliente) objeto;
                                String sql = "UPDATE cliente SET cpf = ?, nome = ?, email = ?, senha = ? WHERE idcliente = ?";
                                try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                                    pstm.setString(1, cliente.getCpf());
                                    pstm.setString(2, cliente.getNome());
                                    pstm.setString(3, cliente.getEmail());
                                    pstm.setString(4, cliente.getSenha());
                                    pstm.setInt(5, cliente.getIdcliente());
                                    pstm.executeUpdate();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            @Override
                            public void excluir(Object entity) {
                                if (!(entity instanceof Cliente)) {
                                    throw new IllegalArgumentException("Objeto deve ser do tipo Cliente.");
                                }
                                Cliente cliente = (Cliente) entity;
                                excluir(cliente.getIdcliente());
                            }

                            @Override
                            public void excluir(int id) {
                                String sql = "DELETE FROM cliente WHERE idcliente = ?";
                                try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                                    pstm.setInt(1, id);
                                    int linhasAfetadas = pstm.executeUpdate();
                                    if (linhasAfetadas == 0) {
                                        throw new SQLException("Falha ao deletar: nenhuma linha foi afetada.");
                                    }
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }