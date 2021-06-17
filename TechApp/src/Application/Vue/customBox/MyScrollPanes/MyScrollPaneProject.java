/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyScrollPanes;

import Application.Metier.Project;
import Application.Vue.customBox.MyButtons.MyButton;
import Application.Vue.customBox.MyButtons.MyButtonProject;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBox;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBoxProject;
import Application.Vue.customBox.MyPanes.MyPane;
import Application.Vue.customBox.MyPanes.MyPaneDuoVBox;
import Application.Vue.customBox.MyRowBox;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.main.MainController;
import Application.Vue.projectsScene.SceneProjectsController;
import java.util.ArrayList;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author David
 */
public class MyScrollPaneProject extends MyScrollPane{
    
    //private Project project;
    private Project project;
    private SceneProjectsController parentController;
    
    public MyScrollPaneProject(MyStyle style, SceneProjectsController parentController, MainController mainController) {
        super(style, mainController);
        this.parentController = parentController;
    }
    
    public MyScrollPane scrollPaneProject() {
        MyStyle style = new MyStyleOrange("Carlito");
        initScrollPaneProject();    
        return this;
    }
    
    public void initScrollPaneProject() {        
        VBox vboxLayout = new VBox(this.getScrollPaneStyle().getBoxSpacing());
        for (int i = 0; i < parentController.getListProject().size(); i++) {         
            MyCustomBox boxProject = new MyCustomBoxProject(parentController.getListProject().get(i), this.getScrollPaneStyle());       
            parentController.setActionBoxProject(boxProject, parentController.getListProject().get(i));//Evenement boite principale          
            boxProject.addRowBoxListItem(this.generateMainRow(parentController.getListProject().get(i))); //ajout de la bôite de titre
            boxProject.addRowBoxListItem(this.generateItemBox(parentController.getListProject().get(i))); //ajout de la boite d'item
            boxProject.initBox();//initialisation de la boite principale
            if(parentController.getProjetActif() != null 
            && parentController.getProjetActif().equals(parentController.getListProject().get(i))){
                boxProject.openBox();
            }
            this.getCustomBoxList().add(boxProject);//remplissage de la liste de customBox avec l'instance actuellement générée      
            MyCustomBox.setVgrow(boxProject, Priority.ALWAYS);
            vboxLayout.getChildren().add(boxProject);
        }      
        this.setContent(vboxLayout);
        this.setFitToWidth(true);
    }
    
    public MyRowBox generateMainRow(Project p) { 
        // Génération de la ligne du nom du technicien
        String pName = p.getName();
        MyRowBox mainTechRow = new MyRowBox(pName, getScrollPaneStyle()); 
        String iconName = "WAITING";
        switch(p.getStatus()){
            case ENDED: iconName = "OK"; break;
            case CANCELED: iconName = "CANCELED"; break;
            case WAITING: iconName = "WAITING"; break;
            case WORKING: iconName = "WORKING"; break;
        }
        mainTechRow.generateLineIconBoxRow(iconName);
        return mainTechRow;
    }
    
    public MyRowBox generateItemBox(Project p) {
        // création du container d'Items
        MyRowBox projetItems = new MyRowBox("", getScrollPaneStyle());
        projetItems.generateItemVBoxRow(this.createItemList(p), Priority.ALWAYS); //ajout des items à la boite d'item
        //Génération d'un Item Bouton ACTIVITES
        projetItems.addButtonToRowBox(this.createBtnActivities(p));
        //Génération d'un Item Bouton MATERIEL
        projetItems.addButtonToRowBox(this.createBtnMateriel(p));
        return projetItems;
    }
        
    public ArrayList<MyPane> createItemList(Project p) {
        // Génération des Item de la boite
        String client = "<Client>";
        String statut = p.getStatusString();
        String dateCommande = "00/00/0000";
        String totalActivite = String.valueOf(p.getActivities().size());
        ArrayList<MyPane> itemBoxList = new ArrayList();
        MyPane i1 = new MyPaneDuoVBox(getScrollPaneStyle(), "Statut", statut);
        MyPane i2 = new MyPaneDuoVBox(getScrollPaneStyle(), "Client", client);
        MyPane i3 = new MyPaneDuoVBox(getScrollPaneStyle(),"Commande", dateCommande);
        MyPane i4 = new MyPaneDuoVBox(getScrollPaneStyle(), "Total Activités", totalActivite);
        itemBoxList.add(i1);
        itemBoxList.add(i2);
        itemBoxList.add(i3);
        itemBoxList.add(i4);
        return itemBoxList;
    }      
    
    public MyButton createBtnActivities(Project p) {
        //Génération d'un Item Bouton ACTIVITES
        MyButtonProject btnActivities = new MyButtonProject("Activités", "ACTIVITY", p, getMainController(), getScrollPaneStyle());
        btnActivities.addIconButton("CRAYON");
        return btnActivities;
    }
    
    public MyButton createBtnMateriel(Project p) {
        //Génération d'un Item Bouton ACTIVITES
        MyButtonProject btnMateriel = new MyButtonProject("Matériel", "MATERIEL", p, getMainController(), getScrollPaneStyle());
        btnMateriel.addIconButton("CRAYON");
        return btnMateriel;
    }  

}