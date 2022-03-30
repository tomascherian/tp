package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a meeting clash notification.
 */
public class MeetingClashWindow extends UiPart<Stage> {

    public static final String CLASH_MESSAGE = "WARNING: There is a clash in timing with another Meeting! "
            + "Refer to the display box for details regarding these clash(es).";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "MeetingClashWindow.fxml";

    @FXML
    private Label clashMessage;

    /**
     * Creates a new MeetingClashWindow.
     *
     * @param root Stage to use as the root of the MeetingClashWindow.
     */
    public MeetingClashWindow(Stage root) {
        super(FXML, root);
        clashMessage.setText(CLASH_MESSAGE);
    }

    /**
     * Creates a new MeetingClashWindow.
     */
    public MeetingClashWindow() {
        this(new Stage());
    }

    /**
     * Shows the meeting clash window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing meeting clash notification.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the meeting clash window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the meeting clash window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the meeting clash window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
