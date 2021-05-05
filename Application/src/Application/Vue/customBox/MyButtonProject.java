/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox;

import Application.Metier.Project;
import Application.Vue.main.MainController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author David
 */
public class MyButtonProject extends MyButton{
    private Project project;
    private MainController mainController;
    private String type;

    public MyButtonProject(String name, String type, Project p, MainController mainController, MyStyle style) {
        super(name, style);
        this.project = p;
        this.mainController = mainController;
        this.type = type;
    }
    
    /**
     * Cette méthode permet de setter des actions 
     * personnalisées pour un bouton de projet donné
     */
    @Override
    public void setButton() {
        
        this.setOnAction((event) -> {
            switch(type) {
                case "ACTIVITY" :                               
                    try {
                        mainController.projectActivities();
                        System.out.println("[OPEN ACTIVITY: " + this.project.getName() + "]");
                    } catch (IOException ex) {
                        Logger.getLogger(MyButtonProject.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "MATERIEL" : 
                               
                    try {
                        mainController.projectActivities();
                        System.out.println("[OPEN MATERIEL: " + this.project.getName() + "]");
                    } catch (IOException ex) {
                        Logger.getLogger(MyButtonProject.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            }
        });
    }
}
