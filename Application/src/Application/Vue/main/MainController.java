/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author math7
 */
public class MainController implements Initializable {
    
    @FXML
    private JFXButton projectsWindow;

    @FXML
    private JFXButton customers;

    @FXML
    private JFXButton techsWindow;

    @FXML
    private JFXButton techWindow;

    @FXML
    private JFXButton profileWindow;

    @FXML
    private JFXButton deconnectionButton;
    
    @FXML 
    private BorderPane container;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadSceneLogin();
            //loadSceneProjects();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void loadSceneCustomers() throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Application/Vue/customersScene/sceneCustomers.fxml"));
            Pane tempPane = fxmlLoader.load();
            container.setCenter(tempPane);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void loadSceneProjects() throws IOException{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Application/Vue/projectsScene/sceneProjects.fxml"));
            Pane tempPane = fxmlLoader.load();
            container.setCenter(tempPane);
        } catch (IOException e){
            e.printStackTrace();
        }
        /*
        Parent root = FXMLLoader.load(getClass().getResource("/prototype/Vue/projectsScene/sceneProjects.fxml"));
        Scene scene = new Scene(root);
        Prototype.switchScene(scene);
        */
    }
    
    @FXML
    public void loadSceneLogin() throws IOException{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Application/Vue/Login/sceneLogin.fxml"));
            Pane tempPane = fxmlLoader.load();
            container.setCenter(tempPane);
        } catch (IOException e){
            e.printStackTrace();
        }
        /*
        Parent root = FXMLLoader.load(getClass().getResource("/prototype/Vue/projectsScene/sceneProjects.fxml"));
        Scene scene = new Scene(root);
        Prototype.switchScene(scene);
        */
    }
    
    @FXML
    public void loadSceneTechs() throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Application/Vue/techsScene/sceneTechs.fxml"));
            Pane tempPane = fxmlLoader.load();
            container.setCenter(tempPane);
        } catch (IOException e){
            e.printStackTrace();         
        }
    }
}
