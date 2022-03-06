package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Date;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Name;
import seedu.address.model.meeting.Time;
import seedu.address.model.meeting.PersonList;
import seedu.address.model.person.Person;
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
        name = source.getName().name;
        date = source.getDate().value;
        startTime = source.getStartTime().value;
        endTime = source.getEndTime().value;
        persons.addAll(source.getPersonList().getPersons()
                .stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this JsonAdaptedMeeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Meeting toModelType() throws IllegalValueException {
        final Name modelName = toModelName(name);
        final Date modelDate = toModelDate(date);
        final Time modelStartTime = toModelTime(startTime);
        final Time modelEndTime = toModelTime(endTime);
        final PersonList modelPersons = toModelPersons(persons);

        return new Meeting(modelName, modelDate, modelStartTime, modelEndTime, modelPersons);
    }

    private Name toModelName(String name) throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    private Date toModelDate(String date) throws IllegalValueException {
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(date);
    }

    private Time toModelTime(String time) throws IllegalValueException {
        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(startTime)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        return new Time(time);
    }

    private PersonList toModelPersons(List<JsonAdaptedPerson> persons) throws IllegalValueException {
        final List<Person> meetingPersons = new ArrayList<>();
        for (JsonAdaptedPerson jsonPerson : persons) {
            meetingPersons.add(jsonPerson.toModelType());
        }
        return new PersonList(meetingPersons);
    }
}
