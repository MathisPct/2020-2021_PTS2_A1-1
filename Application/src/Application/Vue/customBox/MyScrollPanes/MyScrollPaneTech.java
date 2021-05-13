/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyScrollPanes;

import Application.Metier.Project;
import Application.Metier.Tech;
import Application.Vue.customBox.MyButtons.MyButtonTech;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBox;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBoxTech;
import Application.Vue.customBox.MyItemBoxes.ItemVBox;
import Application.Vue.customBox.MyRowBox;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.main.MainController;
import Application.Vue.techsScene.sceneTechsController;
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
            boxTech.addRowBoxListItem(generateMainTechRow(parentController.getListTechs().get(i))); //ajout de la bôite de titre
            boxTech.addRowBoxListItem(generateItemBoxTech(parentController.getListTechs().get(i))); //ajout de la boite d'item
            boxTech.initBox();//initialisation de la boite principale
            this.getCustomBoxList().add(boxTech);//remplissage de la liste de customBox avec l'instance actuellement générée      
            MyCustomBox.setVgrow(boxTech, Priority.ALWAYS);
            vboxLayout.getChildren().add(boxTech);
        }      
        this.setContent(vboxLayout);
        this.setFitToWidth(true);
    }
    
    public MyRowBox generateItemBoxTech(Tech tech) {
        // création du container d'Items
        MyRowBox itemTechRow = new MyRowBox("", this.getScrollPaneStyle());
        itemTechRow.generateItemVBoxRow(createTechItemList(tech), Priority.NEVER); //ajout des items à la boite d'item
        //Génération d'un Item Bouton
        MyButtonTech btn = new MyButtonTech("Voir détails", tech, this.getScrollPaneStyle());
        btn.addIconButton("CRAYON");
        itemTechRow.addButtonToRowBox(btn);
        return itemTechRow;
    }
    
    
    public ArrayList<ItemVBox> createTechItemList(Tech tech) {
        // Génération des Item de la boite technicien
        String totalSkills = String.valueOf(tech.GetSkills().size());
        String grade = tech.getGrade();
        String euro = Character.toString ((char) 8364);
        String cout = tech.getCoutHoraire() + euro + " /h";
        ArrayList<ItemVBox> itemBoxList = new ArrayList();         
        ItemVBox i1 = new ItemVBox("Compétences", totalSkills, this.getScrollPaneStyle());
        ItemVBox i2 = new ItemVBox("Grade", grade , this.getScrollPaneStyle());
        ItemVBox i3 = new ItemVBox("Coût horaire", cout , this.getScrollPaneStyle());
        itemBoxList.add(i1);
        itemBoxList.add(i2);
        itemBoxList.add(i3);
        return itemBoxList;
    }
    
        public MyRowBox generateMainTechRow(Tech tech) { 
        // Génération de la ligne du nom du technicien
        String techFullName = tech.getFirstName()+" "+tech.getLastName();
        MyRowBox mainTechRow = new MyRowBox(techFullName, this.getScrollPaneStyle());           
        mainTechRow.generateLineBoxRow();
        return mainTechRow;
    }
    
    public void setTech(Tech tech) {
        this.tech = tech;
    }
    
    public Tech getTech() {
        return this.tech;
    }   
}
