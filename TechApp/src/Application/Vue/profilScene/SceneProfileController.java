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
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.io.IOException;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
     * Attribut chaine de caractère qui correspond au label "Identifiant" dans 
     * le fichier FXML associé
     */
    @FXML
    private TextField loginLabel;
    
    /**
     * Attribut chaine de caractère qui correspond au label "Mot de passe" dans la scène
     */
    @FXML
    private TextField passwordLabel;
    
    /**
     * Attribut chaine de caractère qui correspond au label "Mot de passe (2)" dans la scène
     */
    @FXML
    private PasswordField passwordVerifLabel;
    
    
    @FXML
    private PasswordField unvisiblePasswordLabel;
    
    @FXML
    private ImageView visibilityIcon;
    

    private UserDao dao;


    /**
     * Utilisateur crée lors de la recherche d'un user dans la BDD avec read(login,mdp)
     */
    private User userConnected;
    
    /**
     * Attribut qui permet d'accèder au MainControler pour appeler des méthodes
     * de celui-ci
     */
    private MainController mainController;
    
    @FXML
    void checkVisibility(MouseEvent event) {
        System.out.println("VISIBILITY CLICKED");
        if(!passwordLabel.isVisible()) {
            passwordLabel.setText(unvisiblePasswordLabel.getText());
            passwordLabel.setVisible(true);
            unvisiblePasswordLabel.setVisible(false);
            visibilityIcon.setVisible(false);
        } else {
            unvisiblePasswordLabel.setText(passwordLabel.getText());
            unvisiblePasswordLabel.setVisible(true);
            passwordLabel.setVisible(false);
            visibilityIcon.setVisible(true);
        }   
    }
    
    public SceneProfileController(User user, MainController mainController) throws ClassNotFoundException, SQLException{
        this.userConnected = user;
        this.mainController = mainController;
        try{
            this.dao = new UserDao();
        }
        catch(SQLException eSQL)
        {
            eSQL.printStackTrace();
            UtilsIHM.afficherErreur(eSQL.getLocalizedMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("SceneProfil");
        this.nomLabel.setText(userConnected.getLastName());
        this.prenomLabel.setText(userConnected.getFirstName());
        this.loginLabel.setText(userConnected.getLogin());
    } 
    
    /**
     * Evènement appelé lorsque l'user clique sur le bouton d'enregistrement des
     * informations
     * @throws IOException levé lorsque la redirection vers un autre fichier FXML
     * est impossible 
     * @throws DaoError levé lorsqu'une erreur SQL est levé ou que le nouveau 
     * login est déjà existant dans la base de données
     */
    public void validate() throws IOException, DaoError{
        try{
            System.out.println("Enregistrer");
            if (!passwordLabel.getText().equals(passwordVerifLabel.getText()) ){
                throw new BadPasswordError("Les 2 mots de passes ne sont pas les mêmes");
            }   
            //si l'un des mots de passe est vide
            else if(passwordLabel.getText().isEmpty() || passwordVerifLabel.getText().isEmpty()){
                throw new BadPasswordError("L'un des mots de passe est vide");
            }
            else if (!Utils.IsPasswordSafe(passwordLabel.getText())){
                throw new BadPasswordError("Le mot de passe n'est pas assez sécurisé");
            }
            else{
                this.userConnected.setFirstName(prenomLabel.getText());
                this.userConnected.setLastName(nomLabel.getText());
                this.userConnected.setLogin(loginLabel.getText());
                this.userConnected.setPasswordHash(Utils.HashPassword(passwordLabel.getText()));
                dao.Update(userConnected);
                this.mainController.initFieldsUser();
                this.mainController.projects();
            }
        }catch(BadPasswordError e){
            e.printStackTrace();
            UtilsIHM.afficherErreur(e.getLocalizedMessage());
        }catch(IOException ex){
            ex.printStackTrace();
            UtilsIHM.afficherErreur("Impossible de charger la page Projet");
        }catch(DaoError ex){
            ex.printStackTrace();
            UtilsIHM.afficherErreur(ex.getLocalizedMessage());
        }
    }
}