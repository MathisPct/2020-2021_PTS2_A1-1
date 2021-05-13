/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyPanes;

import Application.Vue.customBox.MyStyles.MyStyle;
import javafx.scene.image.ImageView;

/**
 *
 * @author David
 */
public class MyPaneIcon extends MyPane {

    private ImageView icon;
    private int size;
    
    public MyPaneIcon(MyStyle style, String iconName, int size) {
        super(style);
        initVContainer();
        this.icon = imageView(iconName);
        this.size = size;
        initPane();;
    }

    @Override
    public void initPane() {
        getVcontainer().setPadding(new javafx.geometry.Insets(5,5,5,5)); //top, right, bottom, left
        icon.setFitHeight(size);
        icon.setFitWidth(size);
        getVcontainer().getChildren().add(icon);
    }
    
}
