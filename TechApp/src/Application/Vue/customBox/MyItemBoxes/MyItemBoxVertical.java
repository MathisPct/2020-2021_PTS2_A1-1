/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyItemBoxes;

import Application.Vue.customBox.MyStyles.MyStyle;
import javafx.scene.layout.VBox;

/**
 *
 * @author David
 */
public class MyItemBoxVertical extends MyItemBox {

    public MyItemBoxVertical(MyStyle style) {
        super(style);
    }

    @Override
    public void initItemBox() {
        VBox box = new VBox();
        this.getChildren().add(box);
    }
    
}
