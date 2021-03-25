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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


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
    
    public void initScrollPaneTech(ArrayList<Tech> listTech) {        
        VBox vboxLayout = new VBox(this.style.getBoxSpacing());
        for (int i = 0; i < listTech.size(); i++) {
            
            //création de la boite principale
            MyCustomBox boxTech = new MyCustomBox(this, this.style);
            //Evenement boite principale
            boxTech.setOnMouseClicked((event) -> {
                System.out.println("Instance clicked");
                findBox(boxTech);
                boxTech.openBox();
            }); 

            // Génération de la ligne du nom du technicien
            String techFullName = listTech.get(i).getFirstName()+" "+listTech.get(i).getLastName();
            MyRowBox mainTechRow = new MyRowBox(techFullName, this.style);           
            mainTechRow.generateLineBoxRow();
            
            // création du container d'Items
            MyRowBox itemTechRow = new MyRowBox("", this.style);

            // Génération des Item de la boite
            ArrayList<ItemBox> itemBoxList = new ArrayList();         
            ItemBox i1 = new ItemBox("Compétences", "<<total compétences>>", this.style);
            ItemBox i2 = new ItemBox("Grade", "<<grade>>", this.style);
            ItemBox i3 = new ItemBox("Coût horaire", "<<coût horraire>>", this.style);
            itemBoxList.add(i1);
            itemBoxList.add(i2);
            itemBoxList.add(i3);
            itemTechRow.generateItemBoxRow(itemBoxList, Priority.NEVER); //ajout des items à la boite d'item

            //Génération d'un Item Bouton
            MyButtonTech btn = new MyButtonTech("Voir détails", this.style);
            btn.addIconButton("CRAYON");
            itemTechRow.addButtonToRowBox(btn);
       
            // ajout des boites secondaires à la boite principale
            boxTech.addRowBoxListItem(mainTechRow); //ajout de la bôite de titre
            boxTech.addRowBoxListItem(itemTechRow); //ajout de la boite d'item
            boxTech.initBox(); // initialisation de la boite principale
            this.listCustomBox.add(boxTech); //remplissage de la liste de customBox avec l'instance actuellement générée
            
            MyCustomBox.setVgrow(boxTech, Priority.ALWAYS);
            vboxLayout.getChildren().add(boxTech);
        }      
        this.setContent(vboxLayout);
        this.setFitToWidth(true);
    }
    
    public void findBox(MyCustomBox box) {
        for (int i = 0; i < this.listCustomBox.size(); i++) {
            this.listCustomBox.get(i).closeBox();
        }
    }
}

