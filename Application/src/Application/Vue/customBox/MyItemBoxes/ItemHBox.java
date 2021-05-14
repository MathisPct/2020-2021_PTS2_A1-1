/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyItemBoxes;

import Application.Vue.customBox.MyButtons.MyButton;
import Application.Vue.customBox.MyStyles.MyStyle;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author David
 */
public class ItemHBox extends HBox {
    private String name;
    private String value;
    private MyStyle style;
    
    public ItemHBox(String name, String value, MyStyle style) {
        this.name = name;
        this.value = value;
        this.style = style;
    }
    
    public ItemHBox(MyStyle style) {
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
        labelValue.setFont(javafx.scene.text.Font.font(this.style.getFont(), FontWeight.NORMAL, FontPosture.ITALIC, style.getFontSize()*0.7));
        this.getChildren().addAll(labelName, labelValue);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(this.style.getItemSpacing());
        int padding = this.style.getItemPadding();
        this.setPadding(new javafx.geometry.Insets(padding,padding,padding,padding*3)); //top, right, bottom, left
    }    
}
