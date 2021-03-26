/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author David
 */
public class MyRowBox extends HBox{
    
    private Label title;
    private MyStyle style;
    private AnchorPane boxBar;
    private Label label1;
    //private String boxBarColor;
    
    public MyRowBox(String name, MyStyle style) {
        this.style = style;
        this.title = new Label(name);
        this.boxBar = null;
        this.label1 = null; 
    }
  
    public MyRowBox(String label, String label1, MyStyle style) {
        this.title = new Label(label);
        this.label1 = new Label(label1);
        this.style = style;
        this.boxBar = null;
    }
    
    public void generateLineBoxRow() {
        setBar();
        setTitleBasic(title);
        this.getChildren().addAll(this.boxBar, this.title);
        this.setStyle("-fx-background-color: " + style.getColorBase());   
    }
    
    public void generateLineBoxTwoText() {
        setBar();
        setTitleBasic(this.title);
        setTitleBasic(this.label1);
        this.getChildren().addAll(this.boxBar, this.title, addSpacer(),this.label1);
        
        this.setStyle("-fx-background-color: " + style.getColorBase());   
    }
    
    public void generateItemBoxRow(ArrayList<ItemBox> itemBoxList, Priority priorityFirst) {
        setBar();
        this.getChildren().addAll(this.boxBar);      
        for (int i = 0; i<itemBoxList.size(); i++) {
            if (i == 0) {
                HBox.setHgrow(itemBoxList.get(i), priorityFirst);
            }
            else {
                HBox.setHgrow(itemBoxList.get(i), Priority.ALWAYS);
            }
            itemBoxList.get(i).initItemBox();
            this.getChildren().add(itemBoxList.get(i));
            this.setStyle("-fx-background-color: " + style.getColorSelectedLight());
        }    
    }
    
    public void addButtonToRowBox(MyButton btn) {
        btn.setButton();
        ItemBox btnIB = new ItemBox(this.style);
        btnIB.initItemButtonBox(btn); // initialisation de la boite bouton
        this.getChildren().add(btnIB);
    }
    
    public void setTitleSelected(Label label) {
        label.setFont(javafx.scene.text.Font.font(this.style.getFont(), FontWeight.BOLD, FontPosture.REGULAR, style.getFontSize()*1.1));
        label.setStyle("-fx-text-fill: " + style.getColorTitleSelected());
        int pV = this.style.getTitlePaddingV();
        int pH = this.style.getTitlePaddingH();
        label.setPadding(new Insets(pV*1.1,pH,pV*1.1,pH+5)); //top, right, bottom, left 
    }
    
    public void setTitleBasic(Label label) {
        label.setFont(javafx.scene.text.Font.font(this.style.getFont(), FontWeight.NORMAL, FontPosture.REGULAR, style.getFontSize()));
        label.setStyle("-fx-text-fill: " + style.getColorTitle());
        int pV = this.style.getTitlePaddingV();
        int pH = this.style.getTitlePaddingH();
        label.setPadding(new Insets(pV,pH,pV,pH)); //top, right, bottom, left
    }
    
    public void setBar() {
        this.boxBar = new AnchorPane();
        this.boxBar.setStyle("-fx-background-color: " + style.getColorBaseBar());
        this.boxBar.setMinWidth(style.getWidthBar());
        this.boxBar.setMaxWidth(style.getWidthBar());
        VBox.setVgrow(this.boxBar, Priority.NEVER);
        HBox.setHgrow(this.boxBar, Priority.NEVER);
    }
    
    public void setBoxBarColor(String color) {
        this.boxBar.setStyle("-fx-background-color: " + color);
    }
    
    public void setBoxColor(String color) {
        this.setStyle("-fx-background-color: " + color);
    }
            
    public AnchorPane addSpacer() {
        AnchorPane spacer = new AnchorPane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    public Label getTitle() {
        return title;
    }

    public Label getLabel1() {
        return label1;
    }
    
    
    

}
