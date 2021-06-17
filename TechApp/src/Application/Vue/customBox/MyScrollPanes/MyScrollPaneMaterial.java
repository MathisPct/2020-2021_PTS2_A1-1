/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyScrollPanes;

import Application.Metier.Material;
import Application.Metier.Project;
import Application.Vue.ProjectMaterial.SceneBoxMaterialController;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Vue.customBox.MyStyles.MyStyleOrange;
import Application.Vue.main.MainController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author David
 */
public class MyScrollPaneMaterial extends MyScrollPane{
    
    private Project projet;
    private ArrayList<Material> listMaterial;

    public MyScrollPaneMaterial(MyStyle style, Project projet, MainController mainController) {
        super(style, mainController);
        this.projet = projet;
        this.listMaterial = new ArrayList<>();
    }
    
    public void scrollPane() {
        System.out.println("Scene Material");
        MyStyle style = new MyStyleOrange("Carlito");
        try {
            loadBox();
        } catch (IOException ex) {
            Logger.getLogger(MyScrollPaneActivities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadBox() throws IOException{
        VBox vboxLayout = new VBox(this.getScrollPaneStyle().getBoxSpacing()+10);       
        for (Material material : projet.getMaterials()) {         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/Vue/ProjectMaterial/ProjectMaterial.fxml"));
            SceneBoxMaterialController controller = new SceneBoxMaterialController(material);
            loader.setController(controller);
            
            AnchorPane box = loader.load();
            VBox.setVgrow(box, Priority.ALWAYS);
            System.out.println("add child");
            vboxLayout.getChildren().add(box); 
            
        }
        this.setContent(vboxLayout);
        this.setFitToWidth(true);
    }
    
}
