package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.ContactTagContainsKeywordsPredicate;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) for {@code FindContactCommand}.
 */
public class FindContactCommandTest {

    private static final Name NAME_MATCHING_ALICE = new Name("Alice Pauline");
    private static final Name NAME_MATCHING_BENSON = new Name("Benson Meier");
    private static final Tag TAG_MATCHING_ALICE = new Tag("friends");
    private static final Tag TAG_MATCHING_BENSON = new Tag("owesMoney");
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final NameContainsKeywordsPredicate emptyNamePredicate =
            new NameContainsKeywordsPredicate(Collections.emptySet());
    private final ContactTagContainsKeywordsPredicate emptyTagPredicate =
            new ContactTagContainsKeywordsPredicate(Collections.emptySet());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singleton(new Name("first")));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singleton(new Name("second")));

        ContactTagContainsKeywordsPredicate firstTagPredicate =
                new ContactTagContainsKeywordsPredicate(Collections.emptySet());
        ContactTagContainsKeywordsPredicate secondTagPredicate =
                new ContactTagContainsKeywordsPredicate(Set.of(new Tag("secondTag")));

        FindContactCommand findFirstCommand =
                new FindContactCommand(firstNamePredicate, firstTagPredicate);
        FindContactCommand findSecondCommand = new FindContactCommand(secondNamePredicate, secondTagPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindContactCommand findFirstCommandCopy = new FindContactCommand(firstNamePredicate, firstTagPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_searchByName_success() {

        // one name keyword specified
        NameContainsKeywordsPredicate namePredicate =
                new NameContainsKeywordsPredicate(Set.of(NAME_MATCHING_ALICE));
        FindContactCommand command = new FindContactCommand(namePredicate, emptyTagPredicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        expectedModel.updateFilteredPersonList(namePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        // multiple name keywords specified
        namePredicate =
                new NameContainsKeywordsPredicate(Set.of(NAME_MATCHING_ALICE, NAME_MATCHING_BENSON));
        command = new FindContactCommand(namePredicate, emptyTagPredicate);
        expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        expectedModel.updateFilteredPersonList(namePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_searchByTag_success() {
        // one tag keyword specified
        ContactTagContainsKeywordsPredicate tagPredicate =
                new ContactTagContainsKeywordsPredicate(Set.of(TAG_MATCHING_ALICE));
        FindContactCommand command = new FindContactCommand(emptyNamePredicate, tagPredicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        expectedModel.updateFilteredPersonList(tagPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        // multiple tag keywords specified
        tagPredicate =
                new ContactTagContainsKeywordsPredicate(Set.of(TAG_MATCHING_ALICE, TAG_MATCHING_BENSON));
        command = new FindContactCommand(emptyNamePredicate, tagPredicate);
        expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        expectedModel.updateFilteredPersonList(tagPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_searchByAll_success() {
        // all types matching
        NameContainsKeywordsPredicate namePredicate =
                new NameContainsKeywordsPredicate(Set.of(NAME_MATCHING_ALICE));
        ContactTagContainsKeywordsPredicate tagPredicate =
                new ContactTagContainsKeywordsPredicate(Set.of(TAG_MATCHING_ALICE));
        FindContactCommand command = new FindContactCommand(namePredicate, tagPredicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        expectedModel.updateFilteredPersonList(namePredicate.and(tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        // one type non-matching
        tagPredicate = new ContactTagContainsKeywordsPredicate(Set.of(TAG_MATCHING_BENSON));
        command = new FindContactCommand(namePredicate, tagPredicate);
        expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        expectedModel.updateFilteredPersonList(p -> false);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
