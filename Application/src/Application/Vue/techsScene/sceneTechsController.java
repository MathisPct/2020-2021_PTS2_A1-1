/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.techsScene;

import Application.Database.DaoError;
import Application.Database.UserDao;
import Application.Metier.Skill;
import Application.Metier.Tech;

import Application.Vue.CustomCharts.DoughnutChart;

import Application.Vue.UtilsIHM;

import Application.Vue.customBox.ItemHBox;
import Application.Vue.customBox.MyButton;
import Application.Vue.customBox.MyCustomBox;
import Application.Vue.customBox.MyRowBox;
import Application.Vue.customBox.MyScrollPane;
import Application.Vue.customBox.MyStyle;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


/**
 *
 * @author David
 */
public class sceneTechsController implements Initializable{

    private UserDao dao; //DAO
    private ArrayList<Tech> listTechs; // liste contenant les techniciens récupérés depuis la dao
    private MyScrollPane scrollSkills;
    private Tech tech; 
    @FXML
    private VBox containerTech;  
    @FXML
    private VBox containerSkills;  
    /**
     * Liste qui contient les skills possibles pour les techniciens afin d'effectuer
     * des recherches par skill dans la bdd
     */
    @FXML
    private ComboBox<String> comboBoxSkills = new ComboBox<>();
    
    /**
     * Label qui indique le nombre de techniciens à l'utilisateur
     */
    @FXML
    private Label totalTechs;
    
    @FXML
    private Label skillTechName;
    @FXML
    private Label GraphskillTechName;
    @FXML
    private Label skillTechInfo; 
    @FXML
    private VBox containerGraph; 
    @FXML
    private PieChart chartSkills = new PieChart();
    
    private DoughnutChart rChart;
    
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
    
