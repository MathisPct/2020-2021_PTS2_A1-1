/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyCustomBoxes;

import Application.Metier.Tech;
import Application.Vue.customBox.MyButtons.MyButtonTech;
import Application.Vue.customBox.MyItemBoxes.ItemVBox;
import Application.Vue.customBox.MyRowBox;
import Application.Vue.customBox.MyScrollPanes.MyScrollPane;
import Application.Vue.customBox.MyStyles.MyStyle;
import java.util.ArrayList;
import javafx.scene.layout.Priority;

/**
 *
 * @author David
 */
public class MyCustomBoxTech extends MyCustomBox {
    
    private Tech tech;
    
    public MyCustomBoxTech(Tech tech, MyStyle style) {
        super(style);
        this.tech = tech;
    }
    
    public MyRowBox generateMainTechRow(Tech tech) { 
        // Génération de la ligne du nom du technicien
        String techFullName = tech.getFirstName()+" "+tech.getLastName();
        MyRowBox mainTechRow = new MyRowBox(techFullName, getCustomBoxStyle());           
        mainTechRow.generateLineBoxRow();
        return mainTechRow;
    }
    
    public MyRowBox generateItemBoxTech(Tech tech) {
        // création du container d'Items
        MyRowBox itemTechRow = new MyRowBox("", getCustomBoxStyle());
        itemTechRow.generateItemVBoxRow(createTechItemList(tech), Priority.NEVER); //ajout des items à la boite d'item
        //Génération d'un Item Bouton
        MyButtonTech btn = new MyButtonTech("Voir détails", tech, getCustomBoxStyle());
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
        ItemVBox i1 = new ItemVBox("Compétences", totalSkills, getCustomBoxStyle());
        ItemVBox i2 = new ItemVBox("Grade", grade , getCustomBoxStyle());
        ItemVBox i3 = new ItemVBox("Coût horaire", cout , getCustomBoxStyle());
        itemBoxList.add(i1);
        itemBoxList.add(i2);
        itemBoxList.add(i3);
        return itemBoxList;
    }
    
}
