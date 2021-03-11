package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author math7
 */
public class Main extends javafx.application.Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Application/Vue/main/main.fxml"));
        stage = stage;
        Scene scene = new Scene(root);

        stage.setTitle("Prototype avancé");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


    public static void switchScene(Scene scene) {
        stage.setScene(scene);
    }
}
