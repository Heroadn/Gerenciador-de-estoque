package Classes;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author Edjandir Costa
 */
public class DBUtils {
    private static Connection conexao;
    
    public static Connection getConexao() {
        try {
            if (conexao == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexao = DriverManager.getConnection(
                   "jdbc:mysql://localhost/sistema?useTimezone=true&serverTimezone=UTC",
                        "root", "");
            }	
            return conexao;
        }
        catch(Exception ex){
            System.out.println("Erro conexçao: " +
                    ex.getMessage());
            return null;
        }
    }
}
