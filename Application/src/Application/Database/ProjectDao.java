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
    private static final String colName = "nom";
    private static final String colEDuration = "dureeEstimee";
    private static final String colFDuration = "dureeFinale";
    private static final String colStatus = "statut";
    private static final String colID = "ID";
    
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
        String reqProjets = "SELECT * FROM projet";
        ResultSet rSet = stmt.executeQuery(reqProjets);
        
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
        
        res.setID(rSet.getInt(colID));
        res.setName(rSet.getString(colName));
        res.setStatus(stringToProjectStatus(rSet.getString(colStatus)));
        res.setFinalDuration(rSet.getInt(colFDuration));
        res.setEstimatedDurationMinutes(rSet.getInt(colEDuration));
        
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
}
