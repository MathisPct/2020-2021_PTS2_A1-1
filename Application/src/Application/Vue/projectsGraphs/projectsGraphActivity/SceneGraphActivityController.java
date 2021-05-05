/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.projectsGraphs.projectsGraphActivity;

import Application.Metier.ActivityStatus;
import Application.Metier.Project;
import Application.Vue.Style.CustomCharts.DoughnutChart;
import Application.Vue.main.MainController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author David
 */
public class SceneGraphActivityController implements Initializable{
    //ATTRIBUTS LOGIQUES
    private MainController mainController;
    private Project projet;   
    
    //ATTRIBUTS GRAPHIQUES
    private DoughnutChart rChart;
    @FXML
    private VBox containerGraph;
    @FXML
    private Label labelNomProjet;
    @FXML
    private Label labelValuePrevues;
    @FXML
    private Label labelValueEnCours;
    @FXML
    private Label labelValueAnnulees;
    @FXML
    private Label labelValueRealisees;
    @FXML
    private Label labelValueTotalActivite;
    @FXML
    private JFXButton btnActivities;
      
    public SceneGraphActivityController(Project p, MainController mainController) {
        this.projet = p;
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLabelValueActivities();
        if(getTotalActivities()!=0) {
            initChart(projet);
        }
    }
       
    @FXML
    void openActivities(ActionEvent event) throws IOException {
        this.mainController.projectActivities();
    }
    
    /**
     * Cette méthode initialise le graphique des activités
     * @param p project dont on veut afficher les activités
     */
    private void initChart(Project p){
        containerGraph.getChildren().remove(rChart);
        rChart = new DoughnutChart(createData(p));
        containerGraph.getChildren().add(rChart);
        rChart.setLabelsVisible(false);
        rChart.setAnimated(true);
        rChart.getStyleClass().add("unique");
        rChart.setId("unique");
        rChart.setLegendVisible(false);
        rChart.setStartAngle(90);
    }
    
    private ObservableList<PieChart.Data> createData(Project p) {
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(); 
        int nbRealisees = projet.getNbActivities(ActivityStatus.ENDED);
        int nbEnCours = projet.getNbActivities(ActivityStatus.WORKING);
        int nbPrevues = projet.getNbActivities(ActivityStatus.PLANNED);     
        PieChart.Data d1 = new PieChart.Data("Réalisées", nbRealisees);
        PieChart.Data d2 = new PieChart.Data("En cours", nbEnCours);
        PieChart.Data d3 = new PieChart.Data("Prévues", nbPrevues);
        pieData.addAll(d1, d2, d3);
        return pieData;     
    }
    
    private int getTotalActivities() {
        int cpt = projet.getActivities().size();
        return cpt;
    }
    
    private void initLabelValueActivities() {
        //init nom du projet
        labelNomProjet.setText(projet.getName());
        // init valeurs des activités
        labelValueTotalActivite.setText(String.valueOf(getTotalActivities()));
        labelValueRealisees.setText(String.valueOf(projet.getNbActivities(ActivityStatus.ENDED)));
        labelValueEnCours.setText(String.valueOf(projet.getNbActivities(ActivityStatus.WORKING)));
        labelValueAnnulees.setText(String.valueOf(projet.getNbActivities(ActivityStatus.CANCELED))); 
        labelValuePrevues.setText(String.valueOf(projet.getNbActivities(ActivityStatus.PLANNED)));      
    }  
}
