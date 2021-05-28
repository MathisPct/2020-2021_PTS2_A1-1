/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.techsScene.TechSkillsScene;

import Application.Metier.Skill;
import Application.Metier.Tech;
import Application.Vue.Login.SceneLoginController;
import Application.Vue.customBox.MyScrollPanes.MyScrollPaneSkills;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.main.MainController;
import Application.Vue.techsScene.TechSkillsScene.AddSkillScene.AddSkillWindow;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author David
 */
public class SceneTechSkillsController implements Initializable {
    
    private Tech tech;
    private ArrayList<Skill> listSkills;
    private MyScrollPaneSkills SPskills;
    private MainController mainController;
    

    @FXML
    private Label TechName;
    @FXML
    private VBox containerSkills;
    @FXML
    private JFXButton addSkillButton;
    @FXML
    private Label GraphTechName;
    @FXML
    private AnchorPane containerTechGraph;
    
    @FXML
    void actionAddSkill(ActionEvent event) {
        try {
            FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("/Application/Vue/techsScene/TechSkillsScene/AddSkillScene/addSkillWindow.fxml"));
            AddSkillWindow controller = new AddSkillWindow(tech);
            FXMLLoader.setController(controller);
            Parent root1 = (Parent) FXMLLoader.load();
            Stage stage = new Stage();
            //scene.getStylesheets().add("/Application/Vue/Style/General/general.css");
            stage.setTitle("Choisissez une compétences");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SceneTechSkillsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public SceneTechSkillsController(Tech tech, MainController mainController){
        this.tech = tech;
        this.listSkills = new ArrayList<>();
        this.SPskills = null;
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialize ScneTechSkills");
        System.out.println("Tech : " + tech.getFirstName());
        initFields();
        MyStyle style = new MyStyleOrange("Carlito");
        SPskills = new MyScrollPaneSkills(style, tech, mainController);
        SPskills.scrollPaneSkill();
        containerSkills.getChildren().add(SPskills);      
    }
    
    /**
     * Cette fonction sert à définir le nom de technicien à afficher.
     * @param tech le technicien à afficher dasn le label
     */
    public void initFields() {
        String techName = tech.getFirstName() + " " + tech.getLastName();
        this.TechName.setText(techName);
        this.GraphTechName.setText(techName);
    }
    
    public void clearSkillContainer(){
        this.containerSkills.getChildren().clear();
    }
    
    public VBox getContainerSkills() {
        return containerSkills;
    }

    public AnchorPane getContainerTechGraph() {
        return containerTechGraph;
    }
    
    /**
     * Initialise le graphique des technicien
     * @param tech 
     */
    private void initChart(Tech tech){
    }
    
        /**
     * Genère les datas pour construire le graphique de skill technicien
     * @param tech
     * @return 
     */
    private ObservableList<PieChart.Data> createData(Tech tech) {
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();   
        int idNiveau = 0;
        for (int i = 0; i < tech.GetSkills().size(); i++) {
            String niveau = tech.GetSkills().get(i).getLevel();
            idNiveau = 1;
            switch (niveau) {
                case "Simple" : idNiveau = 1; 
                break;
                case "Intermédiaire" : idNiveau = 2;
                break;
                case "Avancé" : idNiveau = 3;
                break;
            }
            String nameTech = tech.GetSkills().get(i).getName();
            PieChart.Data d = new PieChart.Data("   " + nameTech + "   ", 1.0d*idNiveau);
            pieData.add(d);
        }          
        return pieData;     
    }
    

    
    
    
}
