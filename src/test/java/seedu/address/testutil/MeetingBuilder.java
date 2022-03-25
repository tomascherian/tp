package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Participant;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;


/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {
    public static final String DEFAULT_NAME = "Project Meeting";
    public static final String DEFAULT_DATE = "01/04/2022";
    public static final String DEFAULT_START_TIME = "1800";
    public static final String DEFAULT_END_TIME = "1900";

    private MeetingName name;
    private MeetingDate date;
    private StartTime startTime;
    private EndTime endTime;
    private Set<Participant> participants;
    private Set<Tag> tags;

    /**
     *  Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        name = new MeetingName(DEFAULT_NAME);
        date = new MeetingDate(DEFAULT_DATE);
        startTime = new StartTime(DEFAULT_START_TIME);
        endTime = new EndTime(DEFAULT_END_TIME);
        participants = new HashSet<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the MeetingBuilder with the data of {@code meetingToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        name = meetingToCopy.getName();
        date = meetingToCopy.getDate();
        startTime = meetingToCopy.getStartTime();
        endTime = meetingToCopy.getEndTime();
        participants = new HashSet<Participant>(meetingToCopy.getParticipants());
        tags = new HashSet<Tag>(meetingToCopy.getTags());
    }

    /**
     * Sets the {@code MeetingName} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withName(String name) {
        this.name = new MeetingName(name);
        return this;
    }

    /**
     * Sets the {@code MeetingDate} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDate(String date) {
        this.date = new MeetingDate(date);
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withStartTime(String startTime) {
        this.startTime = new StartTime(startTime);
        return this;
    }

    /**
     * Sets the {@code EndTime} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withEndTime(String endTime) {
        this.endTime = new EndTime(endTime);
        return this;
    }

    /**
     * Sets the {@code ParticipantsList} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withParticipants(Contact ... contacts) {
        this.participants = SampleDataUtil.getParticipantSet(contacts);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Meeting} that we are building.
     */
    public MeetingBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Meeting build() {
        return new Meeting(name, date, startTime, endTime, participants, tags);
    }
}
