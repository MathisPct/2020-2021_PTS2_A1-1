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

import Application.Vue.Style.CustomCharts.DoughnutChart;

import Application.Vue.UtilsIHM;

import Application.Vue.customBox.MyItemBoxes.ItemHBox;
import Application.Vue.customBox.MyButtons.MyButton;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBox;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBoxTech;
import Application.Vue.customBox.MyRowBox;
import Application.Vue.customBox.MyScrollPanes.MyScrollPane;
import Application.Vue.customBox.MyScrollPanes.MyScrollPaneSkills;
import Application.Vue.customBox.MyScrollPanes.MyScrollPaneTech;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.main.MainController;
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
    private ArrayList<Tech> listTechs; // liste contenant les project récupérés depuis la dao
    private ArrayList<Skill> listSkills;
    private MyScrollPaneSkills SPskills; 
    private MyScrollPaneTech SPtechs;
    
    
    @FXML
    private VBox containerTech;  
 
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
    private MainController mainController;
    
    public sceneTechsController(MainController mainController) {
        this.mainController = mainController;
        this.SPtechs = null;
        this.SPskills = null;
        this.listTechs = new ArrayList<>(); 
        this.listSkills = new ArrayList<>();      
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            MyStyle style = new MyStyleOrange("Carlito");
            this.SPtechs = new MyScrollPaneTech(style, this, this.mainController);
            initData();
        } catch (SQLException ex) {
            Logger.getLogger(sceneTechsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sceneTechsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SPtechs.scrollPaneTech();
        containerTech.getChildren().add(SPtechs);
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
            this.containerTech.getChildren().clear();
            //this.containerSkills.getChildren().clear();
            SPtechs.scrollPaneTech();
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
    

//    public void scrollPaneTech() {
//            System.out.println("SceneTechs");
//            MyStyle style = new MyStyleOrange("Carlito");
//            MyScrollPane scrollTech = new MyScrollPane(style, mainController);
//            initScrollPaneTech(listTechs, scrollTech, this.containerSkills);
//            this.containerTech.getChildren().add(scrollTech);
//            this.scrollSkills = new MyScrollPane(scrollTech.getScrollPaneStyle(), mainController);
//            //this.vboxLayoutSkills = new VBox(scrollTech.getSPStyle().getBoxSpacing());
//    }
    
//    /**
//     * Cette fonction génère les box de techniciens dans le scrollPane des techniciens à partir d'une liste de technicien
//     * @param listTech liste de technicien dont il faudra générer les box
//     * @param SP scrollPane qui contient les boites 
//     * @param boxSkills utile pour définir l'action de clic sur une boite
//     */
//    public void initScrollPaneTech(ArrayList<Tech> listTech, MyScrollPane SP, VBox boxSkills) {        
//        VBox vboxLayout = new VBox(SP.getScrollPaneStyle().getBoxSpacing());
//        for (int i = 0; i < listTech.size(); i++) {         
//            MyCustomBox boxTech = new MyCustomBoxTech(listTech.get(i), SP.getScrollPaneStyle());       
//            setActionBoxTech(SP, boxTech, listTech.get(i), boxSkills);//Evenement boite principale          
//            boxTech.addRowBoxListItem(SP.generateMainTechRow(listTech.get(i))); //ajout de la bôite de titre
//            boxTech.addRowBoxListItem(SP.generateItemBoxTech(listTech.get(i))); //ajout de la boite d'item
//            boxTech.initBox();//initialisation de la boite principale
//            SP.getCustomBoxList().add(boxTech);//remplissage de la liste de customBox avec l'instance actuellement générée      
//            MyCustomBox.setVgrow(boxTech, Priority.ALWAYS);
//            vboxLayout.getChildren().add(boxTech);
//        }      
//        SP.setContent(vboxLayout);
//        SP.setFitToWidth(true);
//    }   
    
    /**
     * Cette fonction défini l'action à effectuer quand une boite de technicien est cliqué
     * @param SP
     * @param boxTech
     * @param tech
     * @param containerSkills 
     */
    public void setActionBoxTech(MyCustomBox boxTech, Tech tech) {
        boxTech.setOnMouseClicked((event) -> {
            System.out.println("Instance clicked");
            SPtechs.findBox(boxTech);
            boxTech.openBox();
            //setTech(tech);
            this.skillTechInfo.setText("Compétences de ");
            setTechLabelName(tech);
            //this.containerSkills.getChildren().clear();          
            //initScrollPaneSkill(tech);
        });
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public ArrayList<Tech> getListTechs() {
        return this.listTechs;
    }
    
    /**
     * @return la boite déroulante de selection de skills
     */
    public ComboBox<String> getComboBoxSkills() {
        return this.comboBoxSkills;
    }
    
    /**
     * @return le label affichant le nombre de technciens
     */
    public Label getLabelTotalTechs(){
        return this.totalTechs;
    }
}
