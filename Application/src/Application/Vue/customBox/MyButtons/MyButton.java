/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyButtons;

import Application.Vue.customBox.MyStyles.MyStyle;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author David
 */
public class MyButton extends Button{
    private String name;
    private MyStyle style;
    
    public MyButton(String name, MyStyle style) {
        this.name = name;
        this.setText(name);
        this.style = style;
        this.setStyle("-fx-background-color: " + style.getColorSelected());
        this.setFont(javafx.scene.text.Font.font(this.style.getFont(), FontWeight.NORMAL, FontPosture.REGULAR, style.getBtnFontSize()));
    }
    

    public void setButton() {
        this.setOnAction((event) -> {
            System.out.println("Bouton clicked");
        });
    }
    
    public void addIconButton(String iconName) {   
        Image icon = new Image(getIconUrl(iconName));
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(25);
        imageView.setFitHeight((int)(25 * icon.getHeight() / icon.getWidth()));
        this.setGraphic(imageView);
        this.setStyle("-fx-background-color: "+this.style.getColorSelected());
    }
    
    public String getIconUrl(String iconName) {
        String res = "images/Icons black/Icons_black_00014.png";
        switch (iconName) {
            case "MODIFIER":
                res = "images/Icons black/Icons_black_00012.png";
            break;
            case "CRAYON":
                res = "images/Icons black/Icons_black_00011.png";
            break;
            case "LOUPE":
                res = "images/Icons black/Icons_black_00020.png";
            break;
        }
        return res;
    }
    
    
    
}
