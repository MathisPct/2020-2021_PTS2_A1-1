package Application.Database;

import Application.Metier.User;
import Application.Metier.Tech;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

/**
 * Gère l'accès des utilisateurs aux données
 */
public class UserDao {
    private Connection con;

    /**
     * Constructeur qui permet de se connecter à la base de donnée
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public UserDao()throws ClassNotFoundException, SQLException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/mydb";
            String login = "root";
            String password = "";
            this.con = (Connection) DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Lit un utilisateur depuis la BD. En cas d'erreur, lève une exception.
     * Si aucun utilisateur est trouvé, la valeur de retour est null
     * @param aLogin login de l'utilisateur cherché
     * @param aPassHash valeur de hachage du mot de passe de l'utilisateur cherché
     * @return L'utilisateur chargé depuis la BD.
     */
    public User Read(String aLogin, String aPassHash) throws DaoError, SQLException {
        User user = null;
        Statement stmt = con.createStatement();
        String reqGetUser = "SELECT * FROM utilisateurs WHERE login = " + aLogin + "AND password = " + aPassHash;
        ResultSet rSet = stmt.executeQuery(reqGetUser);
        //parcours du rSet qui contient les résultats de reqGetUser
        while (rSet.next()){
            //si un user est trouvé avec le login et le password passé en paramètre
            if (!rSet.wasNull()){
                user = createUser(rSet.getInt("ID"), rSet);
            }
        }
        return user;
    }

    /**
     * Fonction qui crée une instance de personne à partir des données de la table
     * @param id valeur entière qui est la clé primaire de l'utilisateur
     * @param rSet contient les données de la table Utilisateurs
     * @return une instance de Utilisateurs
     */
    private User createUser(int id, ResultSet rSet){
        User user = null;
        return user;
    }

    /**
     * Met à jour l'utilisateur dans la BD
     * @param aUser L'utilisateur à mettre à jour
     */
    public void Update(User aUser) {
        throw new UnsupportedOperationException();
    }

    /**
     * Liste les techniciens
     * @return un ensemble d'utilisateurs qui sont des techniciens
     */
    public ArrayList<Tech> ListTechs() {
        throw new UnsupportedOperationException();
    }
}