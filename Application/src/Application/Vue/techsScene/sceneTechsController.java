/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.techsScene;

import Application.Database.UserDao;
import Application.Metier.Tech;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

/**
 *
 * @author David
 */
public class sceneTechsController implements Initializable{
    @FXML
    private ListView<Tech> listViewTechs;

    private UserDao dao; //DAO
    private ArrayList<Tech> listTechs; // liste contenant les techniciens récupérés depuis la dao

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("SceneTechs");
        try {
            viewTech();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialise la liste des données des techniciens
     * @throws SQLException
     * @throws ClassNotFoundException
     * @autor Mathis Poncet
     */
    private void initData() throws SQLException, ClassNotFoundException {
        this.dao = new UserDao();
        this.listTechs = new ArrayList<>();
        try {
            this.listTechs.addAll(this.dao.ListTechs());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Procédure qui initialise la listView pour afficher les techniciens
     * @throws SQLException
     * @throws ClassNotFoundException
     * @autor David Golay & Mathis Poncet
     */
    public void viewTech() throws SQLException, ClassNotFoundException {
        initData();
        listViewTechs.getItems().addAll(listTechs);
    }

    
}
