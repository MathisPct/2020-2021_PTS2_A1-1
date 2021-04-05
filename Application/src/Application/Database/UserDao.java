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
     * Lit un utilisateur depuis la BD. En cas d'erreur, lève une exception.
     * @param aLogin login de l'utilisateur cherché
     * @param aPassHash valeur de hachage du mot de passe de l'utilisateur cherché
     * @return L'utilisateur chargé depuis la BD.
     */
    public User Read(String aLogin, String aPassHash) throws Application.Database.DaoError, SQLException {
        User user = null;
        Statement stmt = con.createStatement();
        String reqGetUser = "SELECT * FROM utilisateur WHERE login='" + aLogin + "' AND password='" + aPassHash +"'";
        ResultSet rSet = stmt.executeQuery(reqGetUser);
        try{
            //parcours du rSet qui contient les résultats de la requête
            while (rSet.next()){
                user = new User(rSet.getInt("id"));
            }
        }catch(SQLException sqlE){ //exception levé si aucun user trouvé
            sqlE.printStackTrace();
            System.out.println("Aucun user trouvé");
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
     * @author Lucas Moniot
     */
    public void Update(User aUser) throws SQLException {
        
        Statement stmt = null;
        Connection conn = null;
        
        try{
            stmt = con.createStatement();
            //On update le login, le mdp, le nom et le prénom
            String reqUpdateUser = "UPDATE utilisateur SET login='"+ aUser.getLogin() +"', passwordHash='"+ aUser.getPasswordHash()+"',lastName='"+ aUser.getLastName()+"', firstName='"+aUser.getFirstName() +"' WHERE id =' "+ aUser.getID()+"'";         
            stmt.executeUpdate(reqUpdateUser);
    
        }
        catch(SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
               if(stmt !=null)
                  conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
               if(conn!=null)
                  conn.close();
            }catch(SQLException se){
               se.printStackTrace();
            }//end finally try
         }//end try
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