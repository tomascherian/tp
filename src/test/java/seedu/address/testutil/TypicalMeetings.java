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


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalMeetings {

    public static final Meeting CS2103T = new MeetingBuilder().withName("CS2103T project").withDate("12/04/2022")
            .withStartTime("1600").withEndTime("1700").withArchiveStatus("false")
            .withParticipants(ALICE, BENSON).withTags("v1point2").build();
    public static final Meeting CS2101 = new MeetingBuilder().withName("CS2101 project").withDate("15/05/2022")
            .withStartTime("1900").withEndTime("2000").withArchiveStatus("false")
            .withParticipants(BENSON, CARL).withTags("OP2").build();
    public static final Meeting NUSSU = new MeetingBuilder().withName("NUSSU meeting").withDate("20/06/2022")
            .withStartTime("1600").withEndTime("1700").withArchiveStatus("false")
            .withParticipants(DANIEL, ELLE).withTags("important").build();
    public static final Meeting DANCE_CLUB = new MeetingBuilder().withName("Dance exco meeting").withDate("22/07/2022")
            .withStartTime("1700").withEndTime("1800").withArchiveStatus("false").build();
    public static final Meeting COMPUTING_CLUB = new MeetingBuilder().withName("Computing club meeting")
            .withDate("30/08/2022").withStartTime("1600").withEndTime("1700")
            .withArchiveStatus("false").withParticipants(ELLE, FIONA).build();

    private TypicalMeetings() {
    } // prevents instantiation

    /**
     * Returns a {@code List} with all the typical meetings.
     */
    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(CS2103T, CS2101, NUSSU, DANCE_CLUB, COMPUTING_CLUB));
    }


}
