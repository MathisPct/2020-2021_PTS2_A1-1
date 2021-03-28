/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.Login;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Application.Database.DaoError;
import Application.Database.UserDao;
import Application.Main;
import Application.Metier.User;
import Application.Vue.UtilsIHM;
import Application.Vue.main.MainController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author David
 */
public class SceneLoginController implements Initializable {
    /**
     * Attribut chaine de caractère qui correspond au label "Identifiant" dans la scène
     */
    @FXML
    private TextField idLabel;

    /**
     * Attribut chaine de caractère qui correspond au label "Mot de passe" dans la scène
     */
    @FXML
    private TextField passwordLabel;

    private UserDao dao;

    private UtilsIHM utils = new UtilsIHM();

    /**
     * Utilisateur crée lors de la recherche d'un user dans la BDD avec read(login,mdp)
     */
    private User userConnected;
    
    /**
     * Attribut qui permet d'accèder au MainControler pour appeler des méthodes
     * de celui-ci
     */
    private MainController mainController;
    
    public SceneLoginController(User user) throws ClassNotFoundException, SQLException{
        this.userConnected = user;
        try{
            this.dao = new UserDao();
        }catch(SQLException eSQL){
            eSQL.printStackTrace();
            this.utils.afficherErreur(eSQL.getLocalizedMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("SceneLogin");
    }

    /**
     * l'utilisateur valide la saisie du login/du mot de passe (bouton Connexion)
     * Si la connexion est valide, il est redirigé vers la scène des projets
     */
    @FXML
    public void connect() throws SQLException, IOException{
        try{
            User user;
            System.out.println("Connexion");
            user = dao.Read(idLabel.getText(), passwordLabel.getText());
            user.setIsConnected(true);
            this.userConnected.Copy(user);
            //dès que l'utilisateur est connecté
            this.mainController.initFieldsUser();
            mainController.enableButtons();
            this.mainController.projects(); 
        }catch(DaoError e){
            e.printStackTrace();
            utils.afficherErreur(e.getLocalizedMessage());
        }
    }
    
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

}
