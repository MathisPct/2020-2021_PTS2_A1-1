/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyItemBoxes;

import Application.Vue.customBox.MyStyles.MyStyle;
import javafx.scene.layout.Pane;

/**
 *
 * @author David
 */
public abstract class MyItemBox extends Pane {
    
    private MyStyle style;
    
    public MyItemBox(MyStyle style){
        this.style = style;
    }
 
    public abstract void initItemBox();
    
}
