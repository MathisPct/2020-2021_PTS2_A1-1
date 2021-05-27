/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.projectsScene;


import Application.Database.ProjectDao;
import Application.Metier.Project;
import Application.Vue.customBox.MyCustomBoxes.MyCustomBox;
import Application.Vue.customBox.MyScrollPanes.MyScrollPaneProject;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.main.MainController;
import Application.Vue.projectsDetailsScene.SceneProjectsDetailsController;
import Application.Vue.projectsGraphs.projectsGraphActivity.SceneGraphActivityController;
import java.net.URL;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.image.ImageView;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author math7
 */
public class SceneProjectsController implements Initializable {
    
    private MyScrollPaneProject SPprojects;
    private ArrayList<Project> listProject;
    private MainController mainController;
    private Project projetActif;
    
    @FXML
    private Label labelTotalProjects;
    @FXML
    private Label label;    
    @FXML
    private ImageView appClose;
    @FXML
    private JFXButton projectsWindow;
    @FXML
    private JFXButton customers;
    @FXML
    private JFXButton techsWindow;
    @FXML
    private JFXButton techWindow;
    @FXML
    private JFXButton profileWindow;
    @FXML
    private JFXButton deconnectionButton;     
    @FXML
    private VBox containerProject;  
    @FXML
    private VBox paneDetailProject;
      
    public SceneProjectsController(MainController mainController) {
        this.mainController = mainController;
        this.SPprojects = null;
        this.projetActif = null;
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setListProject();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SceneProjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initScrollPaneProjects();              
    }

    public ArrayList<Project> getListProject() {
        return listProject;
    }
    
    public void setListProject() throws ClassNotFoundException, SQLException {
        ProjectDao projectDAO = new ProjectDao();     
        listProject = projectDAO.listAll();                 
    }
    
    public void initScrollPaneProjects(){
        MyStyle style = new MyStyleOrange("Carlito");
        containerProject.getChildren().clear();
        this.SPprojects = new MyScrollPaneProject(style, this, mainController, projetActif);
        initFields();
        containerProject.getChildren().add(this.SPprojects.scrollPaneProject());
        
    }
       
    public void setActionBoxProject(MyCustomBox boxProject, Project p) {
        boxProject.setOnMouseClicked((event) -> {
            this.projetActif = p;
            SPprojects.setProject(p);
            SPprojects.findBox();
            boxProject.openBox();       

            try {
                this.paneDetailProject.getChildren().clear();
                initDetailProject(p);
                initGraphActivity(p);
            } catch (IOException ex) {
                Logger.getLogger(SceneProjectsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void initDetailProject(Project p) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/Vue/projectsDetailsScene/sceneProjectsDetails.fxml"));
        SceneProjectsDetailsController controller = new SceneProjectsDetailsController(p,this);
        loader.setController(controller);
        VBox detailBox = loader.load();
        this.paneDetailProject.getChildren().add(detailBox);
    }
    
    public void initGraphActivity(Project p) throws IOException {       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/Vue/projectsGraphs/projectsGraphActivity/sceneGraphActivity.fxml"));
        SceneGraphActivityController controller = new SceneGraphActivityController(p, mainController);
        loader.setController(controller);
        VBox graphBox = loader.load();
        this.paneDetailProject.getChildren().add(graphBox);        
    }
    
    /**
     * Cette méthode initialise les champs fixes de l'IHM
     */
    public void initFields() {
        int totalProjects = this.SPprojects.getListProject().size();
        labelTotalProjects.setText(String.valueOf(totalProjects));
    }    
}
