/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.ProjectActivityScene;

import Application.Database.ProjectDao;
import Application.Metier.Activity;
import Application.Metier.Project;
import Application.Vue.customBox.MyCustomBox;
import Application.Vue.customBox.MyScrollPane;
import Application.Vue.customBox.MyStyle;
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
    private MyScrollPane scrollActivity;
    private MyStyle style;
    private Project projet;
    private ArrayList<Activity> activityList;
    
    @FXML
    private VBox containerActivity;

    public SceneProjectActivityController(MainController mainController, Project projet){
        this.mainController = mainController;
        this.projet = projet;
        this.activityList = new ArrayList<>();
        this.style = new MyStyle("ORANGE", "Carlito");
        this.scrollActivity = new MyScrollPane(style, mainController);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListActivity();
        SPActivity();
    }
    
    public void SPActivity() {
        initSPActivity();
        this.containerActivity.getChildren().add(scrollActivity);
    }
    
    public void initSPActivity() {        
        VBox vboxLayout = new VBox(scrollActivity.getScrollPaneStyle().getBoxSpacing());
        for (int i = 0; i < activityList.size(); i++) {         
            MyCustomBox boxProjectActivity = new MyCustomBox(scrollActivity, activityList.get(i), style);                  
            boxProjectActivity.addRowBoxListItem(scrollActivity.generateMainActivityRow(activityList.get(i))); //ajout de la bôite de titre        
            boxProjectActivity.initBox();//initialisation de la boite principale
            scrollActivity.getCustomBoxList().add(boxProjectActivity);//remplissage de la liste de customBox avec l'instance actuellement générée      
            MyCustomBox.setVgrow(boxProjectActivity, Priority.ALWAYS);
            vboxLayout.getChildren().add(boxProjectActivity);
        }      
        scrollActivity.setContent(vboxLayout);
        scrollActivity.setFitToWidth(true);
    }   
    
    public void setListActivity() {
        for (Activity activity : projet.getActivities()) {
            this.activityList.add(activity);
        }
    }
}
