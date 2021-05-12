/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.ProjectActivityScene;

import Application.Metier.Activity;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBox;
import Application.Vue.customBox.MyScrollPanes.MyScrollPane;
import Application.Vue.customBox.MyStyles.MyStyle;

/**
 *
 * @author David
 */
public class MyCustomBoxActivity extends MyCustomBox {

    private Activity activity;

    public MyCustomBoxActivity(Activity activity, MyStyle style) {
        super(style);
        this.activity = activity;
    }
    
}
