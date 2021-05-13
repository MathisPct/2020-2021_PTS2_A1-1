/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyCustomBoxes;

import Application.Metier.Tech;
import Application.Vue.customBox.MyButtons.MyButtonTech;
import Application.Vue.customBox.MyItemBoxes.ItemVBox;
import Application.Vue.customBox.MyRowBox;
import Application.Vue.customBox.MyScrollPanes.MyScrollPane;
import Application.Vue.customBox.MyStyles.MyStyle;
import java.util.ArrayList;
import javafx.scene.layout.Priority;

/**
 *
 * @author David
 */
public class MyCustomBoxTech extends MyCustomBox {
    
    private Tech tech;
    
    public MyCustomBoxTech(Tech tech, MyStyle style) {
        super(style);
        this.tech = tech;
    }   
}
