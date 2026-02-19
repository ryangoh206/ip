package grump;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Grump using FXML.
 */
public class Main extends Application {
    private static final int MIN_WINDOW_HEIGHT = 220;
    private static final int MIN_WINDOW_WIDTH = 417;

    private Grump grump = new Grump("data/tasks.csv");

    /**
     * Starts the GUI application by loading the FXML layout and injecting the Grump instance.
     *
     * @param stage The primary stage for this JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        assert stage != null : "Stage must not be null.";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane rootPane = fxmlLoader.load();
            Scene scene = new Scene(rootPane);
            stage.setScene(scene);
            stage.setMinHeight(MIN_WINDOW_HEIGHT);
            stage.setMinWidth(MIN_WINDOW_WIDTH);
            fxmlLoader.<MainWindow>getController().setGrump(grump);
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load the application UI: " + e.getMessage());
            System.err.println("Application will now exit.");
            System.exit(1);
        }
    }
}
