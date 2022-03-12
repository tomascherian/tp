package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.Participant;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static Meeting[] getSampleMeetings() {
        Person[] samplePersons = getSamplePersons();

        return new Meeting[] {
                new Meeting(new MeetingName("CS2103T project"), new MeetingDate("12/03/2022"),
                        new StartTime("1600"), new EndTime("1700"),
                        getParticipantSet(samplePersons[0], samplePersons[1]), getTagSet("v1.2")),
                new Meeting(new MeetingName("CS2101 project"), new MeetingDate("15/03/2022"),
                        new StartTime("1900"), new EndTime("2000"),
                        getParticipantSet(samplePersons[0], samplePersons[1]), getTagSet("OP2")),
                new Meeting(new MeetingName("NUSSU meeting"), new MeetingDate("20/03/2022"),
                        new StartTime("1600"), new EndTime("1700"),
                        getParticipantSet(samplePersons[3], samplePersons[1]), getTagSet("what do we even do")),
                new Meeting(new MeetingName("Dance exco meeting"), new MeetingDate("22/03/2022"),
                        new StartTime("1700"), new EndTime("1800"),
                        getParticipantSet(samplePersons[0], samplePersons[3]), getTagSet()),
                new Meeting(new MeetingName("Computing club meeting"), new MeetingDate("30/03/2022"),
                        new StartTime("1600"), new EndTime("1700"),
                        getParticipantSet(samplePersons[4], samplePersons[2]), getTagSet()),


        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        for (Meeting sampleMeeting : getSampleMeetings()) {
            sampleAb.addMeeting(sampleMeeting);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a participant set containing the list of Persons given.
     */
    public static Set<Participant> getParticipantSet(Person... persons) {
        return Arrays.stream(persons)
                .map(Participant::new)
                .collect(Collectors.toSet());
    }
}
