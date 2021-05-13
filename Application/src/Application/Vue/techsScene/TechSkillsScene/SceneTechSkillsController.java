/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.techsScene.TechSkillsScene;

import Application.Database.UserDao;
import Application.Metier.Skill;
import Application.Metier.Tech;
import Application.Vue.customBox.MyRowBox;
import Application.Vue.customBox.MyScrollPanes.MyScrollPaneSkills;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author David
 */
public class SceneTechSkillsController implements Initializable {
    
    private Tech tech;
    private ArrayList<Skill> listSkills;
    private MyScrollPaneSkills SPskills;
    

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
    
    public SceneTechSkillsController(Tech tech){
        this.tech = tech;
        this.listSkills = new ArrayList<>();
        this.SPskills = null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialize ScneTechSkills");
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
    

    
    
    /**
     * Initialise le graphique des technicien
     * @param tech 
     */
    private void initChart(Tech tech){
    }
    

    
    
    
}
