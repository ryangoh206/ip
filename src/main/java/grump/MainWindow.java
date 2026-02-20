package grump;

import grump.command.CommandResult;
import grump.enums.CommandType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Label bannerDateLabel;

    private Grump grump;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image grumpImage =
            new Image(this.getClass().getResourceAsStream("/images/DaGrump.png"));

    /**
     * Initializes the main window by binding the scroll pane to auto-scroll on new content
     * and sets the current date in the banner.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Set current date in the banner
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        bannerDateLabel.setText(today.format(formatter));
    }

    /** Injects the Grump instance */
    public void setGrump(Grump grump) {
        assert grump != null : "Grump instance cannot be null";
        this.grump = grump;
        dialogContainer.getChildren().addAll(
                DialogBox.getGrumpDialog(grump.getGreeting(), grumpImage, CommandType.GREETING));
    }

    /**
     * Creates two dialog boxes, one containing the user input and the other containing Grump's
     * reply and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "User input should not be null";
        CommandResult commandResult = grump.getResponseForGui(input);
        assert commandResult != null : "Response from Grump should not be null";
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getGrumpDialog(commandResult.getResponseString(), grumpImage,
                        commandResult.getCommandType()));
        userInput.clear();
    }
}
