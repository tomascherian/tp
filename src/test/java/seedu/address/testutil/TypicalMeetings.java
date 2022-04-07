package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.tag.Tag;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalMeetings {

    public static final String CS2103T_NAME = "CS2103T project";
    public static final String CS2103T_DATE = "12/03/2022";
    public static final String CS2103T_TAG = "v1point2";
    public static final String NUSSU_NAME = "NUSSU meeting";
    public static final String NUSSU_DATE = "20/03/2022";
    public static final String NUSSU_TAG = "important";

    public static final Meeting CS2103T = new MeetingBuilder().withName(CS2103T_NAME).withDate(CS2103T_DATE)
            .withStartTime("1600").withEndTime("1700").withArchiveStatus("false")
            .withParticipants(ALICE, BENSON).withTags(CS2103T_TAG).build();
    public static final Meeting CS2101 = new MeetingBuilder().withName("CS2101 project").withDate("15/03/2022")
            .withStartTime("1900").withEndTime("2000").withArchiveStatus("false")
            .withParticipants(BENSON, CARL).withTags("OP2").build();
    public static final Meeting NUSSU = new MeetingBuilder().withName(NUSSU_NAME).withDate(NUSSU_DATE)
            .withStartTime("1600").withEndTime("1700").withArchiveStatus("false")
            .withParticipants(DANIEL, ELLE).withTags(NUSSU_TAG).build();
    public static final Meeting DANCE_CLUB = new MeetingBuilder().withName("Dance exco meeting").withDate("22/03/2022")
            .withStartTime("1700").withEndTime("1800").withArchiveStatus("false").build();
    public static final Meeting COMPUTING_CLUB = new MeetingBuilder().withName("Computing club meeting")
            .withDate("30/03/2022").withStartTime("1600").withEndTime("1700")
            .withArchiveStatus("false").withParticipants(ELLE, FIONA).build();

    public static final MeetingName MEETING_NAME_MATCHING_CS2103T = new MeetingName(CS2103T_NAME);
    public static final MeetingName MEETING_NAME_MATCHING_NUSSU = new MeetingName(NUSSU_NAME);
    public static final MeetingDate MEETING_DATE_MATCHING_CS2103T = new MeetingDate(CS2103T_DATE);
    public static final MeetingDate MEETING_DATE_MATCHING_NUSSU = new MeetingDate(NUSSU_DATE);
    public static final Tag TAG_MATCHING_CS2103T = new Tag(CS2103T_TAG);
    public static final Tag TAG_MATCHING_NUSSU = new Tag(NUSSU_TAG);

    private TypicalMeetings() {
    } // prevents instantiation

    /**
     * Returns a {@code List} with all the typical meetings.
     */
    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(CS2103T, CS2101, NUSSU, DANCE_CLUB, COMPUTING_CLUB));
    }


}
