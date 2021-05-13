/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyPanes;

import Application.Vue.customBox.MyStyles.MyStyle;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author David
 */
public abstract class MyPane extends StackPane {

    private MyStyle style;
    private VBox Vcontainer;
    private HBox Hcontainer;
    private ImageView icon;
    
    public MyPane(MyStyle style){
        this.style = style;
        //this.Vcontainer = null;
    }
    
    public abstract void initPane();
    
    public VBox createVBox(String title, String value){
        VBox vbox = new VBox();
        Label lTitle = new Label(title);
        Label lValue = new Label(value);
        vbox.getChildren().addAll(lTitle, lValue);
        this.getChildren().add(vbox);
        
        //Styling  
        ArrayList<Label> listLabel = new ArrayList<>();
        listLabel.add(lTitle);
        listLabel.add(lValue);
        VBoxFontStyle(listLabel,0);
        
        //Layout
        VBoxLayout(vbox);
        return vbox;
    }
    
    public HBox createHBoxTitleWithIcon(String title, String iconName){
        HBox hbox = new HBox();
        Label lTitle = new Label(title);
        
        hbox.getChildren().addAll(imageView(iconName),lTitle);
        //hbox.getChildren().add(lTitle);
        this.getChildren().add(hbox);
        
        return hbox;
    }
    
    public void VBoxLayout(VBox vbox){
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(this.style.getItemSpacing());
        int padding = this.style.getItemPadding();
        this.setPadding(new javafx.geometry.Insets(padding,padding,padding,padding*3)); //top, right, bottom, left
    }
    
    public void VBoxFontStyle(ArrayList<Label> listLabel, int TitlePosition){
        for (Label label : listLabel) {
            if (label == listLabel.get(TitlePosition)){
                label.setFont(javafx.scene.text.Font.font(this.style.getFont(), FontWeight.BOLD, FontPosture.REGULAR, style.getFontSize()*0.9));
            }
            else{
                label.setFont(javafx.scene.text.Font.font(this.style.getFont(), FontWeight.NORMAL, FontPosture.ITALIC, style.getFontSize()*0.8));  
            }
        }    
    }
    
    public ImageView imageView(String iconName) {   
        Image icon = new Image(getImageUrl(iconName));
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(25);
        imageView.setFitHeight((int)(25 * icon.getHeight() / icon.getWidth()));
        return imageView;
    }
    
    public String getImageUrl(String iconName) {
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
            case "OK":
                res = "images/finished.png";
            break;
            case "WORKING":
                res = "images/waiting1.png";
            break;
            case "CANCELED":
                res = "images/canceled.png";
            break;
            case "WAITING":
                res = "images/waiting1.png";
            break;
        }
        return res;
    }
    
    public void initHContainer(){
        this.Hcontainer = new HBox();
        this.getChildren().add(this.Hcontainer);
    }
    
    public void initVContainer(){
        this.Vcontainer = new VBox();
        this.getChildren().add(this.Vcontainer);
    }
            

    public MyStyle getPaneStyle() {
        return style;
    }

    public VBox getVcontainer() {
        return Vcontainer;
    }

    public HBox getHcontainer() {
        return Hcontainer;
    }

    public ImageView getIcon() {
        return icon;
    }
    
    
    
    
}
