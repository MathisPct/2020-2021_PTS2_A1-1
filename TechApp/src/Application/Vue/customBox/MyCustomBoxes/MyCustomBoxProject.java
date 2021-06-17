/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyCustomBoxes;

import Application.Metier.Project;
import Application.Metier.Tech;
import Application.Vue.customBox.MyScrollPanes.MyScrollPane;
import Application.Vue.customBox.MyStyles.MyStyle;

/**
 *
 * @author David
 */
public class MyCustomBoxProject extends MyCustomBox {
      
    private Project project;

    public MyCustomBoxProject(Project project, MyStyle style) {
        super(style);
        this.project = project;
    }
}
