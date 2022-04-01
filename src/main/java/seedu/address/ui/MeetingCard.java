package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.address.model.meeting.Meeting;

/**
 * An UI component that displays information of a {@code Meeting}.
 */
public class MeetingCard extends UiPart<Region> {

    private static final String FXML = "MeetingListCard.fxml";
    private static final String LABEL_ARCHIVED = "ARCHIVED";
    private static final Background BACKGROUND_ARCHIVED =
            new Background(new BackgroundFill(
                    Color.rgb(128, 96, 0, 0.7),
                    new CornerRadii(0),
                    new Insets(0)));
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Meeting meeting;

    @FXML
    private FlowPane participants;
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label archiveStatus;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label startToEndTime;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code MeetingCard} with the given {@code Meeting} and index to display.
     */
    public MeetingCard(Meeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        name.setText(meeting.getName().meetingName);
        date.setText(meeting.getDate().toString());
        startToEndTime.setText(meeting.getStartTime().value + " - " + meeting.getEndTime().value);
        meeting.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        meeting.getParticipants().stream()
                .sorted(Comparator.comparing(participant -> participant.contact.getName().fullName))
                .forEach(participant -> participants.getChildren().add(
                        new Label(participant.contact.getName().fullName)));
        if (meeting.getArchiveStatus().isArchive) {
            archiveStatus.setText(LABEL_ARCHIVED);
            archiveStatus.setBackground(BACKGROUND_ARCHIVED);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingCard)) {
            return false;
        }

        // state check
        MeetingCard card = (MeetingCard) other;
        return id.getText().equals(card.id.getText())
                && meeting.equals(card.meeting);
    }
}
