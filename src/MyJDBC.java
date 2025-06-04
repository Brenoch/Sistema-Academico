import java.sql.DriverManager;

public class MyJDBC {
    public static void main(String[] args){
        Connection connection = DriverManager.getConnection(
         "jdbc:mysql://"
        );
    }
}