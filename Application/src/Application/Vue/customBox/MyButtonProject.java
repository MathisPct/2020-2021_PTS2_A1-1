/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox;

import Application.Metier.Project;

/**
 *
 * @author David
 */
public class MyButtonProject extends MyButton{
    private Project project;

    public MyButtonProject(String name, Project p, MyStyle style) {
        super(name, style);
        this.project = p;
    }
    
    @Override
    public void setButton() {
        this.setOnAction((event) -> {
            String txtProject = "Action sur " + this.project.getName();
            System.out.println(txtProject);
        });
    }
}
