package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalMeetings.MEETING_DATE_MATCHING_CS2103T;
import static seedu.address.testutil.TypicalMeetings.MEETING_DATE_MATCHING_NUSSU;
import static seedu.address.testutil.TypicalMeetings.MEETING_NAME_MATCHING_CS2103T;
import static seedu.address.testutil.TypicalMeetings.MEETING_NAME_MATCHING_NUSSU;
import static seedu.address.testutil.TypicalMeetings.TAG_MATCHING_CS2103T;
import static seedu.address.testutil.TypicalMeetings.TAG_MATCHING_NUSSU;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.MeetingNameHasKeywordsPredicate;
import seedu.address.model.meeting.MeetingOccursOnDatesPredicate;
import seedu.address.model.meeting.MeetingTagHasKeywordsPredicate;
import seedu.address.model.tag.Tag;

class FindMeetingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final MeetingNameHasKeywordsPredicate emptyNamePredicate =
            new MeetingNameHasKeywordsPredicate(Collections.emptySet());
    private final MeetingOccursOnDatesPredicate emptyDatePredicate =
            new MeetingOccursOnDatesPredicate(Collections.emptySet());
    private final MeetingTagHasKeywordsPredicate emptyTagPredicate =
            new MeetingTagHasKeywordsPredicate(Collections.emptySet());


    @Test
    public void equals() {
        MeetingNameHasKeywordsPredicate firstMeetingNamePredicate =
                new MeetingNameHasKeywordsPredicate(Collections.singleton(new MeetingName("first")));
        MeetingNameHasKeywordsPredicate secondMeetingNamePredicate =
                new MeetingNameHasKeywordsPredicate(Collections.singleton(new MeetingName("second")));

        MeetingTagHasKeywordsPredicate firstTagPredicate =
                new MeetingTagHasKeywordsPredicate(Collections.emptySet());
        MeetingTagHasKeywordsPredicate secondTagPredicate =
                new MeetingTagHasKeywordsPredicate(Collections.singleton(new Tag("secondTag")));

        MeetingOccursOnDatesPredicate firstMeetingDatePredicate =
                new MeetingOccursOnDatesPredicate(Collections.emptySet());
        MeetingOccursOnDatesPredicate secondMeetingDatePredicate =
                new MeetingOccursOnDatesPredicate(Collections.singleton(new MeetingDate("16-06-2022")));

        FindMeetingCommand firstFindMeetingCommand =
                new FindMeetingCommand(firstMeetingNamePredicate, firstMeetingDatePredicate, firstTagPredicate);

        // same object -> returns true
        assertTrue(firstFindMeetingCommand.equals(firstFindMeetingCommand));

        // same values -> returns true
        FindMeetingCommand firstFindMeetingCommandCopy =
                new FindMeetingCommand(firstMeetingNamePredicate, firstMeetingDatePredicate, firstTagPredicate);
        assertTrue(firstFindMeetingCommand.equals(firstFindMeetingCommandCopy));

        // different types -> returns false
        assertFalse(firstFindMeetingCommand.equals(1));

        // null -> returns false
        assertFalse(firstFindMeetingCommand.equals(null));

        // different name -> returns false
        FindMeetingCommand differentNameFindMeetingCommand =
                new FindMeetingCommand(secondMeetingNamePredicate, firstMeetingDatePredicate, firstTagPredicate);
        assertFalse(firstFindMeetingCommand.equals(differentNameFindMeetingCommand));

        //different date -> returns false
        FindMeetingCommand differentDateFindMeetingCommand =
                new FindMeetingCommand(firstMeetingNamePredicate, secondMeetingDatePredicate, firstTagPredicate);
        assertFalse(firstFindMeetingCommand.equals(differentDateFindMeetingCommand));

        //different date -> returns false
        FindMeetingCommand differentTagFindMeetingCommand =
                new FindMeetingCommand(firstMeetingNamePredicate, firstMeetingDatePredicate, secondTagPredicate);
        assertFalse(firstFindMeetingCommand.equals(differentTagFindMeetingCommand));
    }

    //---------------- Tests for execute with only one type of search --------------------------------------

    @Test
    public void execute_searchByName_success() {
        // one name keyword specified
        MeetingNameHasKeywordsPredicate namePredicate =
                new MeetingNameHasKeywordsPredicate(Set.of(MEETING_NAME_MATCHING_CS2103T));
        FindMeetingCommand command = new FindMeetingCommand(namePredicate, emptyDatePredicate, emptyTagPredicate);
        String expectedMessage = String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, 1);
        expectedModel.updateFilteredMeetingList(namePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        // multiple name keywords specified
        namePredicate =
                new MeetingNameHasKeywordsPredicate(Set.of(MEETING_NAME_MATCHING_CS2103T, MEETING_NAME_MATCHING_NUSSU));
        command = new FindMeetingCommand(namePredicate, emptyDatePredicate, emptyTagPredicate);
        expectedMessage = String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, 2);
        expectedModel.updateFilteredMeetingList(namePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_searchByDate_success() {
        // one date specified
        MeetingOccursOnDatesPredicate datesPredicate =
                new MeetingOccursOnDatesPredicate(Set.of(MEETING_DATE_MATCHING_CS2103T));
        FindMeetingCommand command = new FindMeetingCommand(emptyNamePredicate, datesPredicate, emptyTagPredicate);
        String expectedMessage = String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, 1);
        expectedModel.updateFilteredMeetingList(datesPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        // multiple dates specified
        datesPredicate =
                new MeetingOccursOnDatesPredicate(Set.of(MEETING_DATE_MATCHING_CS2103T, MEETING_DATE_MATCHING_NUSSU));
        command = new FindMeetingCommand(emptyNamePredicate, datesPredicate, emptyTagPredicate);
        expectedMessage = String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, 2);
        expectedModel.updateFilteredMeetingList(datesPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_searchByTag_success() {
        // one tag keyword specified
        MeetingTagHasKeywordsPredicate tagPredicate =
                new MeetingTagHasKeywordsPredicate(Set.of(TAG_MATCHING_CS2103T));
        FindMeetingCommand command = new FindMeetingCommand(emptyNamePredicate, emptyDatePredicate, tagPredicate);
        String expectedMessage = String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, 1);
        expectedModel.updateFilteredMeetingList(tagPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        // multiple tag keywords specified
        tagPredicate =
                new MeetingTagHasKeywordsPredicate(Set.of(TAG_MATCHING_CS2103T, TAG_MATCHING_NUSSU));
        command = new FindMeetingCommand(emptyNamePredicate, emptyDatePredicate, tagPredicate);
        expectedMessage = String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, 2);
        expectedModel.updateFilteredMeetingList(tagPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    //---------------- Tests for execute combining multiple types of search --------------------------------------
    @Test
    public void execute_searchByAll_success() {
        // all types matching
        MeetingNameHasKeywordsPredicate namePredicate =
                new MeetingNameHasKeywordsPredicate(Set.of(MEETING_NAME_MATCHING_CS2103T));
        MeetingOccursOnDatesPredicate datesPredicate =
                new MeetingOccursOnDatesPredicate(Set.of(MEETING_DATE_MATCHING_CS2103T));
        MeetingTagHasKeywordsPredicate tagPredicate =
                new MeetingTagHasKeywordsPredicate(Set.of(TAG_MATCHING_CS2103T));
        FindMeetingCommand command = new FindMeetingCommand(namePredicate, datesPredicate, tagPredicate);
        String expectedMessage = String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, 1);
        expectedModel.updateFilteredMeetingList(namePredicate.and(datesPredicate).and(tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        // one type non-matching
        tagPredicate = new MeetingTagHasKeywordsPredicate(Set.of(TAG_MATCHING_NUSSU));
        command = new FindMeetingCommand(namePredicate, datesPredicate, tagPredicate);
        expectedMessage = String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, 0);
        expectedModel.updateFilteredMeetingList(m -> false);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
