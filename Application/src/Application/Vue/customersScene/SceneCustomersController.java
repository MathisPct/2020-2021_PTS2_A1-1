/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customersScene;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import Application.Metier.Prototype.Person;
import Application.Metier.Prototype.PersonDAO;

/**
 * FXML Controller class
 *
 * @author math7
 */
public class SceneCustomersController implements Initializable {
    private ArrayList<Person> listPerson;
    private PersonDAO dao;

    @FXML
    private ListView<Person> customersListView;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Scene customers");
        this.listPerson = new ArrayList<>();
        try{
            initData();
        }catch (ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
        System.out.println(listPerson);
        initIHM();
    }

    /**
     * Procédure qui permet d'initialiser l'interface de la scene
     */
    private void initIHM(){
        this.customersListView.getItems().addAll(this.listPerson);
    }

    /**
     * Procédure qui permet de se connecter à la bdd et récupérer les données de la table et les ajouter à listPerson
     * @throws SQLException exception levé si erreur sql
     * @throws ClassNotFoundException
     */
    private void initData() throws SQLException, ClassNotFoundException{
        this.dao = new PersonDAO();
        try{
            this.listPerson.addAll(this.dao.listAll());
        }catch (Exception e){
            System.err.println(e);
        }
    }
}
