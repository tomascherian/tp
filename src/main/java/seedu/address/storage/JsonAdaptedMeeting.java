package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.meeting.EndTime
import seedu.address.model.meeting.ParticipantsList;
import seedu.address.model.contact.Contact;
import seedu.address.storage.JsonAdaptedPerson;

/**
 * Json-friendly version of {@link Meeting}.
 */
public class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private final String name;
    private final String date;
    private final String startTime;
    private final String endTime;
    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given meeting details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("name") String name, @JsonProperty("date") String date,
                              @JsonProperty("startTime") String startTime, @JsonProperty("endTime") String endTime,
                              @JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        if (!Objects.isNull(persons)) {
            this.persons.addAll(persons);
        }
    }

    /**
     * Converts a given {@code Meeting} into an object of this class for Json use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        // to be replaced with actual implementation of Meeting
        name = source.getName().meetingName;
        date = source.getDate().value;
        startTime = source.getStartTime().value;
        endTime = source.getEndTime().value;
        persons.addAll(source.getParticipantsList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
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
        final Set<Participant> modelParticipants = toModelParticipants(persons);

        return new Meeting(modelName, modelDate, modelStartTime, modelEndTime, modelParticipants);
    }

    private MeetingName toModelName(String name) throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MeetingName.class.getSimpleName()));
        }
        if (!MeetingName.isValidName(name)) {
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
        if (!StartTime.isValidStartTime(startTime)) {
            throw new IllegalValueException(StartTime.MESSAGE_CONSTRAINTS);
        }
        return new StartTime(time);
    }

    private EndTime toModelEndTime(String time) throws IllegalValueException {
        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EndTime.class.getSimpleName()));
        }
        if (!EndTime.isValidEndTime(endTime)) {
            throw new IllegalValueException(EndTime.MESSAGE_CONSTRAINTS);
        }
        return new EndTime(time);
    }

    private Set<Participant> toModelParticipants(List<JsonAdaptedPerson> persons) throws IllegalValueException {
        final List<Contact> meetingPersons = new ArrayList<>();
        for (JsonAdaptedPerson jsonPerson : persons) {
            meetingPersons.add(jsonPerson.toModelType());
        }
        return new meetingPersons.stream().collect(Collectors.toSet());
    }
}
