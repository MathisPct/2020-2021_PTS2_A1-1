/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.techsScene;

import Application.Database.UserDao;
import Application.Metier.Tech;
import Application.Vue.customBox.ItemHBox;
import Application.Vue.customBox.MyButton;
import Application.Vue.customBox.MyCustomBox;
import Application.Vue.customBox.MyRowBox;
import Application.Vue.customBox.MyScrollPane;
import Application.Vue.customBox.MyStyle;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


/**
 *
 * @author David
 */
public class sceneTechsController implements Initializable{

    private UserDao dao; //DAO
    private ArrayList<Tech> listTechs; // liste contenant les techniciens récupérés depuis la dao
    private MyScrollPane scrollSkills;
    private Tech tech; 
    @FXML
    private VBox containerTech;  
    @FXML
    private VBox containerSkills;  
    @FXML
    private Label skillTechName;
    @FXML
    private Label skillTechInfo;
    
    public void setTech(Tech tech) {
        this.tech = tech;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initData();
        } catch (SQLException ex) {
            Logger.getLogger(sceneTechsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sceneTechsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.tech = null;
        this.scrollSkills = null;
        this.skillTechInfo.setText("Choisissez un technicien pour afficher ses compétences");
        this.skillTechName.setText("");
        scrollPaneTech();  
    }

    /**
     * Initialise la liste des données des techniciens
     * @throws SQLException
     * @throws ClassNotFoundException
     * @autor Mathis Poncet
     */
    private void initData() throws SQLException, ClassNotFoundException {
        this.dao = new UserDao();
        this.listTechs = new ArrayList<>();
        try {
            this.listTechs.addAll(this.dao.ListTechs());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void scrollPaneTech() {
            System.out.println("SceneTechs");
            MyStyle style = new MyStyle("ORANGE", "Carlito");
            MyScrollPane scrollTech = new MyScrollPane(style);
            initScrollPaneTech(listTechs, scrollTech, this.containerSkills);
            this.containerTech.getChildren().add(scrollTech);
            this.scrollSkills = new MyScrollPane(scrollTech.getSPStyle());
            //this.vboxLayoutSkills = new VBox(scrollTech.getSPStyle().getBoxSpacing());
    }
    
    public void initScrollPaneTech(ArrayList<Tech> listTech, MyScrollPane SP, VBox boxSkills) {        
        VBox vboxLayout = new VBox(SP.getSPStyle().getBoxSpacing());
        for (int i = 0; i < listTech.size(); i++) {         
            MyCustomBox boxTech = new MyCustomBox(SP, listTech.get(i), SP.getSPStyle());       
            setActionBoxTech(SP, boxTech, listTech.get(i), boxSkills);//Evenement boite principale          
            boxTech.addRowBoxListItem(SP.generateMainTechRow(listTech.get(i))); //ajout de la bôite de titre
            boxTech.addRowBoxListItem(SP.generateItemBoxTech(listTech.get(i))); //ajout de la boite d'item
            boxTech.initBox();//initialisation de la boite principale
            SP.getCustomBoxList().add(boxTech);//remplissage de la liste de customBox avec l'instance actuellement générée      
            MyCustomBox.setVgrow(boxTech, Priority.ALWAYS);
            vboxLayout.getChildren().add(boxTech);
        }      
        SP.setContent(vboxLayout);
        SP.setFitToWidth(true);
    }   
       
    public void setActionBoxTech(MyScrollPane SP, MyCustomBox boxTech, Tech tech, VBox containerSkills) {
        boxTech.setOnMouseClicked((event) -> {
            System.out.println("Instance clicked");
            SP.findBox(boxTech);
            boxTech.openBox();
            setTech(tech);
            this.skillTechInfo.setText("Compétences de ");
            setTechLabelName(tech);
            this.containerSkills.getChildren().clear();          
            initScrollPaneSkill(tech);
        });
    }
    
    public void scrollPaneSkill(Tech tech) {
        try {
            initData();
            System.out.println("SceneSkill");
            MyStyle style = new MyStyle("BLEU", "Carlito");
            this.scrollSkills = new MyScrollPane(style);
            initScrollPaneSkill(tech);
            this.containerSkills.getChildren().add(this.scrollSkills);
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    } 

    public void initScrollPaneSkill(Tech tech) {
        this.containerSkills.getChildren().clear();
        this.scrollSkills.setContent(null);
        
        VBox vboxLayout = new VBox(this.scrollSkills.getSPStyle().getBoxSpacing());
        
        /*
        for (int i = 0; i < tech.GetSkills().size(); i++) {          
            //création de la boite principale
            MyCustomBox boxSkill = new MyCustomBox(this.scrollSkills, tech, this.scrollSkills.getSPStyle());       
            // Génération de la ligne du nom du technicien
            String skillName = tech.GetSkills().get(i).getName();
            String skillLevel = tech.GetSkills().get(i).getLevel();
            ArrayList<ItemHBox> itemList = new ArrayList();
            ItemHBox itemName = new ItemHBox(skillName, "", this.scrollSkills.getSPStyle());
            ItemHBox itemLevel = new ItemHBox("Niveau: ",skillLevel, this.scrollSkills.getSPStyle());
            itemList.add(itemName);
            itemList.add(itemLevel);
            MyRowBox mainSkillRow = new MyRowBox("", "", this.scrollSkills.getSPStyle());
            mainSkillRow.setStyle("-fx-background-color: " + this.scrollSkills.getSPStyle().getColorBase());
            mainSkillRow.generateItemHBoxRow(itemList, Priority.ALWAYS);
            MyButton btn = new MyButton("Modifier", this.scrollSkills.getSPStyle());
            btn.addIconButton("MODIFIER");
            mainSkillRow.addButtonToRowBox(btn);
            // ajout des boites secondaires à la boite principale
            boxSkill.addRowBoxListItem(mainSkillRow); //ajout de la bôite de titre
            boxSkill.initBox(); // initialisation de la boite principale      
            MyCustomBox.setVgrow(boxSkill, Priority.ALWAYS);
            vboxLayout.getChildren().add(boxSkill);      
        }
        this.scrollSkills.setContent(vboxLayout);   
        this.scrollSkills.setFitToWidth(true);
        this.containerSkills.getChildren().add(this.scrollSkills);
        */
        
        for (int i = 0; i < tech.GetSkills().size(); i++) {
            MyRowBox skillRow = new MyRowBox("", "", this.scrollSkills.getSPStyle());
            skillRow.generateSkillGridPane(tech, tech.GetSkills().get(i));
            vboxLayout.getChildren().add(skillRow);
        }
        this.scrollSkills.setContent(vboxLayout);   
        this.scrollSkills.setFitToWidth(true);
        this.containerSkills.getChildren().add(this.scrollSkills);
        

    }

    public void setTechLabelName(Tech tech) {
        String techName = tech.getFirstName() + " " + tech.getLastName();
        this.skillTechName.setText(techName);
    }
}
