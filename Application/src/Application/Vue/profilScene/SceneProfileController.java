/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.profilScene;

import Application.Database.BadPasswordError;
import Application.Database.DaoError;
import Application.Database.UserDao;
import Application.Metier.User;
import Application.Metier.Utils;
import Application.Vue.main.MainController;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import Application.Vue.UtilsIHM;
import java.io.IOException;

/**
 *
 * @author David
 */
public class SceneProfileController implements Initializable {
    
    /**
     * Attribut chaine de caractère qui correspond au label "Nom" dans la scène
     */
    @FXML
    private TextField nomLabel;

    /**
     * Attribut chaine de caractère qui correspond au label "Prénom" dans la scène
     */
    @FXML
    private TextField prenomLabel;
    
    /**
     * Attribut chaine de caractère qui correspond au label "Mot de passe" dans la scène
     */
    @FXML
    private TextField passwordLabel;
    
    /**
     * Attribut chaine de caractère qui correspond au label "Mot de passe (2)" dans la scène
     */
    @FXML
    private TextField passwordVerifLabel;
    

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
    
    public SceneProfileController(User user, MainController mainController) throws ClassNotFoundException, SQLException{
        this.userConnected = user;
        this.mainController = mainController;
        try{
            this.dao = new UserDao();
        }
        catch(SQLException eSQL)
        {
            eSQL.printStackTrace();
            this.utils.afficherErreur(eSQL.getLocalizedMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("SceneProfil");
        this.nomLabel.setText(userConnected.getLastName());
        this.prenomLabel.setText(userConnected.getFirstName());
    } 
    
    public void validate() throws SQLException, IOException{
        try{
            System.out.println("Enregistrer");
            if (!passwordLabel.getText().equals(passwordVerifLabel.getText()) ){
                throw new BadPasswordError("Les 2 mots de passes ne sont pas les mêmes");
            }   
            else if (!Utils.IsPasswordSafe(passwordLabel.getText())){
                throw new BadPasswordError("Le mot de passe n'est pas assez sécurisé");
            }
            else{
                
                this.userConnected.setFirstName(prenomLabel.getText());
                this.userConnected.setLastName(nomLabel.getText());
                this.userConnected.setPasswordHash(Utils.HashPassword(passwordLabel.getText()));
                dao.Update(userConnected);
                this.mainController.initFieldsUser();
                this.mainController.projects();
                
            }
        }catch(BadPasswordError e){
            e.printStackTrace();
            utils.afficherErreur(e.getLocalizedMessage());
        }catch(IOException ex){
            ex.printStackTrace();
            utils.afficherErreur("Impossible de charger la page Projet");
        }catch(SQLException ex){
            ex.printStackTrace();
            utils.afficherErreur("Impossible d'update l'user");
        }
    }
}