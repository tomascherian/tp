package seedu.address.testutil;

import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.logic.commands.exceptions.contact.Contact;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalAddressBook {

    public static final List<Contact> CONTACT_LIST = TypicalPersons.getTypicalPersons();
    public static final List<Meeting> MEETING_LIST = TypicalMeetings.getTypicalMeetings();

    private TypicalAddressBook() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
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
