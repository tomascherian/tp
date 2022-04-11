package seedu.address.testutil;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditMeetingDescriptor objects.
 */
public class EditMeetingDescriptorBuilder {

    private EditMeetingDescriptor descriptor;

    public EditMeetingDescriptorBuilder() {
        descriptor = new EditMeetingDescriptor();
    }

    public EditMeetingDescriptorBuilder(EditMeetingDescriptor descriptor) {
        this.descriptor = new EditMeetingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMeetingDescriptor} with fields containing {@code meeting}'s details
     */
    public EditMeetingDescriptorBuilder(Meeting meeting) {
        descriptor = new EditMeetingDescriptor();
        descriptor.setMeetingName(meeting.getName());
        descriptor.setMeetingDate(meeting.getDate());
        descriptor.setStartTime(meeting.getStartTime());
        descriptor.setEndTime(meeting.getEndTime());
        descriptor.setTags(meeting.getTags());
        descriptor.setParticipantsIndex(Collections.emptySet());
    }

    /**
     * Sets the {@code MeetingName} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withName(String name) {
        descriptor.setMeetingName(new MeetingName(name));
        return this;
    }

    /**
     * Sets the {@code MeetingDate} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDate(String date) {
        descriptor.setMeetingDate(new MeetingDate(date));
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withStartTime(String startTime) {
        descriptor.setStartTime(new StartTime(startTime));
        return this;
    }

    /**
     * Sets the {@code EndTime} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withEndTime(String endTime) {
        descriptor.setEndTime(new EndTime(endTime));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditMeetingDescriptor}
     * that we are building.
     */
    public EditMeetingDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditMeetingDescriptor build() {
        return descriptor;
    }
}
