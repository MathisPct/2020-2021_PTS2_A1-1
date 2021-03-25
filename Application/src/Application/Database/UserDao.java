package Application.Database;

import com.mysql.jdbc.Connection;
import Application.Metier.Tech;
import Application.Metier.User;

import javax.swing.plaf.nimbus.State;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Gère l'accès des utilisateurs aux données
 * @author Mathis Poncet
 */
public class UserDao {
    /**
     * Attribut privée qui est la variable gérant la connexion avec la bdd
     */
    private Connection con;

    /**
     * Constructeur qui permet de se connecter à la base de donnée
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public UserDao()throws ClassNotFoundException, SQLException {
        /*Chargement du driver JDBC pour mysql */
        this.con = DatabaseConnection.getCon();
    }

    /**
     * Lit un utilisateur depuis la BD. En cas d'erreur, lève une exception de type DaoError.
     * @param aLogin login de l'utilisateur cherché
     * @param aPassHash valeur de hachage du mot de passe de l'utilisateur cherché
     * @return L'utilisateur chargé depuis la BD.
     * @exception Exception DaoError levée si aucun utilisateur n'est trouvé dans la bdd
     */
    public User Read(String aLogin, String aPassHash) throws Application.Database.DaoError, SQLException {
        User user = new User(0);
        Statement stmt = con.createStatement();
        String typeUser = "";
        String reqGetUser = "SELECT * FROM utilisateur WHERE login='" + aLogin + "' AND password='" + aPassHash +"'";
        ResultSet rSet = stmt.executeQuery(reqGetUser);
        try{
            //parcours du rSet qui contient les résultats de la requête
            while (rSet.next()){
                user.setID(rSet.getInt("id"));
                user.setLogin(rSet.getString("LOGIN"));
                user.setPasswordHash(rSet.getString("PASSWORD"));
                typeUser = rSet.getString("TYPE_UTILISATEUR");
            }
            //si aucun user n'a été trouvé on lève une exception
            if (user.getID() == 0){
                throw new DaoError("Aucun utilisateur n'a été trouvé");
            }
            String reqGetInfoUser = "SELECT * FROM utilisateur NATURAL JOIN " + typeUser + " WHERE id='" + user.getID() + "'";
            rSet = stmt.executeQuery(reqGetInfoUser);
            while (rSet.next()){
                user.setFirstName(rSet.getString("nom"));
                user.setLastName(rSet.getString("prenom"));
            }
        }catch(SQLException sqlE){ //exception levé si aucun user trouvé
            sqlE.printStackTrace();
        }
        rSet.close();
        return user;
    }

    /**
     * Fonction qui retourne une instance complète de Tech crée à partir de l'ID d'un technicien 
     * @param idTech valeur entière qui est la clé primaire du technicien
     * @param rSet contient les données de la table Technicien
     * @return une instance complète de Technicien
     */
    private Tech createTechFromId(int idTech, ResultSet rSet) throws SQLException {
        Tech tech = new Tech(idTech);
        tech.setLastName(rSet.getString("nom"));
        tech.setFirstName(rSet.getString("prenom"));
        tech.setIsChief(false);
        return tech;
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
    public ArrayList<Tech> ListTechs() throws SQLException {
        ArrayList<Tech> listTech = new ArrayList<>();
        Statement stmt = con.createStatement();
        String reqShTechs = "SELECT * FROM technicien";
        ResultSet rSet = stmt.executeQuery(reqShTechs);
        //tant que des techniciens sont trouvées
        while (rSet.next()){
            listTech.add( createTechFromId(rSet.getInt("id"),rSet) );
        }
        return listTech;
    }
}