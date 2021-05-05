/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.ProjectActivities;

import Application.Vue.main.MainController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author David
 */
public class SceneProjectActivitiesController implements Initializable{
    
    private MainController mainController;
    
    public SceneProjectActivitiesController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("[ACTIVITY SCENE OPENED]");
    }
    
}
