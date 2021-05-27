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
    private ProjectDao projectDao;
    
    
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
        try {
            this.projectDao = new ProjectDao();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SceneProjectsDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("SceneProjectsDetails");
        updateValuesDetailProject();
        //action listeners 
        changeNameProject();
        changeEstimatedDurationProject();
        changeFinalDurationProject();
    }
    
    private void changeNameProject(){
        labelDetailNomProjet.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            String newName = singleQuoteFixer(labelDetailNomProjet.getText()); 
            projet.setName(newName);
            //labelDetailNomProjet.setText(newName);
            try {
                projectDao.update(projet);
                parentController.initScrollPaneProjects();
            } catch (SQLException ex) {
                Logger.getLogger(SceneProjectsDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
    }
        
    private void changeEstimatedDurationProject(){
        labelEstimatedDuration.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            String newDuration = onlyNumbers(labelEstimatedDuration.getText());
            projet.setEstimatedDurationMinutes(Integer.parseInt(newDuration));
            labelEstimatedDuration.setText(newDuration);
            try {
                projectDao.update(projet);
                parentController.initScrollPaneProjects();
            } catch (SQLException ex) {
                Logger.getLogger(SceneProjectsDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        });          
    }
    
    private void changeFinalDurationProject(){
        labelFinalDuration.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            String newDuration = onlyNumbers(labelFinalDuration.getText());
            projet.setFinalDuration(Integer.parseInt(newDuration));
            labelFinalDuration.setText(newDuration);
            try {
                projectDao.update(projet);
                parentController.initScrollPaneProjects();
            } catch (SQLException ex) {
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
    
    private String onlyNumbers(String chaine) {
        String newChaine = "";
        char c;
        for (int i=0; i<chaine.length(); i++) {
            c = chaine.charAt(i);
            if(Character.isDigit(chaine.charAt(i))){
                newChaine += c;
            }
        }      
        return newChaine;    
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
