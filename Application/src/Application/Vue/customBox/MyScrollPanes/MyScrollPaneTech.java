/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyScrollPanes;

import Application.Database.DaoError;
import Application.Metier.Project;
import Application.Metier.Tech;
import Application.Vue.UtilsIHM;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBox;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBoxTech;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.main.MainController;
import Application.Vue.techsScene.sceneTechsController;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


/**
 *
 * @author David
 */
public class MyScrollPaneTech extends MyScrollPane {
    
    private Project project;
    private sceneTechsController parentController;
    private Tech tech;

    public MyScrollPaneTech(MyStyle style, sceneTechsController parentController, MainController mainController) {
        super(style, mainController);
        this.parentController = parentController;
        this.tech = null;
    }
    
    public void scrollPaneTech() {
        System.out.println("SceneTechs");
        MyStyle style = new MyStyleOrange("Carlito");
        initScrollPaneTech();
    }

     /**
     * Cette fonction génère les box de techniciens 
     * dans le scrollPane des techniciens à partir 
     * d'une liste de technicien
     */
    public void initScrollPaneTech() {    
        VBox vboxLayout = new VBox(this.getScrollPaneStyle().getBoxSpacing());
        for (int i = 0; i < parentController.getListTechs().size(); i++) {         
            MyCustomBox boxTech = new MyCustomBoxTech(parentController.getListTechs().get(i), this.getScrollPaneStyle());       
            parentController.setActionBoxTech(boxTech, parentController.getListTechs().get(i));//Evenement boite principale          
            boxTech.addRowBoxListItem(this.generateMainTechRow(parentController.getListTechs().get(i))); //ajout de la bôite de titre
            boxTech.addRowBoxListItem(this.generateItemBoxTech(parentController.getListTechs().get(i))); //ajout de la boite d'item
            boxTech.initBox();//initialisation de la boite principale
            this.getCustomBoxList().add(boxTech);//remplissage de la liste de customBox avec l'instance actuellement générée      
            MyCustomBox.setVgrow(boxTech, Priority.ALWAYS);
            vboxLayout.getChildren().add(boxTech);
        }      
        this.setContent(vboxLayout);
        this.setFitToWidth(true);
    } 
    
    public void setTech(Tech tech) {
        this.tech = tech;
    }
    
    public Tech getTech() {
        return this.tech;
    }   
}
