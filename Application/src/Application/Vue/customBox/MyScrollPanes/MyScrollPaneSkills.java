/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyScrollPanes;

import Application.Metier.Tech;
import Application.Vue.customBox.MyRowBox;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.main.MainController;
import Application.Vue.techsScene.sceneTechsController;
import java.sql.SQLException;
import javafx.scene.layout.VBox;

/**
 *
 * @author David
 */
public class MyScrollPaneSkills extends MyScrollPane{

    private Tech tech;
    
    public MyScrollPaneSkills(MyStyle style, Tech tech, MainController mainController) {
        super(style, mainController);
        this.tech = tech;
    }

    
        /**
     * Cette fonction créé le scrollpane des skills en lui assignant un style et en définissant sa boite parente
     * à savoir containerSkill.
     * @param tech 
     */
    public void scrollPaneSkill() {
      
        System.out.println("SceneSkill");
        MyStyle style = new MyStyleOrange("Carlito");         
        initScrollPaneSkill();
        //this.containerSkills.getChildren().add(this.scrollSkills);       
    } 
    
    /**
    * Cette fonction génère les box de skills d'un technicien passé en paramètre dans le scrollPane de skills
    * @param tech 
    */
    public void initScrollPaneSkill() {
        
        this.getChildren().clear();
        this.setContent(null);     

        VBox vboxLayout = new VBox(this.getScrollPaneStyle().getBoxSpacing());

        for (int i = 0; i < tech.GetSkills().size(); i++) {
            MyRowBox skillRow = new MyRowBox("", "", this.getScrollPaneStyle());
            skillRow.generateSkillGridPane(tech, tech.GetSkills().get(i));
            vboxLayout.getChildren().add(skillRow);
        }
        this.setContent(vboxLayout);   
        this.setFitToWidth(true);
        //this.containerSkills.getChildren().add(this);
        //initChart(tech);
    }
    
}
