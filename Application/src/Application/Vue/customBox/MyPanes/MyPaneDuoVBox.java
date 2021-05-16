/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyPanes;

import Application.Vue.customBox.MyStyles.MyStyle;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author David
 */
public class MyPaneDuoVBox extends MyPane {
    
    private String title;
    private String value;

    public MyPaneDuoVBox(MyStyle style, String title, String value) {
        super(style);
        initVContainer();
        this.title = title;
        this.value = value;
        initPane();
    }

    @Override
    public void initPane() {
        getVcontainer().getChildren().add(createVBox(title, value));
    }
    
}
