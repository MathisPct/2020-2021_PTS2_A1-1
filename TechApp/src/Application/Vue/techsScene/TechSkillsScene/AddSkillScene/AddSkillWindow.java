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
import Application.Vue.UtilsIHM;
import Application.Vue.techsScene.TechSkillsScene.SceneTechSkillsController;
import Application.Vue.techsScene.sceneTechsController;
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
import javafx.stage.Stage;


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
    /**
     * Pour mettre à jour les techniciens quand on ajoute un skill
     */
    private sceneTechsController techsController;
    
    /**
     * La fenêtre de l'ajout des skills auquel la scène fxml est ratachée
     */
    private Stage stage;
    
    /**
     * Pour pouvoir  mettre à jour les skills du technicien 
     */
    private SceneTechSkillsController skillsController;
    
    /**
     * Constructeur par initialisation
     * @param tech
     * @param parentController
     * @throws SQLException
     * @throws DaoError
     * @throws ClassNotFoundException 
     */
    public AddSkillWindow(Tech tech, sceneTechsController techsController) throws SQLException, DaoError, ClassNotFoundException{
        this.tech = tech; 
        this.techsController = techsController;
        try{
            initData();
        }catch(DaoError e){
            e.printStackTrace();
            UtilsIHM.afficherErreur(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initComboBoxSkills();
            initComboBoxNiveau();     
        } catch (SQLException | DaoError ex) {
            ex.printStackTrace();
            UtilsIHM.afficherErreur(ex.getMessage());
        }       
    }
    
    /**
     * Permet d'enregistrer et d'ajouter le skill au technicien choisi 
     * @throws java.lang.Exception est levé si les informations de l'ajout d'un
     * skill ne sont pas complète
     */
    @FXML
    public void actionAddSkill() throws Exception {
        int idSkill = boxListSkills.getSelectionModel().getSelectedIndex();
        int idNiveau = boxNiveau.getSelectionModel().getSelectedIndex();
        try{
            if(idSkill == -1 || idNiveau == -1 ){
                throw new Exception("Vous n'avez pas séléctionné toutes les informations pour ajouter un skill");
            }
            String nomSkill = boxListSkills.getItems().get(idSkill); 
            String niveauSkill = boxNiveau.getItems().get(idNiveau);
            Skill skill = new Skill();
            skill.setName(nomSkill);
            skill.setLevel(niveauSkill);
            this.dao.ajoutSkill(this.tech, skill); //on ajoute à la bdd
            this.tech.AddSkill(skill); //on ajoute au modèle
            //refresh de la page
            techsController.initScrollPaneTechs();
            this.skillsController.initScrollPaneSkills();
            stage.close();
        }catch(Exception e){
            e.printStackTrace();
            UtilsIHM.afficherErreur(e.getLocalizedMessage());
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setSkillsController(SceneTechSkillsController skillC){
        this.skillsController = skillC;
    }
}
