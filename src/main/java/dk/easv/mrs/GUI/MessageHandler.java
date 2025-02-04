package dk.easv.mrs.GUI;

// java imports
import javafx.scene.control.Alert;


public class MessageHandler {

    /**
     *
     * @param t
     */
    public static void displayErrorAlertBox(Exception t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");

        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }
}
