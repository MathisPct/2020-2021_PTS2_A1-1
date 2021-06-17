/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.ProjectMaterial;

import Application.Metier.Activity;
import Application.Metier.Material;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author David
 */
public class SceneBoxMaterialController implements Initializable {
    
    private Material material;
    
    
    @FXML
    private Label lNom;

    @FXML
    private Label lType;

    @FXML
    private Label lQuantite;

    @FXML
    private Label lPrix;
    
    @FXML
    private Label lCommande;

    @FXML
    private Label lLivraison;
    
    public SceneBoxMaterialController(Material material){
        this.material = material; 
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initFields();
    }
    
        private void initFields(){

        if(material.getName()!= null) {
            lNom.setText(material.getName());
        }
        if(material.getPrice() != 0.0) {
            lPrix.setText(String.valueOf(material.getPrice()));
        }
        if(material.getType() != null) {
            lType.setText(String.valueOf(material.getType()));
        }
        
        if(material.getQuantity() != 0) {
            lQuantite.setText(String.valueOf(material.getQuantity()));
        }
        
        if(material.getDeliveryDate()!= null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
            String strDate = formatter.format(material.getDeliveryDate());  
            lLivraison.setText(strDate);
        }
        
        if(material.getOrderDate()!= null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
            String strDate = formatter.format(material.getOrderDate());  
            lCommande.setText(strDate);
        }
    }
    
    private void valueIfEmpty(String string){       
        String res = "";
        if(string != null){
            
        }
    }
    
}
