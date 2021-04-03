/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.projectsScene;

import Application.Metier.Project;
import Application.Metier.Tech;
import Application.Vue.customBox.MyCustomBox;
import Application.Vue.customBox.MyScrollPane;
import Application.Vue.customBox.MyStyle;
import java.net.URL;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.image.ImageView;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("SceneProjects");
        setListProject();
        scrollPaneProject();          
    }    
    
    @FXML
    public void loadSceneCustomers(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Application/Vue/customersScene/sceneCustomers.fxml"));
        Scene scene = customers.getScene();
    }
    
    @FXML
    public void loadSceneLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Application/Vue/customersScene/sceneLogin.fxml"));
        Scene scene = deconnectionButton.getScene();
    }
    
    public void scrollPaneProject() {
        System.out.println("Affiche des projets");
        MyStyle style = new MyStyle("ORANGE", "Carlito");
        MyScrollPane scrollProject = new MyScrollPane(style);
        initScrollPaneProject(listProject, scrollProject);
        this.containerProject.getChildren().add(scrollProject);
        //this.vboxLayoutSkills = new VBox(scrollTech.getSPStyle().getBoxSpacing());
    }
    
    public void initScrollPaneProject(ArrayList<Project> listProject, MyScrollPane SP) {        
        VBox vboxLayout = new VBox(SP.getSPStyle().getBoxSpacing());
        for (int i = 0; i < listProject.size(); i++) {         
            MyCustomBox boxProject = new MyCustomBox(SP, listProject.get(i), SP.getSPStyle());       
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
        });
    }
    
    public void setListProject() {
        Project p1 = new Project();
        Project p2 = new Project();
        Project p3 = new Project();
        Project p4 = new Project();
        Project p5 = new Project();
        listProject = new ArrayList();
        listProject.add(p1);
        listProject.add(p2);
        listProject.add(p3);
        listProject.add(p4);
        listProject.add(p5);
    }
    
    public void setProject(Project p) {
        this.project = p;
    }
}
