/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Metier;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe qui gère la connexion avec la BDD
 * @author math7
 */
public class PersonDAO {
    private Connection con;
    
    public PersonDAO()throws ClassNotFoundException, SQLException {
        try{
            Class.forName( "com.mysql.jdbc.Driver" );
            String url = "jdbc:mysql://127.0.0.1:3306/Directory";
            String login = "root";
            String password = "";
            this.con = (Connection) DriverManager.getConnection(url, login, password);
        }catch (Exception e){
            System.err.println(e);
        }
    }
    
    /**
     * Fonction qui fournit la liste des personnes présentes dans la BDD
     * @return 
     * @throws SQLException
     */
    public ArrayList<Person> listAll() throws SQLException, Exception{
        ArrayList<Person> listRes = new ArrayList<>();
        Statement stmt = con.createStatement();
        String reqGetData = "SELECT * FROM person";
        //execution de la requete
        ResultSet rSet = stmt.executeQuery(reqGetData);
        //parcours de rSet
        while (rSet.next() ){
            Person p = crPersID(rSet.getInt("ID"), rSet);
            listRes.add(p);
        }
        return listRes;
    }
    
    /**
     * Fonction qui crée une personne à partir des données de la table
     * @param id valeur entière qui est la clé primaire de la person
     * @param rSet valeur ResultSet qui contient les données de la table person
     * @return 
     */
    private Person crPersID(int id,ResultSet rSet) throws SQLException,Exception{
        Person p = new Person(id);
        p.setLastName(rSet.getString("LASTNAME"));
        p.setFirstName(rSet.getString("FIRSTNAME"));
        p.setPhone(rSet.getString("PHONE"));
        p.setEmail(rSet.getString("EMAIL"));
        p.setAdresse(rSet.getString("ADDRESS"));
        if ( rSet.getString("GENDER").equals("M") ){
            p.setGender(Gender.M);
        }
        else if ( rSet.getString("GENDER").equals("F") ){
            p.setGender(Gender.F);
        }
        return p;
    }
}
