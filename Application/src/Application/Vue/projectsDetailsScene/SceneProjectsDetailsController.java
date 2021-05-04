/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.projectsDetailsScene;

import Application.Metier.Project;
import Application.Vue.Login.SceneLoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author David
 */
public class SceneProjectsDetailsController implements Initializable {
    

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
    


    /**
     * Projet dont on veut voir le détail (loadé grâce au bouton "voir détail")
     */
    private Project projet;
   
    
    public SceneProjectsDetailsController(Project p) {
        this.projet = p;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("SceneProjectsDetails");
        updateValuesDetailProject();
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
