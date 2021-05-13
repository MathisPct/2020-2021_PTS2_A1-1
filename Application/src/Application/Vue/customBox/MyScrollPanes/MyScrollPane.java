/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyScrollPanes;


import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBox;
import Application.Vue.main.MainController;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;

/**
 *
 * @author David
 */
public class MyScrollPane extends ScrollPane{
    private MyStyle style;
    private MainController mainController;
    private ArrayList <MyCustomBox> listCustomBox = new ArrayList();
    
    public MyScrollPane(MyStyle style, MainController mainController) {
        this.style = style;
        this.mainController = mainController;
        this.setPadding(new Insets(10,10,10,10)); //top, right, bottom, left 
    }
  
    public void findBox(MyCustomBox box) {
        for (int i = 0; i < this.listCustomBox.size(); i++) {
            this.listCustomBox.get(i).closeBox();
        }
    }
     
    public MyStyle getScrollPaneStyle() {
        return this.style;
    }
    
    public ArrayList getCustomBoxList() {
        return this.listCustomBox;
    }
    
    public MainController getMainController(){
        return mainController;
    }
}

