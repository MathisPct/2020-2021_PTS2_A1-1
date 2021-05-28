/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.techsScene.TechSkillsScene.AddSkillScene;

import Application.Database.DaoError;
import Application.Database.UserDao;
import Application.Metier.Skill;
import Application.Metier.Tech;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;


/**
 *
 * @author David
 */
public class AddSkillWindow implements Initializable {
    
    private UserDao dao; //DAO
    private Tech tech;
    
    @FXML
    private JFXButton btnEnregistrer;
    @FXML
    private ComboBox<String> boxListSkills;
    @FXML
    private ComboBox<String> boxNiveau;

    @FXML
    void actionAddSkill(ActionEvent event) {
        int idSkill = boxListSkills.getSelectionModel().getSelectedIndex();
        String skillToAdd = boxListSkills.getItems().get(idSkill);
    }
    
    public AddSkillWindow(Tech tech){
        this.tech = tech;      
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initData();
            initComboBoxSkills();
            initComboBoxNiveau();
            
        } catch (SQLException | ClassNotFoundException | DaoError ex) {
            Logger.getLogger(AddSkillWindow.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    private void initComboBoxSkills() throws SQLException, DaoError{
        boxListSkills.getItems().clear();
        boxListSkills.getItems().addAll(dao.ListSkills()); 
        for (String skillFromDao : dao.ListSkills()) {  
            for (Skill skillFromTech : tech.GetSkills()) {
                //si le technicien possède déja cette compétence
                if( (skillFromTech.getName().equalsIgnoreCase(skillFromDao)) ){
                    //on la supprime de la box
                    boxListSkills.getItems().remove(skillFromDao);                
                }
            }
        }        
    }
    private void initComboBoxNiveau(){
        boxNiveau.getItems().addAll("simple", "intermediaire", "avancé");
    }
    

    private void initData() throws SQLException, ClassNotFoundException, DaoError {
        this.dao = new UserDao();
    }

}
