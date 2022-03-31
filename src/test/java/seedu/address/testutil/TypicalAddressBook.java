package seedu.address.testutil;

import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Person} and {@code Meeting} objects to be used in tests.
 */
public class TypicalAddressBook {

    public static final List<Contact> CONTACT_LIST = TypicalPersons.getTypicalPersons();
    public static final List<Meeting> MEETING_LIST = TypicalMeetings.getTypicalMeetings();

    private TypicalAddressBook() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons and meetings.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();

        for (Contact person : CONTACT_LIST) {
            ab.addPerson(person);
        }

        for (Meeting meeting : MEETING_LIST) {
            ab.addMeeting(meeting);
        }

        return ab;
    }

}
