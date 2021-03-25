/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.techsScene;

import Application.Database.UserDao;
import Application.Metier.Tech;
import Application.Vue.customBox.MyScrollPane;
import Application.Vue.customBox.MyStyle;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 *
 * @author David
 */
public class sceneTechsController implements Initializable{

    private UserDao dao; //DAO
    private ArrayList<Tech> listTechs; // liste contenant les techniciens récupérés depuis la dao
    @FXML
    private VBox scrollTechParent;

    @FXML
    private HBox titleBoxScrollTech;

    @FXML
    private JFXButton addTechButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("SceneTechs");
        MyStyle style = new MyStyle("ORANGE", "Carlito");
        MyScrollPane scrollTech = new MyScrollPane(style);
        try {
            scrollTechParent.getChildren().clear();
            scrollTechParent.getChildren().add(titleBoxScrollTech);
            initData();
            scrollTech.initScrollPaneTech(listTechs);
            scrollTechParent.getChildren().add(scrollTech);
            scrollTechParent.getChildren().add(addTechButton);
            VBox.setMargin(addTechButton, new Insets(15,0,0,0)); //Top, Right, bottom, left
            
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
    }

    
}
