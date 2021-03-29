/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox;

import Application.Metier.Skill;
import Application.Metier.Tech;
import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
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
    
    public void generateItemVBoxRow(ArrayList<ItemVBox> itemBoxList, Priority priorityFirst) {
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
    
    public void generateItemHBoxRow(ArrayList<ItemHBox> itemBoxList, Priority priorityFirst) {
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
            
        }    
    }
    
    public void generateSkillGridPane(Tech tech, Skill skill) {
        
        setBar();
        GridPane gridpane = new GridPane();
        Label skillName = new Label(skill.getName());
        skillName.setStyle("-fx-text-fill: " + this.style.getColorTitle());
        skillName.setFont(javafx.scene.text.Font.font(this.style.getFont(), FontWeight.NORMAL, FontPosture.REGULAR, style.getFontSize()));
        gridpane.add(skillName, 0, 0);  // column=0 row=0
        skillName.setPadding(new Insets(0, 0, 0, 5)); // T/R/B/L
        
        Label level = new Label("Niveau: ");
        level.setStyle("-fx-text-fill: " + this.style.getColorTitle());
        level.setFont(javafx.scene.text.Font.font(this.style.getFont(), FontWeight.BOLD, FontPosture.REGULAR, style.getFontSize()*0.9));
        Label levelValue = new Label(skill.getLevel());
        levelValue.setFont(javafx.scene.text.Font.font(this.style.getFont(), FontWeight.NORMAL, FontPosture.ITALIC, style.getFontSize()*0.8));

        levelValue.setStyle("-fx-text-fill: " + this.style.getColorTitle());
        HBox hboxLevel = new HBox(level, levelValue);
        hboxLevel.setAlignment(Pos.CENTER_LEFT);
        gridpane.add(hboxLevel, 1, 0);  // column=1 row=0
        
        MyButtonSkill btn = new MyButtonSkill("Modifier", tech, skill, this.style);
        btn.setButton();
        btn.addIconButton("MODIFIER");
        btn.setAlignment(Pos.CENTER);
        btn.setStyle("-fx-background-color: " + this.style.getColorBaseBar());
        GridPane.setMargin(btn, new Insets(5, 0, 5, 0));
        GridPane.setHgrow(btn, Priority.ALWAYS);
        gridpane.add(btn, 2, 0); // column=2 row=0
        gridpane.setPadding(new Insets(3, 3, 3, 3));
        //gridpane.setHgap(25);
        //gridpane.setVgap(8);
        //gridpane.setGridLinesVisible( true );
        
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        col1.setPrefWidth(800);     
        col2.setPrefWidth(200);      
        col3.setPrefWidth(200);
        
        col1.setPercentWidth(50);     
        col2.setPercentWidth(30);      
        col3.setPercentWidth(20);
        col2.setHalignment(HPos.RIGHT);
        col3.setHalignment(HPos.RIGHT);
        gridpane.getColumnConstraints().addAll(col1, col2, col3);

        this.getChildren().addAll(this.boxBar, gridpane);
        this.setStyle("-fx-background-color: " + style.getColorBase());
    }
    
    public void addButtonToRowBox(MyButton btn) {
        btn.setButton();
        ItemVBox btnIB = new ItemVBox(this.style);
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
