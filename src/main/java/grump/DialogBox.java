package grump;

import java.io.IOException;
import java.util.Collections;

import grump.enums.CommandType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final double CROP_START_X = 225;
    private static final double CROP_START_Y = 220;
    private static final double CROP_SIZE = 1650;
    private static final double CLIP_RADIUS = 35;
    private static final double CLIP_CENTER_X = 36;
    private static final double CLIP_CENTER_Y = 35;

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert dialog != null && displayPicture != null : "FXML fields failed to inject";
        dialog.setText(text);
        displayPicture.setImage(img);

        Rectangle2D viewport = new Rectangle2D(CROP_START_X, CROP_START_Y, CROP_SIZE, CROP_SIZE);

        displayPicture.setViewport(viewport);

        Circle clip = new Circle(CLIP_CENTER_X, CLIP_CENTER_Y, CLIP_RADIUS);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> reversedChildren =
                FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(reversedChildren);
        getChildren().setAll(reversedChildren);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a dialog box representing the user's message.
     *
     * @param text The text content of the message.
     * @param img The user's display image.
     * @return A DialogBox styled for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    private void changeDialogStyle(CommandType commandType) {
        switch (commandType) {
        case MARK:
        case UNMARK:
        case TAG:
        case UNTAG:
        case FIND:
            dialog.getStyleClass().add("mark-and-tag-label");
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            dialog.getStyleClass().add("add-label");
            break;
        case DELETE:
            dialog.getStyleClass().add("delete-label");
            break;
        case ERROR:
            dialog.getStyleClass().add("error-label");
            break;
        default:
            // No additional styling for other command types
        }
    }

    /**
     * Creates a dialog box representing Grump's reply, flipped so the image is on the left.
     *
     * @param text The text content of the reply.
     * @param img Grump's display image.
     * @return A flipped DialogBox styled for Grump.
     */
    public static DialogBox getGrumpDialog(String text, Image img, CommandType commandType) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        dialogBox.changeDialogStyle(commandType);
        return dialogBox;
    }
}
