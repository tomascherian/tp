package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.RemindCommand.MESSAGE_REMINDER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalMeetings.CS2101;
import static seedu.address.testutil.TypicalMeetings.CS2103T;
import static seedu.address.testutil.TypicalMeetings.COMPUTING_CLUB;
import static seedu.address.testutil.TypicalMeetings.NUSSU;
import static seedu.address.testutil.TypicalMeetings.DANCE_CLUB;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.ReminderDatePredicate;



/**
 * Contains integration tests (interaction with the Model) for {@code RemindCommand}.
 */
public class ReminderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ReminderDatePredicate firstPredicate = new ReminderDatePredicate(1);
        ReminderDatePredicate secondPredicate = new ReminderDatePredicate(2);

        RemindCommand remindFirstCommand = new RemindCommand(firstPredicate);
        RemindCommand remindSecondCommand = new RemindCommand(secondPredicate);

        // same object return true
        assertTrue(remindFirstCommand.equals(remindFirstCommand));

        // same values return true
        RemindCommand copy = new RemindCommand(firstPredicate);
        assertTrue(remindFirstCommand.equals(copy));

        // different types return false
        assertFalse(remindFirstCommand.equals("1"));

        // null -> returns false
        assertFalse(remindFirstCommand.equals(null));

        // different predicate returns false
        assertFalse(remindFirstCommand.equals(remindSecondCommand));
    }

    // Returns zero meetings due to invalid days
    @Test
    public void execute_invalidRange_noMeetingFound() {
        String expectedMessage = String.format(MESSAGE_REMINDER, 0, -1);
        ReminderDatePredicate predicate = new ReminderDatePredicate(-1);
        RemindCommand command = new RemindCommand(predicate);
        expectedModel.updateFilteredMeetingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMeetingList());
    }

    // returns zero meetings as no meeting starts in 10 days
    @Test
    public void execute_smallRange_noMeetingFound() {
        String expectedMessage = String.format(MESSAGE_REMINDER, 0, 10);
        ReminderDatePredicate predicate = new ReminderDatePredicate(10);
        RemindCommand command = new RemindCommand(predicate);
        expectedModel.updateFilteredMeetingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredMeetingList());
    }

    @Test    // returns all meeting due to large range. This test will fail after 12/4/2022.
    public void execute_largeRange_allMeetingFound() {
        String expectedMessage = String.format(MESSAGE_REMINDER, 5, 69420);
        ReminderDatePredicate predicate = new ReminderDatePredicate(69420);
        RemindCommand command = new RemindCommand(predicate);
        expectedModel.updateFilteredMeetingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(
                Arrays.asList(CS2103T, CS2101, NUSSU, DANCE_CLUB, COMPUTING_CLUB),
                model.getFilteredMeetingList());
    }

    // returns all meeting due to big range. This test will fail after 12/4/2022
    @Test
    public void execute_largeRange_mostMeetingFound() {
        String expectedMessage = String.format(MESSAGE_REMINDER, 5, 500);
        ReminderDatePredicate predicate = new ReminderDatePredicate(500);
        RemindCommand command = new RemindCommand(predicate);
        expectedModel.updateFilteredMeetingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(
                Arrays.asList(CS2103T, CS2101, NUSSU, DANCE_CLUB, COMPUTING_CLUB),
                model.getFilteredMeetingList());
    }
}
