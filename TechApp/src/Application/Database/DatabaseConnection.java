package Application.Database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gère la connexion à la base de données
 * @author Mathis Poncet
 */
public class DatabaseConnection {
    private static Connection con = null;
    private static String url = "jdbc:mysql://127.0.0.1:3306/mydb";
    private static String login = "root";
    private static String password = "";

    /**
     * Constructeur qui initialise la connexion à la base de donnnées "mydb"
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private DatabaseConnection() throws ClassNotFoundException, SQLException{
        /*Chargement du driver JDBC pour mysql */
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, login, password);
        }catch(SQLException e){
            System.err.println("La connexion à la BDD a échoué");
            e.printStackTrace();
        }
    }

    /**
     * Accesseur qui renvoie la connexion à la base de données
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getCon() throws ClassNotFoundException, SQLException {
        if ( con == null ){
            new DatabaseConnection();
        }
        return con;
    }
}
