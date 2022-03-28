package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.ArchiveStatus;
import seedu.address.model.meeting.Participant;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.tag.Tag;

/**
 * Json-friendly version of {@link Meeting}.
 */
public class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private final String name;
    private final String date;
    private final String startTime;
    private final String endTime;
    private final String archiveStatus;
    private final List<JsonAdaptedPerson> participants = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given meeting details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("name") String name, @JsonProperty("date") String date,
                              @JsonProperty("startTime") String startTime, @JsonProperty("endTime") String endTime,
                              @JsonProperty("archiveStatus") String archiveStatus,
                              @JsonProperty("participants") List<JsonAdaptedPerson> participants,
                              @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.archiveStatus = archiveStatus;
        if (!Objects.isNull(participants)) {
            this.participants.addAll(participants);
        }
        if (!Objects.isNull(tags)) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Meeting} into an object of this class for Json use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        name = source.getName().meetingName;
        date = source.getDate().toString();
        startTime = source.getStartTime().value;
        endTime = source.getEndTime().value;
        archiveStatus = source.getArchiveStatus().toString();
        participants.addAll(source.getParticipants().stream().map(participant -> participant.contact)
                .map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        tags.addAll(source.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this JsonAdaptedMeeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Meeting toModelType() throws IllegalValueException {
        final MeetingName modelName = toModelName(name);
        final MeetingDate modelDate = toModelDate(date);
        final StartTime modelStartTime = toModelStartTime(startTime);
        final EndTime modelEndTime = toModelEndTime(endTime);
        final ArchiveStatus modelArchiveStatus = toModelArchiveStatus(archiveStatus);
        final Set<Participant> modelParticipants = toModelParticipants(participants);
        final Set<Tag> modelTags = toModelTags(tags);

        return new Meeting(modelName, modelDate, modelStartTime, modelEndTime,  modelParticipants, modelArchiveStatus, modelTags);
    }

    private MeetingName toModelName(String name) throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MeetingName.class.getSimpleName()));
        }
        if (!MeetingName.isValidMeetingName(name)) {
            throw new IllegalValueException(MeetingName.MESSAGE_CONSTRAINTS);
        }
        return new MeetingName(name);
    }

    private MeetingDate toModelDate(String date) throws IllegalValueException {
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MeetingDate.class.getSimpleName()));
        }
        if (!MeetingDate.isValidDate(date)) {
            throw new IllegalValueException(MeetingDate.MESSAGE_CONSTRAINTS);
        }
        return new MeetingDate(date);
    }

    private StartTime toModelStartTime(String time) throws IllegalValueException {
        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StartTime.class.getSimpleName()));
        }
        if (!StartTime.isValidTime(startTime)) {
            throw new IllegalValueException(StartTime.MESSAGE_CONSTRAINTS);
        }
        return new StartTime(time);
    }

    private EndTime toModelEndTime(String time) throws IllegalValueException {
        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EndTime.class.getSimpleName()));
        }
        if (!EndTime.isValidTime(endTime)) {
            throw new IllegalValueException(EndTime.MESSAGE_CONSTRAINTS);
        }
        return new EndTime(time);
    }

    private ArchiveStatus toModelArchiveStatus(String status) throws IllegalValueException {
        if (!ArchiveStatus.isValidArchiveStatus(archiveStatus)) {
            throw new IllegalValueException(ArchiveStatus.MESSAGE_CONSTRAINTS);
        }
           return new ArchiveStatus(Boolean.parseBoolean(status));
    }

    private Set<Participant> toModelParticipants(List<JsonAdaptedPerson> participants) throws IllegalValueException {
        final List<Contact> meetingPersons = new ArrayList<>();
        for (JsonAdaptedPerson jsonPerson : participants) {
            meetingPersons.add(jsonPerson.toModelType());
        }
        return meetingPersons.stream().map(Participant::new).collect(Collectors.toSet());
    }

    private Set<Tag> toModelTags(List<JsonAdaptedTag> tags) throws IllegalValueException {
        final List<Tag> meetingTags = new ArrayList<>();
        for (JsonAdaptedTag jsonTag : tags) {
            meetingTags.add(jsonTag.toModelType());
        }
        return new HashSet<>(meetingTags);
    }
}
