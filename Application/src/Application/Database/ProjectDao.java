/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Database;

import Application.Metier.Project;
import Application.Metier.ProjectStatus;
import Application.Metier.Tech;
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
        Project res = new Project();
        
        res.setID(rSet.getInt(COL_ID));
        res.setName(rSet.getString(COL_NAME));
        res.setStatus(stringToProjectStatus(rSet.getString(COL_STATUS)));
        res.setFinalDuration(rSet.getInt(COL_F_DURATION));
        res.setEstimatedDurationMinutes(rSet.getInt(COL_E_DURATION));
        
        return res;
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
     * Permet de permuter un ProjectStatus en String 
     * @param ps le ProjectStatus à permuter
     * @return l'équivalent de type string
     */
    private static String projectStatusToString(ProjectStatus ps){
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
}
