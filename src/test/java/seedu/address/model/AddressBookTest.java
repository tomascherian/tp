package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
//import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Contact editedAlice = new PersonBuilder(ALICE).withTelegram(VALID_TELEGRAM_BOB).withTags(VALID_TAG_HUSBAND)
                .build();

        List<Contact> newPersons = Arrays.asList(ALICE, editedAlice);


        Meeting meeting = new MeetingBuilder().withName("Project Discussion")
                .withDate("10/02/2022").withStartTime("1830").withEndTime("1930")
                .withParticipants(ALICE)
                .withTags("teammates").build();
        Meeting editedMeeting = new MeetingBuilder(meeting).withDate("23/02/2022").build();

        List<Meeting> newMeetings = Arrays.asList(meeting, editedMeeting);
        AddressBookStub newData = new AddressBookStub(newPersons, newMeetings);


        //assertThrows(DuplicateContactException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Contact editedAlice = new PersonBuilder(ALICE).withTelegram(VALID_TELEGRAM_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertFalse(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {

        private final ObservableList<Contact> persons = FXCollections.observableArrayList();

        private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();

        AddressBookStub(Collection<Contact> persons, Collection<Meeting> meetings) {

            this.persons.setAll(persons);
            this.meetings.setAll(meetings);
        }

        @Override
        public ObservableList<Contact> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Meeting> getMeetingList() {
            return meetings;
        }
    }
}
