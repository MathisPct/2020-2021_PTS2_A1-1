/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.projectsScene;

import java.net.URL;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.scene.image.ImageView;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

/**
 *
 * @author math7
 */
public class SceneProjectsController implements Initializable {
    
    @FXML
    private Label label;
    
     @FXML
    private ImageView appClose;

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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("SceneProjects");
    }    
    
    @FXML
    public void loadSceneCustomers(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Application/Vue/customersScene/sceneCustomers.fxml"));
        Scene scene = customers.getScene();
    }
    
    @FXML
    public void loadSceneLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Application/Vue/customersScene/sceneLogin.fxml"));
        Scene scene = deconnectionButton.getScene();
    }
    
}
