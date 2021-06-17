/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyPanes;

import Application.Vue.customBox.MyStyles.MyStyle;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author David
 */
public class MyPaneTitleIcon extends MyPane {
    private Label title;
    private ImageView icon;

    public MyPaneTitleIcon(MyStyle style, String title, String iconName) {
        super(style);
        this.icon = super.imageView(iconName);
        this.title = new Label(title);
        initHContainer();
        initPane();
        
    }

    @Override
    public void initPane() {
        getHcontainer().getChildren().addAll(icon, title);
    }
    
}
