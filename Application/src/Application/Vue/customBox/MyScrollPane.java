/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox;


import Application.Metier.Activity;
import Application.Metier.Project;
import Application.Metier.Tech;
import Application.Vue.main.MainController;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
/**
 *
 * @author David
 */
public class MyScrollPane extends ScrollPane{
    private MyStyle style;
    private MainController mainController;
    private ArrayList <MyCustomBox> listCustomBox = new ArrayList();
    
    public MyScrollPane(MyStyle style, MainController mainController) {
        this.style = style;
        this.mainController = mainController;
        this.setPadding(new Insets(10,10,10,10)); //top, right, bottom, left 
    }
  
    public void findBox(MyCustomBox box) {
        for (int i = 0; i < this.listCustomBox.size(); i++) {
            this.listCustomBox.get(i).closeBox();
        }
    }

    public MyRowBox generateMainTechRow(Tech tech) { 
        // Génération de la ligne du nom du technicien
        String techFullName = tech.getFirstName()+" "+tech.getLastName();
        MyRowBox mainTechRow = new MyRowBox(techFullName, this.style);           
        mainTechRow.generateLineBoxRow();
        return mainTechRow;
    }
      
    public MyRowBox generateMainProjectRow(Project p) { 
        // Génération de la ligne du nom du technicien
        String pName = p.getName();
        MyRowBox mainTechRow = new MyRowBox(pName, this.style);           
        mainTechRow.generateLineBoxRow();
        return mainTechRow;
    }
    
    public MyRowBox generateItemBoxTech(Tech tech) {
        // création du container d'Items
        MyRowBox itemTechRow = new MyRowBox("", this.style);
        itemTechRow.generateItemVBoxRow(createTechItemList(tech), Priority.NEVER); //ajout des items à la boite d'item
        //Génération d'un Item Bouton
        MyButtonTech btn = new MyButtonTech("Voir détails", tech, this.style);
        btn.addIconButton("CRAYON");
        itemTechRow.addButtonToRowBox(btn);
        return itemTechRow;
    }
    
    public MyRowBox generateItemBoxProject(Project p) {
        // création du container d'Items
        MyRowBox projetItems = new MyRowBox("", this.style);
        projetItems.generateItemVBoxRow(createProjectItemList(p), Priority.NEVER); //ajout des items à la boite d'item
        //Génération d'un Item Bouton ACTIVITES
        projetItems.addButtonToRowBox(createBtnActivities(p));
        //Génération d'un Item Bouton MATERIEL
        projetItems.addButtonToRowBox(createBtnMateriel(p));
        return projetItems;
    }
    
    public MyButton createBtnActivities(Project p) {
        //Génération d'un Item Bouton ACTIVITES
        MyButtonProject btnActivities = new MyButtonProject("Activités", "ACTIVITY", p, mainController, this.style);
        btnActivities.addIconButton("CRAYON");
        return btnActivities;
    }
    
    public MyButton createBtnMateriel(Project p) {
        //Génération d'un Item Bouton ACTIVITES
        MyButtonProject btnMateriel = new MyButtonProject("Matériel", "MATERIEL", p, mainController, this.style);
        btnMateriel.addIconButton("CRAYON");
        return btnMateriel;
    }
    

    public ArrayList<ItemVBox> createProjectItemList(Project p) {
        // Génération des Item de la boite
        String client = "<Client>";
        String statut = p.getStatusString();
        String dateCommande = "00/00/0000";
        String totalActivite = String.valueOf(p.getActivities().size());
        ArrayList<ItemVBox> itemBoxList = new ArrayList();
        ItemVBox i1 = new ItemVBox("Statut", statut, this.style);
        ItemVBox i2 = new ItemVBox("Client", client, this.style);
        ItemVBox i3 = new ItemVBox("Commande", dateCommande , this.style);
        ItemVBox i4 = new ItemVBox("Activités", totalActivite, this.style);
        itemBoxList.add(i1);
        itemBoxList.add(i2);
        itemBoxList.add(i3);
        itemBoxList.add(i4);
        return itemBoxList;
    }
    
    public ArrayList<ItemVBox> createTechItemList(Tech tech) {
        // Génération des Item de la boite technicien
        String totalSkills = String.valueOf(tech.GetSkills().size());
        String grade = tech.getGrade();
        String euro = Character.toString ((char) 8364);
        String cout = tech.getCoutHoraire() + euro + " /h";
        ArrayList<ItemVBox> itemBoxList = new ArrayList();         
        ItemVBox i1 = new ItemVBox("Compétences", totalSkills, this.style);
        ItemVBox i2 = new ItemVBox("Grade", grade , this.style);
        ItemVBox i3 = new ItemVBox("Coût horaire", cout , this.style);
        itemBoxList.add(i1);
        itemBoxList.add(i2);
        itemBoxList.add(i3);
        return itemBoxList;
    }
    
    public MyRowBox generateMainActivityRow(Activity a) { 
        // Génération de la ligne du nom du technicien
        String pName = "nom de l'activité";
        MyRowBox mainActivityRow = new MyRowBox(pName, this.style);           
        mainActivityRow.generateLineBoxRow();
        return mainActivityRow;
    }
    
    public MyStyle getScrollPaneStyle() {
        return this.style;
    }
    
    public ArrayList getCustomBoxList() {
        return this.listCustomBox;
    }
    

}