    public void setTech(Tech tech) {
        this.tech = tech;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initData();
        } catch (SQLException ex) {
            Logger.getLogger(sceneTechsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sceneTechsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.tech = null;
        this.scrollSkills = null;
        this.skillTechInfo.setText("Choisissez un technicien pour afficher ses compétences");
        this.skillTechName.setText("");
        scrollPaneTech();  
    }

    /**
     * Initialise les données de la fenêtre tels que la liste des techniciens
     * et la liste des skills contenues dans la comboBox
     * @throws SQLException
     * @throws ClassNotFoundException
     * @autor Mathis Poncet
     */
    private void initData() throws SQLException, ClassNotFoundException {
        this.dao = new UserDao();
        this.listTechs = new ArrayList<>();
        try {
            this.listTechs.addAll(this.dao.ListTechs(""));
            initNbTechs(); 
            initComboBox();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**

     * Cette fonction créé le scrollpane des techniciens en lui assignant un style et en définissant sa boite parente
     * à savoir containerTech.
     * Initialise la comboBox avec les compétences qui peuvent appartenir aux
     * techniciens de la BDD
     * @throws SQLException 
     */
    private void initComboBox() throws SQLException{
        try{
            this.comboBoxSkills.getItems().add("Tous les skills");
            this.comboBoxSkills.getItems().addAll(this.dao.ListSkills());
        }catch(DaoError dao){
            dao.printStackTrace();
            UtilsIHM.afficherErreur(dao.getLocalizedMessage());
        }
    }
    
    /**
     * Initialise le champ totalTechs qui indique le nombre de techniciens
     */
    private void initNbTechs(){
        this.totalTechs.setText(String.valueOf(this.listTechs.size()));
    }
    
    /**
     * Lance la recherche par skill lorsqu'un élément est choisi dans 
     * comboBoxSkills
     */
    @FXML
    public void searchTechsSkill() throws SQLException, DaoError{
        String skillChoice = comboBoxSkills.getValue();
        //si tous les skills sont choisis
        if(comboBoxSkills.getSelectionModel().getSelectedIndex() == 0){
            skillChoice = "";
        }
        System.out.println(skillChoice);
        try{
            this.listTechs.clear();
            this.listTechs.addAll(dao.ListTechs(skillChoice));
            initNbTechs(); //initialise le nombre de techniciens trouvés
            this.containerTech.getChildren().clear();
            this.containerSkills.getChildren().clear();
            scrollPaneTech();
            this.skillTechInfo.setText("Choisissez un technicien pour afficher ses compétences");
            this.skillTechName.setText("");
        }catch(SQLException eSQL){
            eSQL.printStackTrace();
            UtilsIHM.afficherErreur(eSQL.getLocalizedMessage());
        }catch(DaoError eDao){
            eDao.printStackTrace();
            UtilsIHM.afficherErreur(eDao.getLocalizedMessage());
            comboBoxSkills.getSelectionModel().select(0);
        }
    }
    

    public void scrollPaneTech() {
            System.out.println("SceneTechs");
            MyStyle style = new MyStyle("ORANGE", "Carlito");
            MyScrollPane scrollTech = new MyScrollPane(style);
            initScrollPaneTech(listTechs, scrollTech, this.containerSkills);
            this.containerTech.getChildren().add(scrollTech);
            this.scrollSkills = new MyScrollPane(scrollTech.getSPStyle());
            //this.vboxLayoutSkills = new VBox(scrollTech.getSPStyle().getBoxSpacing());
    }
    
    /**
     * Cette fonction génère les box de techniciens dans le scrollPane des techniciens à partir d'une liste de technicien
     * @param listTech liste de technicien dont il faudra générer les box
     * @param SP scrollPane qui contient les boites 
     * @param boxSkills utile pour définir l'action de clic sur une boite
     */
    public void initScrollPaneTech(ArrayList<Tech> listTech, MyScrollPane SP, VBox boxSkills) {        
        VBox vboxLayout = new VBox(SP.getSPStyle().getBoxSpacing());
        for (int i = 0; i < listTech.size(); i++) {         
            MyCustomBox boxTech = new MyCustomBox(SP, listTech.get(i), SP.getSPStyle());       
            setActionBoxTech(SP, boxTech, listTech.get(i), boxSkills);//Evenement boite principale          
            boxTech.addRowBoxListItem(SP.generateMainTechRow(listTech.get(i))); //ajout de la bôite de titre
            boxTech.addRowBoxListItem(SP.generateItemBoxTech(listTech.get(i))); //ajout de la boite d'item
            boxTech.initBox();//initialisation de la boite principale
            SP.getCustomBoxList().add(boxTech);//remplissage de la liste de customBox avec l'instance actuellement générée      
            MyCustomBox.setVgrow(boxTech, Priority.ALWAYS);
            vboxLayout.getChildren().add(boxTech);
        }      
        SP.setContent(vboxLayout);
        SP.setFitToWidth(true);
    }   
    
    /**
     * Cette fonction défini l'action à effectuer quand une boite de technicien est cliqué
     * @param SP
     * @param boxTech
     * @param tech
     * @param containerSkills 
     */
    public void setActionBoxTech(MyScrollPane SP, MyCustomBox boxTech, Tech tech, VBox containerSkills) {
        boxTech.setOnMouseClicked((event) -> {
            System.out.println("Instance clicked");
            SP.findBox(boxTech);
            boxTech.openBox();
            setTech(tech);
            this.skillTechInfo.setText("Compétences de ");
            setTechLabelName(tech);
            this.containerSkills.getChildren().clear();          
            initScrollPaneSkill(tech);
        });
    }
    
    /**
     * Cette fonction créé le scrollpane des skills en lui assignant un style et en définissant sa boite parente
     * à savoir containerSkill.
     * @param tech 
     */
    public void scrollPaneSkill(Tech tech) {
        try {
            initData();
            System.out.println("SceneSkill");
            MyStyle style = new MyStyle("BLEU", "Carlito");
            this.scrollSkills = new MyScrollPane(style);
            initScrollPaneSkill(tech);
            this.containerSkills.getChildren().add(this.scrollSkills);
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    } 

    /**
     * Cette fonction génère les box de skills d'un technicien passé en paramètre dans le scrollPane de skills
     * @param tech 
     */
    public void initScrollPaneSkill(Tech tech) {
        this.containerSkills.getChildren().clear();
        this.scrollSkills.setContent(null);     

        VBox vboxLayout = new VBox(this.scrollSkills.getSPStyle().getBoxSpacing());

        for (int i = 0; i < tech.GetSkills().size(); i++) {
            MyRowBox skillRow = new MyRowBox("", "", this.scrollSkills.getSPStyle());
            skillRow.generateSkillGridPane(tech, tech.GetSkills().get(i));
            vboxLayout.getChildren().add(skillRow);
        }
        this.scrollSkills.setContent(vboxLayout);   
        this.scrollSkills.setFitToWidth(true);
        this.containerSkills.getChildren().add(this.scrollSkills);
        initChart(tech);
    }
    
    /**
     * Cette fonction sert à définir le nom de technicien à afficher.
     * @param tech le technicien à afficher dasn le label
     */
    public void setTechLabelName(Tech tech) {
        String techName = tech.getFirstName() + " " + tech.getLastName();
        this.skillTechName.setText(techName);
        this.GraphskillTechName.setText(techName);
    }
    
    private void initChart(Tech tech){
        containerGraph.getChildren().remove(rChart);
        rChart = new DoughnutChart(createData(tech));
        containerGraph.getChildren().add(rChart);
        chartSkills.setVisible(true);
        //chartSkills.setTitle("Compétences de " + tech.toString());
        chartSkills.getData().clear();
        chartSkills.setLabelLineLength(20);
        //chartSkills.setStartAngle(0);
        rChart.setAnimated(true);
        rChart.getStyleClass().add("unique");
        rChart.setId("unique");
        rChart.setLegendVisible(false);

    }
}
