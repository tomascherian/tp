package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.CS2103T;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.StartTime;

public class JsonAdaptedMeetingTest {
    private static final String INVALID_MEETING_NAME = "CS2!03@";
    private static final String INVALID_DATE = "10 12 2022";
    private static final String INVALID_START_TIME = "12:30";
    private static final String INVALID_END_TIME = "2559";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_MEETING_NAME = CS2103T.getName().toString();
    private static final String VALID_DATE = CS2103T.getDate().toString();
    private static final String VALID_START_TIME = CS2103T.getStartTime().toString();
    private static final String VALID_END_TIME = CS2103T.getEndTime().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = CS2103T.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedPerson> VALID_PARTICIPANTS = CS2103T.getParticipants().stream()
            .map(participant -> new JsonAdaptedPerson(participant.contact))
            .collect(Collectors.toList());

    @Test
    public void toModelType_validMeetingDetails_returnsMeeting() throws Exception {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(CS2103T);
        assertEquals(CS2103T, meeting.toModelType());
    }

    @Test
    public void toModelType_invalidMeetingName_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(INVALID_MEETING_NAME, VALID_DATE, VALID_START_TIME,
                VALID_END_TIME, VALID_PARTICIPANTS, VALID_TAGS);
        String expectedMessage = MeetingName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullMeetingName_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(null, VALID_DATE, VALID_START_TIME, VALID_END_TIME,
                VALID_PARTICIPANTS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MeetingName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_MEETING_NAME, INVALID_DATE, VALID_START_TIME, VALID_END_TIME,
                        VALID_PARTICIPANTS, VALID_TAGS);
        String expectedMessage = MeetingDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_MEETING_NAME, null, VALID_START_TIME,
                VALID_END_TIME, VALID_PARTICIPANTS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MeetingDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_MEETING_NAME, VALID_DATE, INVALID_START_TIME, VALID_END_TIME,
                        VALID_PARTICIPANTS, VALID_TAGS);
        String expectedMessage = StartTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_MEETING_NAME, VALID_DATE, null, VALID_END_TIME,
                VALID_PARTICIPANTS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StartTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidEndTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_MEETING_NAME, VALID_DATE, VALID_START_TIME, INVALID_END_TIME,
                        VALID_PARTICIPANTS, VALID_TAGS);
        String expectedMessage = EndTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_MEETING_NAME, VALID_DATE, VALID_START_TIME, null,
                VALID_PARTICIPANTS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EndTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_MEETING_NAME, VALID_DATE, VALID_START_TIME, VALID_END_TIME,
                        VALID_PARTICIPANTS, invalidTags);
        assertThrows(IllegalValueException.class, meeting::toModelType);
    }

}
