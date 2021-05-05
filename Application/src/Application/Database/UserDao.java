package Application.Database;

import Application.Metier.Skill;
import com.mysql.jdbc.Connection;
import Application.Metier.Tech;
import Application.Metier.User;
import Application.Vue.UtilsIHM;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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
                throw new DaoError("Mauvais utilisateur ou mauvais mot de passe entré");
            }
            String reqGetInfoUser = "SELECT * FROM utilisateur NATURAL JOIN " + typeUser + " WHERE id='" + user.getID() + "'";
            rSet = stmt.executeQuery(reqGetInfoUser);
            while (rSet.next()){
                user.setFirstName(rSet.getString("nom"));
                user.setLastName(rSet.getString("prenom"));
                user.setTypeUser(rSet.getString("TYPE_UTILISATEUR"));
            }
        }catch(SQLException sqlE){ //exception levé si aucun user trouvé
            sqlE.printStackTrace();
        }
        rSet.close();
        return user;
    }

    /**
     * Met à jour l'utilisateur dans la BD
     * @param aUser L'utilisateur à mettre à jour
     * @author Lucas Moniot
     * @throws DaoError levée lorsqu'une erreur SQL est levée ou que le nouveau 
     * login de aUser est déjà existant dans la base de donnée
     */
    public void Update(User aUser) throws DaoError{
        
        Statement stmt = null;
        Connection conn = null;
        
        try{
            stmt = con.createStatement();
            //On update le login, le mdp, le nom et le prénom
            String reqUpdateUser = "UPDATE utilisateur SET login='"+ aUser.getLogin() +"', password='"+ aUser.getPasswordHash() + "' WHERE id ="+ aUser.getID();         
            String reqUpdateTypeUSer = "UPDATE " + aUser.getTypeUser() + " SET nom='"+ aUser.getLastName()+"', prenom='"+ aUser.getFirstName() + "' WHERE id ="+ aUser.getID();
            stmt.executeUpdate(reqUpdateUser);
            stmt.executeUpdate(reqUpdateTypeUSer);
        }
        catch(MySQLIntegrityConstraintViolationException ex){
            ex.printStackTrace();
            throw new DaoError("Le login est déjà existant dans la base de données", ex);
        }
        catch(SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
            throw new DaoError("Erreur SQL " + se.getLocalizedMessage(), se);
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
               if(conn!=null)
                  con.close();
            }catch(SQLException se){
               se.printStackTrace();
            }//end finally try
         }//end try
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
        tech.setCoutHoraire(rSet.getFloat("coutHoraire"));
        tech.setGrade(rSet.getString("grade"));
        createTechSkill(tech);
        return tech;
    }

    /**
     * Methode qui crée une instance de Skill suivant les données de la BDD
     * @param rSet résultat qui permet d'accéder au nom du skill et le leve
     * @return
     * @throws SQLException
     */
    private Skill createSkill(ResultSet rSet) throws SQLException {
        Skill skill = new Skill();
        skill.setName(rSet.getString("nom"));
        skill.setLevel(rSet.getString("niveau"));
        return skill;
    }
   
    /**
     * Remplie la liste des compétences d'un technicien passé en paramètre
     * @param tech technicien dont la liste des compétences va être remplie
     * @throws SQLException 
     */
    private void createTechSkill(Tech tech) throws SQLException{
        Statement stmt = con.createStatement();
        String reqGetSkillTech = "SELECT * FROM possede INNER JOIN competence on possede.competenceID = competence.ID WHERE technicienID = " + tech.getID();
        ResultSet rSet = stmt.executeQuery(reqGetSkillTech);
        while (rSet.next()){
            Skill skill = createSkill(rSet);
            tech.AddSkill(skill);
        }
    }
    
    /**
     * Liste les techniciens
     * @return un ensemble d'utilisateurs qui sont des techniciens
     */
    public ArrayList<Tech> ListTechs(String skill) throws SQLException, DaoError {
        ArrayList<Tech> listTech = new ArrayList<>();
        Statement stmt = con.createStatement();
        String reqShTechs = "SELECT * FROM technicien";
        if (!"".equals(skill)){
            reqShTechs = "SELECT * FROM technicien WHERE technicien.id IN (SELECT technicienID FROM possede INNER JOIN competence on possede.competenceID = competence.ID WHERE competence.nom ='" + skill + "');";
        }
        ResultSet rSet = stmt.executeQuery(reqShTechs);
        //tant que des techniciens sont trouvées
        while (rSet.next()){
            listTech.add( createTechFromId(rSet.getInt("id"),rSet) );
        }
        //si aucun techniciens n'a été trouvé
        if (listTech.size() == 0){
            throw new DaoError("Aucuns techniciens trouvé");
        }
        return listTech;
    }
    
    /**
     * Liste les compétences que peuvent avoir les techniciens
     * @return un ensemble de compétences de techniciens
     */
    public ArrayList<String> ListSkills() throws SQLException, DaoError{
        ArrayList<String> competences = new ArrayList<>();
        Statement stmt = con.createStatement();
        String reqLsComp = "SELECT DISTINCT nom FROM competence;";
        ResultSet rSet = stmt.executeQuery(reqLsComp);
        //on parcours le tableau de résultat
        while (rSet.next()){
            competences.add(rSet.getString("nom")); //on récupère
        }
        if(competences.size() == 0){
            throw new DaoError("Aucunes compétences trouvées");
        }
        return competences;
    }
}