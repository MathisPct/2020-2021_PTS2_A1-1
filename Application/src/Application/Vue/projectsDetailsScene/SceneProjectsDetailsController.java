/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.projectsDetailsScene;

import Application.Database.ProjectDao;
import Application.Database.UtilsDao;
import Application.Metier.Project;
import Application.Metier.ProjectStatus;
import Application.Vue.UtilsIHM;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.projectsScene.SceneProjectsController;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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
    
     @FXML
    private JFXButton btnStartPause;

    @FXML
    private JFXButton btnValidateProject;

    @FXML
    void actionStartPause(ActionEvent event) {
        if(projet.getStatus() == ProjectStatus.WORKING){
            projet.setStatus(ProjectStatus.WAITING);
            btnStartPause.setText("Démarer");
            
        }
        else if(projet.getStatus() == ProjectStatus.WAITING) {
            projet.setStatus(ProjectStatus.WORKING);
            btnStartPause.setText("Mettre en pause");
        }
        colorButtons();
        initButtonFields();
    }

    @FXML
    void actionValidateUnvalidate(ActionEvent event) {
        if(projet.getStatus() == ProjectStatus.CANCELED) {
            projet.setStatus(ProjectStatus.WAITING);
            btnStartPause.setText("Démarrer");
            btnValidateProject.setText("Invalider le projet");
        }else if(projet.getStatus() == ProjectStatus.WAITING) {
            projet.setStatus(ProjectStatus.CANCELED);
            btnStartPause.setText("Indisponible");
            btnValidateProject.setText("Valider le projet");
        
        }else if(projet.getStatus() == ProjectStatus.WORKING) {
            projet.setStatus(ProjectStatus.CANCELED);
            btnStartPause.setText("Indisponible");
            btnValidateProject.setText("Valider le projet");
        }
                
        try {
            projectDao.update(projet);
        } catch (SQLException ex) {
            ex.printStackTrace();
            UtilsIHM.afficherErreur("Erreur de base de donnnées");
        }
        labelStatutProjet.setText(projet.getStatusString());
        parentController.initScrollPaneProjects();
        colorButtons();
        initButtonFields();
    }
    
    
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
        initButtonFields();
        colorButtons();
        //action listeners 
        changeNameProject();
        changeEstimatedDurationProject();
        changeFinalDurationProject();
    }
    
    private void changeNameProject(){
        labelDetailNomProjet.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {        
            String newName = labelDetailNomProjet.getText();
            projet.setName(newName);
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
            String newDuration = UtilsDao.onlyNumbers(labelEstimatedDuration.getText());
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
            String newDuration = UtilsDao.onlyNumbers(labelFinalDuration.getText());
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
    
    private void initButtonFields() {
        labelStatutProjet.setText(projet.getStatusString());
        if(projet.getStatus() == ProjectStatus.WORKING){
            btnStartPause.setText("Mettre en pause");
            btnStartPause.setVisible(true);
            btnValidateProject.setVisible(true);
        }
        else if(projet.getStatus() == ProjectStatus.WAITING) {
            btnStartPause.setText("Démarrer");
            btnStartPause.setVisible(true);
            btnValidateProject.setVisible(true);
        }
        else if(projet.getStatus() == ProjectStatus.CANCELED) {
            btnStartPause.setVisible(false);
            btnValidateProject.setVisible(true);
            btnStartPause.setText("Indisponible");
            btnValidateProject.setText("Valider le projet");
        }
        else if(projet.getStatus() == ProjectStatus.ENDED) {
            btnStartPause.setVisible(false);
            btnValidateProject.setVisible(false);
            btnStartPause.setText("Indisponible");
            btnValidateProject.setText("Invalider le projet");
        }
    }
    
    public void updateValuesDetailProject() {
        labelNomProjet.setText(projet.getName());
        labelDetailNomProjet.setText(projet.getName());
        labelEstimatedDuration.setText(String.valueOf(projet.getEstimatedDurationMinutes()));
        labelFinalDuration.setText(String.valueOf(projet.getFinalDuration()));
        labelStatutProjet.setText(projet.getStatusString());
        statusColor();
    }
    
    private void colorButtons(){
        MyStyle style = new MyStyleOrange("Carlito");
        if(projet.getStatus() == ProjectStatus.WORKING){         
            btnStartPause.setStyle("-fx-background-color: #2D9BF0");
        }
        else if(projet.getStatus() == ProjectStatus.WAITING) {
            btnStartPause.setStyle("-fx-background-color: #4FB541");
            btnValidateProject.setStyle("-fx-background-color: #CD1F35");
        }
        else if(projet.getStatus() == ProjectStatus.CANCELED) {
            btnValidateProject.setStyle("-fx-background-color: #4FB541");
        }
        else if(projet.getStatus() == ProjectStatus.ENDED) {
            btnValidateProject.setStyle("-fx-background-color: #CD1F35");
        }
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
