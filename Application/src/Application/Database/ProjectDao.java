/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Database;

import Application.Metier.Activity;
import Application.Metier.ActivityStatus;
import Application.Metier.ActivityType;
import Application.Metier.Project;
import Application.Metier.ProjectStatus;
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
    private static final String COL_NAME = "nom";
    private static final String COL_E_DURATION = "dureeEstimee";
    private static final String COL_F_DURATION = "dureeFinale";
    private static final String COL_STATUS = "statut";
    private static final String COL_ID = "id";
    
    private static final int COL_ASTATUS = 1;
    private static final int COL_TYPE = 2;
    private static final int COL_SUMMARY = 3;
    private static final int COL_DETAILS = 4;
    private static final int COL_STARTDATE = 5;
    private static final int COL_ENDDATE = 6;
    
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
        String qProjets = "SELECT statut, activite_type.nom as \"type\", resume, detail, dateDebut, dateFin\n"
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
        
        return project;
    }
    
    private Project createProjectOverall(ResultSet rSet, Project p) throws SQLException {
        p.setID(rSet.getInt(COL_ID));
        p.setName(rSet.getString(COL_NAME));
        p.setStatus(stringToProjectStatus(rSet.getString(COL_STATUS)));
        p.setFinalDuration(rSet.getInt(COL_F_DURATION));
        p.setEstimatedDurationMinutes(rSet.getInt(COL_E_DURATION));
        p.setClient(COL_ID);
        return p;
    }

    private Project createProjetActivities(ResultSet rSet, Project p) throws SQLException {
        while (rSet.next()){
            p.addActivity(new Activity(
            stringToActivityStatus(rSet.getString(COL_ASTATUS)), stringToActivityType(rSet.getString(COL_TYPE)),
            rSet.getDate(COL_STARTDATE), rSet.getDate(COL_ENDDATE), rSet.getString(COL_SUMMARY), rSet.getString(COL_DETAILS)));
        }    
        
        return p;
    }

    private Project createProjetClient(ResultSet rSet, Project project) throws SQLException {
        while (rSet.next()){
            project.setClient(rSet.getString(1));
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
                + " statut = '"+projectStatusToString(p.getStatus())+"' "
                + " WHERE id = "+p.getID()+";";
        stmt.executeUpdate(qUpdate);
        
    }
    
    /**
     * Permet de permuter une String en ProjectStatus
     * @param s la string à permuter
     * @return l'équivalent de type ProjectStatus
     */
    private static ProjectStatus stringToProjectStatus(String s){
        ProjectStatus res;
        switch(s){
            case "fini" : res = ProjectStatus.ENDED;
                          break;
                          
            case "en attente" : res = ProjectStatus.WAITING;
                          break;
             
            case "en cours" : res = ProjectStatus.WORKING;
                          break;
                          
            case "annule" : res = ProjectStatus.CANCELED;
                          break;
                          
            default : res = null;                
        }
        
        return res;
    }    
    
        /**
     * Permet de permuter un ProjectStatus en String 
     * @param ps le ProjectStatus à permuter
     * @return l'équivalent de type string
     */
    public static String projectStatusToString(ProjectStatus ps){
        String res;
        switch(ps){
            case ENDED : res = "fini";
                          break;
                          
            case WAITING : res = "en attente";
                          break;
             
            case WORKING : res = "en cours";
                          break;
                          
            case CANCELED : res = "annule";
                          break;
                          
            default : res = "";                
        }
        
        return res;
    }
    
    private static ActivityStatus stringToActivityStatus(String s){
        ActivityStatus res;
        switch(s){
            case "prévue" : res = ActivityStatus.PLANNED;
                          break;
                          
            case "terminée" : res = ActivityStatus.ENDED;
                          break;
             
            case "en cours" : res = ActivityStatus.WORKING;
                          break;
                          
            case "annulée" : res = ActivityStatus.CANCELED;
                          break;
                          
            default : res = null;                
        }
        
        return res;
    }
    
        private static ActivityType stringToActivityType (String s){
        ActivityType res;
        switch(s){
            case "déploiement" : res = ActivityType.IMPLEMENTATION;
                          break;
                          
            case "développement" : res = ActivityType.DEVELOPMENT;
                          break;
             
            case "maintenance" : res = ActivityType.MAINTENANCE;
                          break;
                          
            case "migration" : res = ActivityType.MIGRATION;
                          break;
                          
            case "service après vente" : res = ActivityType.AFTER_SALE;
                          break;
                          
            case "solution innovante" : res = ActivityType.INNOVATION;
                          break;                          
                          
            default : res = null;                
        }
        
        return res;
    }    
    
}
