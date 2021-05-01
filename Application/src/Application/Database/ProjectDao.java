/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Database;


import Application.Metier.Activity;
import Application.Metier.Material;
import Application.Metier.Project;
import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Eileen Lorenzo
 */
public class ProjectDao {
    private static final int COL_NAME = 3;
    private static final int COL_E_DURATION = 4;
    private static final int COL_F_DURATION = 5;
    private static final int COL_STATUS = 6;
    private static final int COL_ID = 1;

    private static final int COL_ASTATUS = 1;
    private static final int COL_TYPE = 2;
    private static final int COL_SUMMARY = 3;
    private static final int COL_DETAILS = 4;
    private static final int COL_STARTDATE = 5;
    private static final int COL_ENDDATE = 6;
    private static final int COL_ADURATION = 7;
    
    private static final int COL_QUANTITY = 1;
    private static final int COL_PRICE = 2;
    private static final int COL_DELIVERY= 3;
    private static final int COL_ORDER = 4;
    private static final int COL_MATNAME = 5;
    private static final int COL_TYPENAME = 6;    
    
    private Connection con;
    
    public ProjectDao()throws ClassNotFoundException, SQLException {
        /*Chargement du driver JDBC pour mysql */
        this.con = DatabaseConnection.getCon();
    }
    
    /**
     * Permet d'extraire les données de la table projet de la BDD sous forme d'une liste de projets.
     * @return la liste de projet
     * @throws SQLException 
     */
    public ArrayList<Project> listAll() throws SQLException {
        ArrayList<Project> res = new ArrayList<>();
        Statement stmt = con.createStatement();
        String qProjets = "SELECT * FROM projet";
        ResultSet rSet = stmt.executeQuery(qProjets);
        
        while (rSet.next()){
            res.add(createProject(rSet));
        }
        
        return res;
    }
    
    /**
     * Création d'un projet en fonction d'un tuple de la table projet de la BDD
     * @param rSet le tuple en question
     * @return un projet dont les attributs sont complétés avec les données de la BDD
     * @throws SQLException 
     */
    private Project createProject(ResultSet rSet) throws SQLException{
        Project project = new Project();
        
        //infos Générales du projet
        project = createProjectOverall(rSet, project);
        
        //activités du projet
        Statement stmt = con.createStatement();
        String qProjets = "SELECT statut, activite_type.nom as \"type\", resume, detail, dateDebut, dateFin, dureePrevue\n"
                            + "FROM compose JOIN activite ON compose.activiteID = activite.ID JOIN activite_type ON activite.IDType = activite_type.ID "
                            + "WHERE projetID = " + project.getID() + " ;";
        ResultSet rSetActivities = stmt.executeQuery(qProjets);
        project = createProjetActivities(rSetActivities, project);
 
        //client
        stmt = con.createStatement();
        qProjets = "SELECT client.nom, client.prenom\n" 
                    + "FROM client INNER JOIN devis ON client.UTILISATEUR_ID = devis.clientID INNER JOIN projet ON devis.projetID = projet.ID "
                    + "where devis.projetID = " + project.getID() + " ;";
        ResultSet rSetClient = stmt.executeQuery(qProjets);
        project = createProjetClient(rSetClient, project);
        
        //Materiel
        stmt = con.createStatement();
        qProjets = "SELECT necessite.quantite, necessite.prixAchat, necessite.dateLivraison, necessite.dateCommande, materiel.nom, materiel_type.nom\n"
                + "FROM necessite JOIN materiel ON necessite.materielID = materiel.ID JOIN materiel_type ON materiel.IDType = materiel_type.ID "
                + "WHERE necessite.projetID = "+ project.getID() +" ;";
        ResultSet rSetMaterial = stmt.executeQuery(qProjets);
        project = createProjetMaterial(rSetMaterial, project);
        
        
        return project;
    }
    
    private Project createProjectOverall(ResultSet rSet, Project p) throws SQLException {
        p.setID(rSet.getInt(COL_ID));
        p.setName(rSet.getString(COL_NAME));
        p.setStatus(Converter.stringToProjectStatus(rSet.getString(COL_STATUS)));
        p.setFinalDuration(rSet.getInt(COL_F_DURATION));
        p.setEstimatedDurationMinutes(rSet.getInt(COL_E_DURATION));
        return p;
    }

    private Project createProjetActivities(ResultSet rSet, Project p) throws SQLException {
        while (rSet.next()){
            p.addActivity(new Activity(
            Converter.stringToActivityStatus(rSet.getString(COL_ASTATUS)), Converter.stringToActivityType(rSet.getString(COL_TYPE)),
            rSet.getDate(COL_STARTDATE), rSet.getDate(COL_ENDDATE), rSet.getString(COL_SUMMARY), rSet.getString(COL_DETAILS), rSet.getInt(COL_ADURATION)));
        }    
        
        return p;
    }

    private Project createProjetClient(ResultSet rSet, Project project) throws SQLException {
        while (rSet.next()){
            project.setClient(rSet.getString(1));
        }    
        return project;
    }    
    
    private Project createProjetMaterial(ResultSet rSet, Project project) throws SQLException {
        while(rSet.next()){
            project.addMaterial(new Material(
                    rSet.getString(COL_MATNAME),
                    Converter.StringToMaterialType(rSet.getString(COL_TYPENAME)),
                    rSet.getInt(COL_QUANTITY),
                    rSet.getDate(COL_ORDER),
                    rSet.getDate(COL_DELIVERY),
                    rSet.getFloat(COL_PRICE)));
        }
        return project;
    }    
    
    

    
    /**
     * Met à jour le projet donné dans la base de données avec les données du programme
     * @param p le projet à mettre à jour
     */
    public void update(Project p) throws SQLException {
        Statement stmt = con.createStatement();
        String qUpdate = "UPDATE projet SET"
                + " nom = '"+p.getName()+"',"
                + " dureeEstimee = "+p.getEstimatedDurationMinutes()+","
                + " dureeFinale = "+p.getFinalDuration()+","
                + " statut = '"+Converter.projectStatusToString(p.getStatus())+"' "
                + " WHERE id = "+p.getID()+";";
        stmt.executeUpdate(qUpdate);
        
    }
}
