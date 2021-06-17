/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.ProjectMaterial;

import Application.Metier.Activity;
import Application.Metier.Material;
import Application.Metier.Project;
import Application.Vue.customBox.MyScrollPanes.MyScrollPaneActivities;
import Application.Vue.customBox.MyScrollPanes.MyScrollPaneMaterial;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.main.MainController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 *
 * @author David
 */
public class SceneProjectMaterialController implements Initializable {
    
    private MainController mainController;
    private MyScrollPaneMaterial SPmaterial;
    private Project project;
    private ArrayList<Material> listMaterials;
    
    @FXML
    private VBox containerMaterial;

    public SceneProjectMaterialController(MainController mainController, Project projet){
        this.mainController = mainController;
        this.project = projet;
        this.listMaterials = new ArrayList<>();
        this.SPmaterial = null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListMaterial();
        MyStyle style = new MyStyleOrange("Carlito");
        this.SPmaterial = new MyScrollPaneMaterial(style, project, mainController);
        this.SPmaterial.scrollPane();
        this.containerMaterial.getChildren().add(this.SPmaterial);
    }
    
    public void setListMaterial() {
        for (Material material : project.getMaterials()) {
            this.listMaterials.add(material);
            System.out.println("Material: " + material.toString() );
        }
    }
    
}
