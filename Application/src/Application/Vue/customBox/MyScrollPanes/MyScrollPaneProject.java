/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyScrollPanes;

import Application.Database.ProjectDao;
import Application.Metier.Project;
import Application.Vue.customBox.MyButtons.MyButton;
import Application.Vue.customBox.MyButtons.MyButtonProject;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBox;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBoxProject;
import Application.Vue.customBox.MyItemBoxes.ItemVBox;
import Application.Vue.customBox.MyRowBox;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.main.MainController;
import Application.Vue.projectsScene.SceneProjectsController;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author David
 */
public class MyScrollPaneProject extends MyScrollPane{
    
    //private Project project;
    private ArrayList<Project> listProject; // liste contenant les project récupérés depuis la dao
    private Project project;
    private SceneProjectsController parentController;
    
    public MyScrollPaneProject(MyStyle style, SceneProjectsController parentController, MainController mainController) {
        super(style, mainController);
        this.parentController = parentController;
    }
    
    public MyScrollPane scrollPaneProject() {
        System.out.println("Affiche des projets");
        MyStyle style = new MyStyleOrange("Carlito");
        //MyScrollPane scrollProject = new MyScrollPane(style, getMainController());
        initScrollPaneProject();
        return this;

    }
    
    public void initScrollPaneProject() {        
        VBox vboxLayout = new VBox(this.getScrollPaneStyle().getBoxSpacing());
        for (int i = 0; i < listProject.size(); i++) {         
            MyCustomBox boxProject = new MyCustomBoxProject(listProject.get(i), this.getScrollPaneStyle());       
            parentController.setActionBoxProject(boxProject, listProject.get(i));//Evenement boite principale          
            boxProject.addRowBoxListItem(this.generateMainRow(listProject.get(i))); //ajout de la bôite de titre
            boxProject.addRowBoxListItem(this.generateItemBox(listProject.get(i))); //ajout de la boite d'item
            boxProject.initBox();//initialisation de la boite principale
            this.getCustomBoxList().add(boxProject);//remplissage de la liste de customBox avec l'instance actuellement générée      
            MyCustomBox.setVgrow(boxProject, Priority.ALWAYS);
            vboxLayout.getChildren().add(boxProject);
        }      
        this.setContent(vboxLayout);
        this.setFitToWidth(true);
    }    
    
    public ArrayList<Project> getListProject(){
        return this.listProject;     
    }
    
    public void setProject(Project p) {
        this.project = p;
    }
    
    public void setListProject() throws ClassNotFoundException, SQLException {
        ProjectDao projectDAO = new ProjectDao();     
        listProject = projectDAO.listAll();          
        
    }

    
    public MyRowBox generateMainRow(Project p) { 
        // Génération de la ligne du nom du technicien
        String pName = p.getName();
        MyRowBox mainTechRow = new MyRowBox(pName, getScrollPaneStyle());           
        mainTechRow.generateLineBoxRow();
        return mainTechRow;
    }
    
    public MyRowBox generateItemBox(Project p) {
        // création du container d'Items
        MyRowBox projetItems = new MyRowBox("", getScrollPaneStyle());
        projetItems.generateItemVBoxRow(this.createItemList(p), Priority.NEVER); //ajout des items à la boite d'item
        //Génération d'un Item Bouton ACTIVITES
        projetItems.addButtonToRowBox(this.createBtnActivities(p));
        //Génération d'un Item Bouton MATERIEL
        projetItems.addButtonToRowBox(this.createBtnMateriel(p));
        return projetItems;
    }
        
    public ArrayList<ItemVBox> createItemList(Project p) {
        // Génération des Item de la boite
        String client = "<Client>";
        String statut = p.getStatusString();
        String dateCommande = "00/00/0000";
        String totalActivite = String.valueOf(p.getActivities().size());
        ArrayList<ItemVBox> itemBoxList = new ArrayList();
        ItemVBox i1 = new ItemVBox("Statut", statut, getScrollPaneStyle());
        ItemVBox i2 = new ItemVBox("Client", client, getScrollPaneStyle());
        ItemVBox i3 = new ItemVBox("Commande", dateCommande , getScrollPaneStyle());
        ItemVBox i4 = new ItemVBox("Activités", totalActivite, getScrollPaneStyle());
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