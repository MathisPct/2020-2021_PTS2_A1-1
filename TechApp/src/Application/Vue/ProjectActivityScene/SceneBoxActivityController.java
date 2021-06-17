/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.ProjectActivityScene;

import Application.Metier.Activity;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author David
 */
public class SceneBoxActivityController implements Initializable {
    
    private Activity activity;
    
    @FXML
    private TextArea tfResume;
    @FXML
    private Label lStatut;
    @FXML
    private Label lType;
    @FXML
    private DatePicker dpStart;
    @FXML
    private TextField tfDuration;
    @FXML
    private DatePicker dpEnd;
    
    
    public SceneBoxActivityController(Activity activity){
        this.activity = activity; 
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initFields();
    }
    
    private void initFields(){
        if(activity.getDetails() != null) {
            tfResume.setText(activity.getDetails());
        }
        if(activity.getStatus() != null) {
            lStatut.setText(activity.getStatusAsString());
        }
        if(activity.getType() != null) {
            lType.setText(activity.getType());
        }
//        if(activity.getStartDate()!= null) {
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
//            String strDate = formatter.format(activity.getStartDate());  
//            System.out.println("DATEeeeeeeeeeeeeeeeee :" + strDate);
//        }  
    }
    
    private void valueIfEmpty(String string){       
        String res = "";
        if(string != null){
            
        }
    }
    
}
