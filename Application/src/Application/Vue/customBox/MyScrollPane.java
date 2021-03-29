/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox;

import Application.Metier.Tech;
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
    private ArrayList <MyCustomBox> listCustomBox = new ArrayList();
    
    public MyScrollPane(MyStyle style) {
        this.style = style;
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
    
    public MyRowBox generateItemBoxTech(Tech tech) {
        // création du container d'Items
        MyRowBox itemTechRow = new MyRowBox("", this.style);

        // Génération des Item de la boite
        String totalSkills = "<<total compétences>>";
        String grade = "<<grade>>";
        String cout = "<<coût horraire>>";
        ArrayList<ItemVBox> itemBoxList = new ArrayList();         
        ItemVBox i1 = new ItemVBox("Compétences", totalSkills, this.style);
        ItemVBox i2 = new ItemVBox("Grade", grade , this.style);
        ItemVBox i3 = new ItemVBox("Coût horaire", cout , this.style);
        itemBoxList.add(i1);
        itemBoxList.add(i2);
        itemBoxList.add(i3);
        itemTechRow.generateItemVBoxRow(itemBoxList, Priority.NEVER); //ajout des items à la boite d'item

        //Génération d'un Item Bouton
        MyButtonTech btn = new MyButtonTech("Voir détails", tech, this.style);
        btn.addIconButton("CRAYON");
        itemTechRow.addButtonToRowBox(btn);
        return itemTechRow;
    }   
    
    public MyStyle getSPStyle() {
        return this.style;
    }
    
    public ArrayList getCustomBoxList() {
        return this.listCustomBox;
    }

}

