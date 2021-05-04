/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.projectsGraphs.projectsGraphActivity;

import Application.Metier.ActivityStatus;
import Application.Metier.Project;
import Application.Vue.CustomCharts.DoughnutChart;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    private int nbActiviteRealisees;
    private int nbActivitePrevues;
    private int nbActiviteEnCours;
    private int nbActiviteAnnulees;
    private int nbResteActivite;

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
    
    DoughnutChart rChart;
    
    /**
    * Projet dont on veut voir le détail (loadé grâce au bouton "voir détail")
    */
    private Project projet;
   
    
    public SceneGraphActivityController(Project p) {
        this.projet = p;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initValuesActivities();
        initLabelValueActivities();
        if(getTotalActivities()!=0) {
            initChart(projet);
        }
    }
    
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
        
        PieChart.Data d1 = new PieChart.Data("Réalisées", nbActiviteRealisees);
        PieChart.Data d2 = new PieChart.Data("En cours", nbActiviteEnCours);
        PieChart.Data d3 = new PieChart.Data("Prévues", nbActivitePrevues);
        //PieChart.Data d4 = new PieChart.Data("Annulées", nbActiviteAnnulees);
        //PieChart.Data d5 = new PieChart.Data("Reste", nbResteActivite);
        pieData.add(d1);
        pieData.add(d2);
        pieData.add(d3);
        //pieData.add(d4);
        //pieData.add(d5);
        return pieData;     
    }
    
    private int getTotalActivities() {
        int cpt = projet.getActivities().size();
        return cpt;
    }
    
    private void initLabelValueActivities() {
        labelNomProjet.setText(projet.getName());
        labelValueTotalActivite.setText(String.valueOf(getTotalActivities()));
        labelValueRealisees.setText(String.valueOf(nbActiviteRealisees));
        labelValueEnCours.setText(String.valueOf(nbActiviteEnCours));
        labelValueAnnulees.setText(String.valueOf(nbActiviteAnnulees)); 
        labelValuePrevues.setText(String.valueOf(nbActivitePrevues));      
    }
    
    private void initValuesActivities() {
        int cptRealise = 0;
        int cptEnCours = 0;
        int cptAnnule = 0;
        int cptPrevues = 0;
        int reste = 0;
        int cptTotal = getTotalActivities();
        
        for (int i = 0; i < projet.getActivities().size(); i++) {
            if(projet.getActivities().get(i).getStatus() == ActivityStatus.ENDED) {
                cptRealise += 1;
            }
            if(projet.getActivities().get(i).getStatus() == ActivityStatus.WORKING) {
                cptEnCours += 1;
            }
            if(projet.getActivities().get(i).getStatus() == ActivityStatus.PLANNED) {
                cptPrevues += 1;
            }
            if(projet.getActivities().get(i).getStatus() == ActivityStatus.CANCELED) {
                cptAnnule += 1;
            }
        }
        int sommeActivites = 0;
        reste = getTotalActivities() - cptPrevues + cptAnnule;
        nbActiviteRealisees = cptRealise;
        nbActivitePrevues = cptPrevues;
        nbActiviteEnCours = cptEnCours;
        nbActiviteAnnulees = cptAnnule;
        nbResteActivite = reste;      
    }
    
}
