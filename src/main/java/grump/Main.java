package grump;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Grump grump = new Grump("data/tasks.csv");

    @Override
    public void start(Stage stage) {
        assert stage != null : "Stage must not be null.";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setGrump(grump);
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load the application UI: " + e.getMessage());
            System.err.println("Application will now exit.");
            System.exit(1);
        }
    }
}
