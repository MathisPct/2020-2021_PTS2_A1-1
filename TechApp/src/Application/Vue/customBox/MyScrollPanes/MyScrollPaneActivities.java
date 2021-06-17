/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyScrollPanes;

import Application.Metier.Activity;
import Application.Metier.Project;
import Application.Vue.ProjectActivityScene.SceneBoxActivityController;
import Application.Vue.ProjectActivityScene.SceneProjectActivityController;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBox;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBoxActivity;
import Application.Vue.customBox.MyItemBoxes.ItemHBox;
import Application.Vue.customBox.MyItemBoxes.ItemVBox;
import Application.Vue.customBox.MyPanes.MyPane;
import Application.Vue.customBox.MyPanes.MyPaneDuoVBox;
import Application.Vue.customBox.MyRowBox;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.main.MainController;
import Application.Vue.projectsGraphs.projectsGraphActivity.SceneGraphActivityController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author David
 */
public class MyScrollPaneActivities extends MyScrollPane {
    
    private SceneProjectActivityController parentController;
    private Project projet;
    private ArrayList<Activity> listActivities;

    public MyScrollPaneActivities(MyStyle style, SceneProjectActivityController parentController, Project projet, MainController mainController) {
        super(style, mainController);
        this.projet = projet;
        this.listActivities = new ArrayList<>();
        this.parentController = parentController;
    }
    
    public void scrollPane() {
        System.out.println("Scene Activity");
        MyStyle style = new MyStyleOrange("Carlito");
        try {
            //initScrollPane();
            loadBox();
        } catch (IOException ex) {
            Logger.getLogger(MyScrollPaneActivities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     /**
     * Cette fonction génère les box de techniciens 
     * dans le scrollPane des techniciens à partir 
     * d'une liste de technicien
     */
    public void initScrollPane() {    
        VBox vboxLayout = new VBox(this.getScrollPaneStyle().getBoxSpacing());
        
        for (Activity activity : projet.getActivities()) {         
            MyCustomBox box = new MyCustomBoxActivity(this.getScrollPaneStyle());                         
            //box.addRowBoxListItem(generateMainRow()); //ajout de la bôite de titre
            box.addRowBoxListItem(generateItemRow(activity)); //ajout de la boite d'item
            box.initBox();//initialisation de la boite principale
            this.getCustomBoxList().add(box);//remplissage de la liste de customBox avec l'instance actuellement générée      
            MyCustomBox.setVgrow(box, Priority.ALWAYS);
            vboxLayout.getChildren().add(box);
        }      
        this.setContent(vboxLayout);
        this.setFitToWidth(true);
    }
    
    public MyRowBox generateItemRow(Activity activity) {
        // création du container d'Items
        MyRowBox itemTechRow = new MyRowBox("", this.getScrollPaneStyle());
        itemTechRow.generateItemVBoxRow(createItemList(activity), Priority.NEVER); //ajout des items à la boite d'item
        return itemTechRow;
    }
    
    
    public ArrayList<MyPane> createItemList(Activity activity) {
        // Génération des Item de la boite d'activité
        String value = "<value>";
        String typeA = activity.getType();
        String statutA = activity.getType();
        String dureeA = String.valueOf(activity.getDuration());
        String finA = String.valueOf(activity.getDuration());
        //String debutA = activity.getStartDate().toString();
        ArrayList<MyPane> itemBoxList = new ArrayList();         
        MyPane type = new MyPaneDuoVBox(this.getScrollPaneStyle(), "Type", typeA);
        MyPane nom = new MyPaneDuoVBox(this.getScrollPaneStyle(), value, value);
        //MyPane debut = new MyPaneDuoVBox(this.getScrollPaneStyle(), "Début", debutA);
        MyPane fin = new MyPaneDuoVBox(this.getScrollPaneStyle(), value, value);
        //MyPane dureePrevue = new MyPaneDuoVBox(this.getScrollPaneStyle(), "Durée prévue", dureeA);
        MyPane statut = new MyPaneDuoVBox(this.getScrollPaneStyle(), "Statut", statutA);
        itemBoxList.add(statut);
        itemBoxList.add(type);
        itemBoxList.add(nom);
        //itemBoxList.add(debut);
        itemBoxList.add(fin);
        //itemBoxList.add(dureePrevue);
        return itemBoxList;
    }
    
    public MyRowBox generateMainRow() { 
        // Génération de la ligne du nom 
        String name = "<name>";
        MyRowBox mainTechRow = new MyRowBox(name, this.getScrollPaneStyle());           
        mainTechRow.generateLineBoxRow();
        return mainTechRow;
    }
    
    private void loadBox() throws IOException{
        VBox vboxLayout = new VBox(this.getScrollPaneStyle().getBoxSpacing()+10);       
        for (Activity activity : projet.getActivities()) {         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/Vue/ProjectActivityScene/BoxActivity.fxml"));
            SceneBoxActivityController controller = new SceneBoxActivityController(activity);
            loader.setController(controller);
            
            AnchorPane box = loader.load();
            VBox.setVgrow(box, Priority.ALWAYS);
            System.out.println("add child");
            vboxLayout.getChildren().add(box); 
            
        }
        this.setContent(vboxLayout);
        this.setFitToWidth(true);
    }

}
