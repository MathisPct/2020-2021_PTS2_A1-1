/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.techsScene.TechSkillsScene;

import Application.Database.DaoError;
import Application.Metier.Skill;
import Application.Metier.Tech;
import Application.Vue.Login.SceneLoginController;
import Application.Vue.customBox.MyScrollPanes.MyScrollPaneSkills;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.main.MainController;
import Application.Vue.techsScene.TechSkillsScene.AddSkillScene.AddSkillWindow;
import Application.Vue.techsScene.sceneTechsController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
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
    private sceneTechsController techsController;

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
    
    public SceneTechSkillsController(Tech tech, MainController mainController, sceneTechsController techsController){
        this.tech = tech;
        this.listSkills = new ArrayList<>();
        this.SPskills = null;
        this.techsController = techsController;
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialize ScneTechSkills");
        System.out.println("Tech : " + tech.getFirstName());
        initFields();
        initScrollPaneSkills();
        //initChart(tech);
        createBarChart();
    }
    
    public void initScrollPaneSkills(){
        containerTechGraph.getChildren().clear();
        MyStyle style = new MyStyleOrange("Carlito");
        containerSkills.getChildren().clear();
        SPskills = new MyScrollPaneSkills(style, tech, mainController);
        SPskills.scrollPaneSkill();
        containerSkills.getChildren().add(SPskills);
        createBarChart();
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
        
        containerTechGraph.getChildren().add(new PieChart(createData(tech)));
    }
    
    @FXML
    void actionAddSkill(ActionEvent event) throws SQLException, DaoError, ClassNotFoundException {
        try {
            FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("/Application/Vue/techsScene/TechSkillsScene/AddSkillScene/addSkillWindow.fxml"));
            AddSkillWindow controller = new AddSkillWindow(tech, techsController);
            controller.setSkillsController(this);
            FXMLLoader.setController(controller);
            Parent root1 = (Parent) FXMLLoader.load();
            Stage stage = new Stage();
            //scene.getStylesheets().add("/Application/Vue/Style/General/general.css");
            stage.setTitle("Choisissez une compétences");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
            controller.setStage(stage);           
        } catch (IOException ex) {
            Logger.getLogger(SceneTechSkillsController.class.getName()).log(Level.SEVERE, null, ex);
        } 
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
            System.out.println("BOUCLE FOR");
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
    
    private void createBarChart(){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        //xAxis.setLabel("Compétences");   
        bc.setVerticalGridLinesVisible(false);
        bc.setHorizontalGridLinesVisible(false);
        xAxis.tickLabelFontProperty().set(Font.font(15));
        yAxis.setLabel("Niveau");
 
        int idNiveau = 0;
        for (int i = 0; i < tech.GetSkills().size(); i++) {
            String niveau = tech.GetSkills().get(i).getLevel();
            idNiveau = 1;
            switch (niveau) {
                case "Simple" : idNiveau = 1; 
                break;
                case "Intermediaire" : idNiveau = 2;
                break;
                case "Avancé" : idNiveau = 3;
                break;
            }
            XYChart.Series serie = new XYChart.Series();
            XYChart.Data xyChartData = new XYChart.Data(tech.GetSkills().get(i).getName(), (double)idNiveau);
            serie.getData().add(xyChartData);          
            bc.getData().add(serie);
        }
        bc.getYAxis().setTickLabelsVisible(false);
        bc.getYAxis().setTickMarkVisible(false);
        
        bc.setMaxHeight(300);
        bc.setLegendVisible(false);
        containerTechGraph.getChildren().add(bc);
    }
    
    
    

    
    
    
}
