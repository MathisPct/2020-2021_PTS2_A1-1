/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox;

import Application.Metier.Project;
import Application.Metier.Tech;
import java.util.ArrayList;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author David
 */
public class MyCustomBox extends VBox {
    private MyStyle style;
    private VBox Vcontainer;
    private ArrayList<MyRowBox> rowBoxList;
    private boolean isOpen;
    private MyScrollPane parent;
    private Tech tech;
    private Project project;
    
    public MyCustomBox(MyScrollPane parent, Tech tech, MyStyle style) {
        this.style = style;
        this.Vcontainer = new VBox();
        this.rowBoxList = new ArrayList();
        this.isOpen = false;
        this.parent = parent;
        this.tech = tech;
        this.project = null;
    }
    
    public MyCustomBox(MyScrollPane parent, Project project, MyStyle style) {
        this.style = style;
        this.Vcontainer = new VBox();
        this.rowBoxList = new ArrayList();
        this.isOpen = false;
        this.parent = parent;
        this.tech = null;
        this.project = project;
    }
    
    public void initBox() {
        //parcourt de la liste de rowBox
        for (int i = 0; i<this.rowBoxList.size(); i++) {           
            MyCustomBox.setVgrow(rowBoxList.get(i), Priority.ALWAYS);         
        }
        this.Vcontainer.getChildren().add(this.rowBoxList.get(0));
        this.getChildren().addAll(this.Vcontainer);
    }
    
    public void removeRowBoxListItem(MyRowBox rowBox) {
        this.rowBoxList.remove(rowBox);
    }

    public void addRowBoxListItem(MyRowBox rowBox) {
        this.rowBoxList.add(rowBox);
    }   
    
    public void switchOpening() {      
        this.isOpen = !(this.isOpen);
    }
    
    public boolean getIsOpen() {
        return this.isOpen;
    }
    
    public void closeBox() {
        
        for (int i = 1; i<this.rowBoxList.size(); i++) {
            this.Vcontainer.getChildren().remove(this.rowBoxList.get(i));
            }
        this.rowBoxList.get(0).setTitleBasic(this.rowBoxList.get(0).getTitle());
        rowBoxList.get(0).setBoxBarColor(this.style.getColorBaseBar());
        rowBoxList.get(0).setBoxColor(this.style.getColorBase());    
    }
    
    public void openBox() {
        for (int i = 1; i<this.rowBoxList.size(); i++) {
            this.Vcontainer.getChildren().add(this.rowBoxList.get(i));
            this.rowBoxList.get(i).setBoxBarColor(this.style.getColorSelected());
            }
        this.rowBoxList.get(0).setTitleSelected(this.rowBoxList.get(0).getTitle());
        rowBoxList.get(0).setBoxBarColor(this.style.getColorSelected());
        rowBoxList.get(0).setBoxColor(this.style.getColorSelected());
    }
    
    public Tech GetTech() {
        return this.tech;
    }
      
    public void openBoxAction() {
        System.out.println("Custom box clicked");  
    }
    
    
}
