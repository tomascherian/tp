package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.logic.commands.exceptions.contact.Contact;
import seedu.address.logic.commands.exceptions.contact.Email;
import seedu.address.logic.commands.exceptions.contact.Name;
import seedu.address.logic.commands.exceptions.contact.Phone;
import seedu.address.logic.commands.exceptions.contact.Telegram;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingArchiveStatus;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Participant;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.tag.Tag;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Contact[] getSamplePersons() {
        return new Contact[]{
            new Contact(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Telegram("rak_01"),
                        getTagSet("friends")),
            new Contact(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Telegram("bernie_01"),
                        getTagSet("colleagues", "friends")),
            new Contact(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Telegram("chalro_01"),
                        getTagSet("neighbours")),
            new Contact(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Telegram("david_01"),
                        getTagSet("family")),
            new Contact(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Telegram("irfan_01"),
                        getTagSet("classmates")),
            new Contact(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Telegram("roy_01"),
                        getTagSet("colleagues"))
        };
    }

    public static Meeting[] getSampleMeetings() {
        Contact[] samplePersons = getSamplePersons();

        return new Meeting[] {
            new Meeting(new MeetingName("CS2103T project"), new MeetingDate("12/03/2022"),
                        new StartTime("1600"), new EndTime("1700"),
                        getParticipantSet(samplePersons[0], samplePersons[1]), new MeetingArchiveStatus(false),
                    getTagSet("v1point2")),
            new Meeting(new MeetingName("CS2101 project"), new MeetingDate("15/03/2022"),
                        new StartTime("1900"), new EndTime("2000"),
                        getParticipantSet(samplePersons[0], samplePersons[1]), new MeetingArchiveStatus(false),
                    getTagSet("OP2")),
            new Meeting(new MeetingName("NUSSU meeting"), new MeetingDate("20/03/2022"),
                        new StartTime("1600"), new EndTime("1700"),
                        getParticipantSet(samplePersons[3], samplePersons[1]), new MeetingArchiveStatus(false),
                    getTagSet("important")),
            new Meeting(new MeetingName("Dance exco meeting"), new MeetingDate("22/03/2022"),
                        new StartTime("1700"), new EndTime("1800"),
                        getParticipantSet(samplePersons[0], samplePersons[3]), new MeetingArchiveStatus(false),
                    getTagSet()),
            new Meeting(new MeetingName("Computing club meeting"), new MeetingDate("30/03/2022"),
                        new StartTime("1600"), new EndTime("1700"),
                        getParticipantSet(samplePersons[4], samplePersons[2]), new MeetingArchiveStatus(false),
                    getTagSet()),


        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Contact samplePerson : getSamplePersons()) {
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
    public static Set<Participant> getParticipantSet(Contact... persons) {
        return Arrays.stream(persons)
                .map(Participant::new)
                .collect(Collectors.toSet());
    }
}
