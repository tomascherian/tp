package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditMeetingDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;


/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_TELEGRAM_AMY = "aB_01";
    public static final String VALID_TELEGRAM_BOB = "bc0_1";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String VALID_MEETING_NAME_CS2103 = "CS2103 Project";
    public static final String VALID_MEETING_NAME_NUSSU = "Nussu meeting";
    public static final String VALID_DATE_CS2103 = "11-04-2022";
    public static final String VALID_DATE_NUSSU = "22-04-2022";
    public static final String VALID_START_TIME_CS2103 = "1630";
    public static final String VALID_START_TIME_NUSSU = "1530";
    public static final String VALID_END_TIME_CS2103 = "1800";
    public static final String VALID_END_TIME_NUSSU = "1730";
    public static final String VALID_TAG_CS2103 = "V1point4";
    public static final String VALID_TAG_NUSSU = "important";

    public static final String MEETING_NAME_DESC_CS2103 = " " + PREFIX_MEETING_NAME + VALID_MEETING_NAME_CS2103;
    public static final String MEETING_NAME_DESC_NUSSU = " " + PREFIX_MEETING_NAME + VALID_MEETING_NAME_NUSSU;
    public static final String DATE_DESC_CS2103 = " " + PREFIX_DATE + VALID_DATE_CS2103;
    public static final String DATE_DESC_NUSSU = " " + PREFIX_DATE + VALID_DATE_NUSSU;
    public static final String START_TIME_DESC_CS2103 = " " + PREFIX_START_TIME + VALID_START_TIME_CS2103;
    public static final String START_TIME_DESC_NUSSU = " " + PREFIX_START_TIME + VALID_START_TIME_NUSSU;
    public static final String END_TIME_DESC_CS2103 = " " + PREFIX_END_TIME + VALID_END_TIME_CS2103;
    public static final String END_TIME_DESC_NUSSU = " " + PREFIX_END_TIME + VALID_END_TIME_NUSSU;
    public static final String TAG_DESC_CS2103 = " " + PREFIX_TAG + VALID_TAG_CS2103;
    public static final String TAG_DESC_NUSSU = " " + PREFIX_TAG + VALID_TAG_NUSSU;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_TELEGRAM + "#"; // empty string not allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby/husband"; // '/' not allowed in tags
    public static final String INVALID_MEETING_NAME_DESC =
            " " + PREFIX_MEETING_NAME + "&"; // '&' not allowed in meeting names
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "09-11-2022aB"; // 'ab' not allowed in dates
    public static final String INVALID_START_TIME_DESC = " " + PREFIX_START_TIME + ":"; // empty string not allowed
    public static final String INVALID_END_TIME_DESC = " " + PREFIX_END_TIME + ":"; // empty string not allowed

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditContactCommand.EditPersonDescriptor DESC_AMY;
    public static final EditContactCommand.EditPersonDescriptor DESC_BOB;
    public static final EditMeetingCommand.EditMeetingDescriptor DESC_CS2103;
    public static final EditMeetingCommand.EditMeetingDescriptor DESC_NUSSU;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withTelegram(VALID_TELEGRAM_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_CS2103 = new EditMeetingDescriptorBuilder().withName(VALID_MEETING_NAME_CS2103)
                .withDate(VALID_DATE_CS2103).withStartTime(VALID_START_TIME_CS2103)
                .withEndTime(VALID_END_TIME_CS2103).withTags(VALID_TAG_CS2103).build();
        DESC_NUSSU = new EditMeetingDescriptorBuilder().withName(VALID_MEETING_NAME_NUSSU)
                .withDate(VALID_DATE_NUSSU).withStartTime(VALID_START_TIME_NUSSU)
                .withEndTime(VALID_END_TIME_NUSSU).withTags(VALID_TAG_NUSSU).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Contact> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Contact person = model.getFilteredPersonList().get(targetIndex.getZeroBased());

        final String[] splitName = person.getName().fullName.split("\\s+");
        final Set<Name> nameSet = new HashSet<>();
        for (String name : splitName) {
            nameSet.add(new Name(name));
        }
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(nameSet));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
