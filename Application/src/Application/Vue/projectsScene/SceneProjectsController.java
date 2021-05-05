/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.projectsScene;

import Application.Database.ProjectDao;
import Application.Metier.Project;
import Application.Vue.customBox.MyCustomBox;
import Application.Vue.customBox.MyScrollPane;
import Application.Vue.customBox.MyStyle;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author math7
 */
public class SceneProjectsController implements Initializable {
    private ArrayList<Project> listProject; // liste contenant les project récupérés depuis la dao
    private Project project;
    
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
    
    private MainController mainController;
    
    public SceneProjectsController(MainController mainController) {
        this.mainController = mainController;
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("SceneProjects");
        try {
            setListProject();
            initFields();
            scrollPaneProject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SceneProjectsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SceneProjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
    
    public void scrollPaneProject() {
        System.out.println("Affiche des projets");
        MyStyle style = new MyStyle("ORANGE", "Carlito");
        MyScrollPane scrollProject = new MyScrollPane(style, mainController);
        initScrollPaneProject(listProject, scrollProject);
        this.containerProject.getChildren().add(scrollProject);
        //this.vboxLayoutSkills = new VBox(scrollTech.getSPStyle().getBoxSpacing());
    }
    
    public void initScrollPaneProject(ArrayList<Project> listProject, MyScrollPane SP) {        
        VBox vboxLayout = new VBox(SP.getScrollPaneStyle().getBoxSpacing());
        for (int i = 0; i < listProject.size(); i++) {         
            MyCustomBox boxProject = new MyCustomBox(SP, listProject.get(i), SP.getScrollPaneStyle());       
            setActionBoxProject(SP, boxProject, listProject.get(i));//Evenement boite principale          
            boxProject.addRowBoxListItem(SP.generateMainProjectRow(listProject.get(i))); //ajout de la bôite de titre
            boxProject.addRowBoxListItem(SP.generateItemBoxProject(listProject.get(i))); //ajout de la boite d'item
            boxProject.initBox();//initialisation de la boite principale
            SP.getCustomBoxList().add(boxProject);//remplissage de la liste de customBox avec l'instance actuellement générée      
            MyCustomBox.setVgrow(boxProject, Priority.ALWAYS);
            vboxLayout.getChildren().add(boxProject);
        }      
        SP.setContent(vboxLayout);
        SP.setFitToWidth(true);
    }   
       
    public void setActionBoxProject(MyScrollPane SP, MyCustomBox boxProject, Project p) {
        boxProject.setOnMouseClicked((event) -> {
            System.out.println("Instance clicked");
            SP.findBox(boxProject);
            boxProject.openBox();
            setProject(p);
            try {
                this.paneDetailProject.getChildren().clear();
                initDetailProject(p);
                initGraphActivity(p);
            } catch (IOException ex) {
                Logger.getLogger(SceneProjectsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void setListProject() throws ClassNotFoundException, SQLException {
        ProjectDao projectDAO = new ProjectDao();
        
        listProject = projectDAO.listAll();
    }
    
    public void setProject(Project p) {
        this.project = p;
    }
    
    public void initDetailProject(Project p) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/Vue/projectsDetailsScene/sceneProjectsDetails.fxml"));
        SceneProjectsDetailsController controller = new SceneProjectsDetailsController(p);
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
        int totalProjects = listProject.size();
        labelTotalProjects.setText(String.valueOf(totalProjects));
    }
    
}
