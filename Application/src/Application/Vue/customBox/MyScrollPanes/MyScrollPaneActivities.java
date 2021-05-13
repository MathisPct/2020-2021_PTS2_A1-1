/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyScrollPanes;

import Application.Metier.Activity;
import Application.Metier.Project;
import Application.Vue.ProjectActivityScene.SceneProjectActivityController;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBox;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBoxActivity;
import Application.Vue.customBox.MyItemBoxes.ItemHBox;
import Application.Vue.customBox.MyItemBoxes.ItemVBox;
import Application.Vue.customBox.MyRowBox;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.main.MainController;
import java.util.ArrayList;
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
        initScrollPane();
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
            box.addRowBoxListItem(generateItemRow()); //ajout de la boite d'item
            box.initBox();//initialisation de la boite principale
            this.getCustomBoxList().add(box);//remplissage de la liste de customBox avec l'instance actuellement générée      
            MyCustomBox.setVgrow(box, Priority.ALWAYS);
            vboxLayout.getChildren().add(box);
        }      
        this.setContent(vboxLayout);
        this.setFitToWidth(true);
    }
    
    public MyRowBox generateItemRow() {
        // création du container d'Items
        MyRowBox itemTechRow = new MyRowBox("", this.getScrollPaneStyle());
        itemTechRow.generateItemVBoxRow(createItemList(), Priority.NEVER); //ajout des items à la boite d'item
        return itemTechRow;
    }
    
    
    public ArrayList<ItemVBox> createItemList() {
        // Génération des Item de la boite d'activité
        String value = "<value>";
        ArrayList<ItemVBox> itemBoxList = new ArrayList();         
        ItemVBox type = new ItemVBox("Type", value, this.getScrollPaneStyle());
        ItemVBox nom = new ItemVBox("Nom", value , this.getScrollPaneStyle());
        ItemVBox debut = new ItemVBox("Début", value , this.getScrollPaneStyle());
        ItemVBox fin = new ItemVBox("Fin", value , this.getScrollPaneStyle());
        ItemVBox dureePrevue = new ItemVBox("Durée prévue", value , this.getScrollPaneStyle());
        ItemVBox statut = new ItemVBox("Statut", value , this.getScrollPaneStyle());
        itemBoxList.add(statut);
        itemBoxList.add(type);
        itemBoxList.add(nom);
        itemBoxList.add(debut);
        itemBoxList.add(fin);
        itemBoxList.add(dureePrevue);
        return itemBoxList;
    }
    
    public MyRowBox generateMainRow() { 
        // Génération de la ligne du nom 
        String name = "<name>";
        MyRowBox mainTechRow = new MyRowBox(name, this.getScrollPaneStyle());           
        mainTechRow.generateLineBoxRow();
        return mainTechRow;
    }

}
