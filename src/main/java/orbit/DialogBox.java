package orbit;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Displays one message in the conversation.
 */
public class DialogBox extends HBox {
    private final Label message = new Label();

    private DialogBox(String text) {
        message.setText(text);
        message.setWrapText(true);
        message.setMaxWidth(340);
        getChildren().add(message);
    }

    /** @return A right-aligned dialog containing the user's message. */
    public static DialogBox getUserDialog(String text) {
        DialogBox dialog = new DialogBox(text);
        dialog.setAlignment(Pos.TOP_RIGHT);
        dialog.message.getStyleClass().add("user-message");
        return dialog;
    }

    /** @return A left-aligned dialog containing Orbit's response. */
    public static DialogBox getOrbitDialog(String text) {
        DialogBox dialog = new DialogBox(text);
        dialog.setAlignment(Pos.TOP_LEFT);
        dialog.message.getStyleClass().add("orbit-message");
        return dialog;
    }
}
