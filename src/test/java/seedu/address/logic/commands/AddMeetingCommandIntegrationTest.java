package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingArchiveStatus;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.MeetingBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddMeetingCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newMeeting_success() {
        Meeting validMeeting = new MeetingBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addMeeting(validMeeting);

        MeetingName validMeetingName = validMeeting.getName();
        MeetingDate validMeetingDate = validMeeting.getDate();
        StartTime validMeetingStartTime = validMeeting.getStartTime();
        EndTime validMeetingEndTime = validMeeting.getEndTime();
        MeetingArchiveStatus validMeetingArchiveStatus = validMeeting.getArchiveStatus();
        Set<Index> validMeetingParticipantsIndex = new HashSet<Index>();
        Set<Tag> validMeetingTagList = validMeeting.getTags();

        assertCommandSuccess(new AddMeetingCommand(validMeetingName, validMeetingDate, validMeetingStartTime,
                        validMeetingEndTime, validMeetingArchiveStatus, validMeetingParticipantsIndex,
                        validMeetingTagList), model,
                String.format(AddMeetingCommand.MESSAGE_SUCCESS, validMeeting), expectedModel);
    }

}
