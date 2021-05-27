/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.projectsDetailsScene;

import Application.Database.ProjectDao;
import Application.Metier.Project;
import Application.Vue.projectsScene.SceneProjectsController;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author David
 */
public class SceneProjectsDetailsController implements Initializable {
    
    //ATTRIBUTS LOGIQUES
    private Project projet;
    private SceneProjectsController parentController;
    
    
    //ATTRIBUT FXML
    @FXML
    private TextField labelDetailNomProjet;
    @FXML
    private TextField labelEstimatedDuration;
    @FXML
    private TextField labelFinalDuration;
    @FXML
    private Label labelStatutProjet;   
    @FXML
    private Label labelNomProjet;
    
    
    public SceneProjectsDetailsController(Project p, SceneProjectsController parentController) {
        this.projet = p;
        this.parentController = parentController;
    }
       
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("SceneProjectsDetails");
        updateValuesDetailProject();
        changeNameProject();
        

    }
    
    private String singleQuoteFixer(String chaine) {
        char c;
        String newChaine = "";
        char singleQuote = ((char) 39);
        
        for (int i=0; i<chaine.length(); i++) {

            c = chaine.charAt(i);
            if(c == singleQuote) {
                newChaine += singleQuote;
                newChaine += singleQuote;
            } else {
                newChaine += chaine.charAt(i);
            }
        }
        return newChaine;
    }
    
    private void changeNameProject(){
        labelDetailNomProjet.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            String newName = singleQuoteFixer(labelDetailNomProjet.getText()); 
            projet.setName(newName);
            //labelDetailNomProjet.setText(newName);
            
            try {
                ProjectDao projectDAO = new ProjectDao();
                projectDAO.update(projet);
                parentController.initScrollPaneProjects();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(SceneProjectsDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        });
    }
    

    public void updateValuesDetailProject() {
        labelNomProjet.setText(projet.getName());
        labelDetailNomProjet.setText(projet.getName());
        labelEstimatedDuration.setText(String.valueOf(projet.getEstimatedDurationMinutes()));
        labelFinalDuration.setText(String.valueOf(projet.getFinalDuration()));
        labelStatutProjet.setText(projet.getStatusString());
        statusColor();
    }
    
    public void statusColor() {
        if (labelStatutProjet.getText() == "Terminé") {
            labelStatutProjet.setStyle("-fx-text-fill: #8FD14F;");
        }
    }

    public String getLabelDetailNomProjet() {
        return labelDetailNomProjet.getText();
    }

    public void setLabelDetailNomProjetValue(String value) {
        this.labelDetailNomProjet.setText(value);
    }

    public String getLabelEstimatedDurationValue() {
        return labelEstimatedDuration.getText();
    }

    public void setLabelEstimatedDurationValue(String value) {
        this.labelEstimatedDuration.setText(value);
    }

    public String getLabelFinalDuration() {
        return labelFinalDuration.getText();
    }

    public void setLabelFinalDuration(String value) {
        this.labelFinalDuration = labelFinalDuration;
    }

    public String getLabelStatutProjet() {
        return labelStatutProjet.getText();
    }

    public void setLabelStatutProjet(String value) {
        this.labelStatutProjet.setText(value);
    }  
}
