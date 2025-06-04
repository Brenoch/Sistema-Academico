import java.sql.*;

public class MyJDBC {
    public static void main(String[] args){
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://192.168.100.6:3306/avaliacao_restaurantes",
                    "programador",
                    "1234teste-"
            );

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM avaliacoes");

            while (resultSet.next()){
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Usuário ID: " + resultSet.getInt("usuario_id"));
                System.out.println("Restaurante ID: " + resultSet.getInt("restaurante_id"));
                System.out.println("Nota: " + resultSet.getInt("nota"));
                System.out.println("Comentário: " + resultSet.getString("comentario"));
                System.out.println("Data: " + resultSet.getDate("data_avaliacao"));
                System.out.println("--------------------------");
            }
            

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
