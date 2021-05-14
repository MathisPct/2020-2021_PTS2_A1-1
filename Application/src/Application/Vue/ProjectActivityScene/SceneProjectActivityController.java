/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.ProjectActivityScene;

import Application.Vue.customBox.MyCustomBoxes.MyCustomBoxActivity;
import Application.Database.ProjectDao;
import Application.Metier.Activity;
import Application.Metier.Project;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBox;
import Application.Vue.customBox.MyScrollPanes.MyScrollPane;
import Application.Vue.customBox.MyScrollPanes.MyScrollPaneActivities;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.main.MainController;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author David
 */
public class SceneProjectActivityController implements Initializable {
    
    private MainController mainController;
    private MyScrollPaneActivities SPactivities;
    private Project project;
    private ArrayList<Activity> listActivity;
    
    @FXML
    private VBox containerActivity;

    public SceneProjectActivityController(MainController mainController, Project projet){
        this.mainController = mainController;
        this.project = projet;
        this.listActivity = new ArrayList<>();
        this.SPactivities = null;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListActivity();
        MyStyle style = new MyStyleOrange("Carlito");
        this.SPactivities = new MyScrollPaneActivities(style, this, project, mainController);
        this.SPactivities.scrollPane();
        this.containerActivity.getChildren().add(this.SPactivities);
    }
    
    
    public void setListActivity() {
        for (Activity activity : project.getActivities()) {
            this.listActivity.add(activity);
            System.out.println("Activité: " + activity.toString() );
        }
    }

    public ArrayList<Activity> getListActivity() {
        return listActivity;
    }
    
    
}
