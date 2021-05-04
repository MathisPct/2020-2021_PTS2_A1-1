/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.projectsGraphs.projectsGraphActivity;

import Application.Metier.Project;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author David
 */
public class SceneGraphActivityController implements Initializable{
    
        /**
     * Projet dont on veut voir le détail (loadé grâce au bouton "voir détail")
     */
    private Project projet;
   
    
    public SceneGraphActivityController(Project p) {
        this.projet = p;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
