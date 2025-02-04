package dk.easv.mrs.GUI;

// Java imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Load the main fxml file
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/MovieView.fxml"));
            primaryStage.setTitle("MRS");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        catch (Exception err) {
            MessageHandler.displayErrorAlertBox(err);
        }
    }


    /**
     * Application starting point
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
