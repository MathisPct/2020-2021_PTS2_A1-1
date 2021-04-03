/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author David
 */
public class ItemVBox extends VBox {
    private String name;
    private String value;
    private MyStyle style;
    
    public ItemVBox(String name, String value, MyStyle style) {
        this.name = name;
        this.value = value;
        this.style = style;
    }
    
    public ItemVBox(MyStyle style) {
        this.name = "";
        this.value = "";
        this.style = style;
    }
    
    public void initItemButtonBox(MyButton btn) {
        btn.setAlignment(Pos.CENTER);
        this.getChildren().add(btn);
        this.setSpacing(this.style.getItemSpacing());
        int padding = this.style.getItemPadding()*2;
        this.setPadding(new javafx.geometry.Insets(padding,padding,padding,padding)); //top, right, bottom, left
    }
    
    public void initItemBox() {
        Label labelName = new Label(name);
        Label labelValue = new Label(value);
        labelName.setFont(javafx.scene.text.Font.font(this.style.getFont(), FontWeight.BOLD, FontPosture.REGULAR, style.getFontSize()*0.9));
        labelValue.setFont(javafx.scene.text.Font.font(this.style.getFont(), FontWeight.NORMAL, FontPosture.ITALIC, style.getFontSize()*0.8));
        this.getChildren().addAll(labelName, labelValue);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(this.style.getItemSpacing());
        int padding = this.style.getItemPadding();
        this.setPadding(new javafx.geometry.Insets(padding,padding,padding,padding*3)); //top, right, bottom, left
    }
    
    public void initSubItem(String fontMode, String colorMode){
        float cName = 0.7f;
        float cValue = 0.9f;
        String res = "-fx-text-fill: "+ style.getColorLabel();
        Label labelName = new Label(name);
        Label labelValue = new Label(value);
        switch (fontMode) {
            case "LARGE":
                cName *= 1.2;
                cValue *= 1.2;
                labelName.setFont(javafx.scene.text.Font.font(this.style.getFont(), FontWeight.BOLD, FontPosture.ITALIC, style.getFontSize()*cName));
                labelValue.setFont(javafx.scene.text.Font.font(this.style.getFont(), FontWeight.NORMAL, FontPosture.ITALIC, style.getFontSize()*cValue));
                break;
            case "MEDIUM":
                labelName.setFont(javafx.scene.text.Font.font(this.style.getFont(), FontWeight.NORMAL, FontPosture.ITALIC, style.getFontSize()*cName));
                labelValue.setFont(javafx.scene.text.Font.font(this.style.getFont(), FontWeight.NORMAL, FontPosture.ITALIC, style.getFontSize()*cValue));
                break;
        }
        
        switch (colorMode) {
            case "NORMAL":
                labelName.setStyle(res);
                labelValue.setStyle(res);
                break;
            case "IMPORTANT":
                res = "-fx-text-fill: #CD1F35";
                labelName.setStyle(res);
                labelValue.setStyle(res);
                break;
        }
  
        this.getChildren().addAll(labelName, labelValue);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(this.style.getItemSpacing());
        int padding = this.style.getItemPadding();
        this.setPadding(new javafx.geometry.Insets(padding,padding,padding,padding*3)); //top, right, bottom, left
    }
}
