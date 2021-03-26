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
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;


/**
 *
 * @author David
 */
public class sceneTechsController implements Initializable{

    private UserDao dao; //DAO
    private ArrayList<Tech> listTechs; // liste contenant les techniciens récupérés depuis la dao
    
    @FXML
    private VBox containerTech;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrollPaneTech();
        //scrollPaneSkill();
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

    public void scrollPaneTech() {
        try {        
            initData();
            System.out.println("SceneTechs");
            MyStyle style = new MyStyle("ORANGE", "Carlito");
            MyScrollPane scrollTech = new MyScrollPane(style);
            scrollTech.initScrollPaneTech(listTechs);
            this.containerTech.getChildren().add(scrollTech);
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void scrollPaneSkill(Tech tech) {
        try {
            initData();
            System.out.println("SceneSkill");
            MyStyle style = new MyStyle("ORANGE", "Carlito");
            MyScrollPane scrollSkill = new MyScrollPane(style);
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    } 
}
