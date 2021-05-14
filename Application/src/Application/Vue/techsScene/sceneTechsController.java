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
import Application.Vue.UtilsIHM;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBox;
import Application.Vue.customBox.MyScrollPanes.MyScrollPaneSkills;
import Application.Vue.customBox.MyScrollPanes.MyScrollPaneTech;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.main.MainController;
import Application.Vue.techsScene.TechSkillsScene.SceneTechSkillsController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;


/**
 *
 * @author David
 */
public class sceneTechsController implements Initializable{

    private UserDao dao; //DAO
    private ArrayList<Tech> listTechs;
    private ArrayList<Skill> listSkills;
    private MyScrollPaneSkills SPskills; 
    private MyScrollPaneTech SPtechs;
    private MainController mainController;
    
    // ATTRIBUTS IHM
    @FXML
    private SplitPane splitPaneContainer;
    @FXML
    private VBox vboxTechs;
    @FXML
    private VBox containerTech;  
    /**
     * Liste qui contient les skills possibles
     * pour les techniciens afin d'effectuer
     * des recherches par skill dans la bdd
     */
    @FXML
    private ComboBox<String> comboBoxSkills = new ComboBox<>();
    @FXML
    private Label totalTechs;  
    @FXML
    private Label skillTechName;
    @FXML
    private Label GraphskillTechName; 
    @FXML
    private VBox containerGraph; 
    @FXML
    private PieChart chartSkills = new PieChart();

    
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
            initComboBox();
        } catch (SQLException | ClassNotFoundException | DaoError ex) {
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
            SPtechs.scrollPaneTech();         
        }catch(SQLException eSQL){
            UtilsIHM.afficherErreur(eSQL.getLocalizedMessage());
        }catch(DaoError eDao){
            UtilsIHM.afficherErreur(eDao.getLocalizedMessage());
            comboBoxSkills.getSelectionModel().select(0);
        }
    }
      
    /**
     * Cette fonction défini l'action à effectuer quand une boite de technicien est cliqué
     * @param boxTech
     * @param tech
     */
    public void setActionBoxTech(MyCustomBox boxTech, Tech tech) {
        boxTech.setOnMouseClicked((event) -> {
            System.out.println("Instance clicked");
            SPtechs.findBox(boxTech);
            boxTech.openBox();
            SPtechs.setTech(tech);
            try {
                viewTechSkills();
            } catch (IOException ex) {
                Logger.getLogger(sceneTechsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
 
    /**
     * Cette fonction créé le scrollpane des techniciens en lui assignant un style et en définissant sa boite parente
     * à savoir containerTech.
     * Initialise la comboBox avec les compétences qui peuvent appartenir aux
     * techniciens de la BDD
     * @throws SQLException 
     */
    private void initComboBox() throws SQLException, DaoError{
        comboBoxSkills.getItems().add("Tous les skills");
        comboBoxSkills.getItems().addAll(dao.ListSkills());
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
        }catch (DaoError | SQLException e){
            e.printStackTrace();
        }
    }
    
    public void viewTechSkills() throws IOException{
        initSplitPaneContainer();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClass().getResource("/Application/Vue/techsScene/TechSkillsScene/SceneTechSkills.fxml"));
        SceneTechSkillsController controller = new SceneTechSkillsController(SPtechs.getTech(), mainController);
        fxmlLoader.setController(controller);
        this.splitPaneContainer.getItems().add(fxmlLoader.load());
    }
    
    public void initSplitPaneContainer(){
        this.splitPaneContainer.getItems().clear();
        this.splitPaneContainer.getItems().addAll(this.vboxTechs);
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
