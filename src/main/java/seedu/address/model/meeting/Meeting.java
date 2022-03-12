package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Meeting in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Meeting {

    // Identity fields
    private final MeetingName name;
    private final MeetingDate date;
    private final StartTime startTime;
    private final EndTime endTime;

    // Data fields
    private final Set<Participant> participants = new HashSet<Participant>();
    private final Set<Tag> tags = new HashSet<Tag>();

    /**
     * Every field must be present and not null.
     */
    public Meeting(MeetingName name, MeetingDate date, StartTime startTime, EndTime endTime,
                   Set<Participant> participants, Set<Tag> tags) {
        requireAllNonNull(name, date, startTime, endTime, participants, tags);
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants.addAll(participants);
        this.tags.addAll(tags);
    }

    public MeetingName getName() {
        return name;
    }

    public MeetingDate getDate() {
        return date;
    }

    public StartTime getStartTime() {
        return startTime;
    }

    public EndTime getEndTime() {
        return endTime;
    }

    public Set<Participant> getParticipants() {
        return Collections.unmodifiableSet(participants);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both meetings have the same name and same date.
     * This defines a weaker notion of equality between two meetings.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getName().equals(getName())
                && otherMeeting.getDate().equals(getDate());
    }

    /**
     * Returns true if both meetings have the same identity and data fields.
     * This defines a stronger notion of equality between two meetings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getName().equals(getName())
                && otherMeeting.getDate().equals(getDate())
                && otherMeeting.getStartTime().equals(getStartTime())
                && otherMeeting.getEndTime().equals(getEndTime())
                && otherMeeting.getParticipants().equals(getParticipants())
                && otherMeeting.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, date, startTime, endTime, participants, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Date: ")
                .append(getDate())
                .append("; From: ")
                .append(getStartTime())
                .append("; To: ")
                .append(getEndTime())
                .append("; Participants: ")
                .append(getParticipants());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}

