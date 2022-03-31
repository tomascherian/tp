package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

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
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singleton(new Name("first")));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singleton(new Name("second")));
        ContactTagContainsKeywordsPredicate thirdPredicate =
                new ContactTagContainsKeywordsPredicate(Collections.<Tag>emptySet());
        FindContactCommand findFirstCommand = new FindContactCommand(firstPredicate, thirdPredicate);
        FindContactCommand findSecondCommand = new FindContactCommand(secondPredicate, thirdPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindContactCommand findFirstCommandCopy = new FindContactCommand(firstPredicate, thirdPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        List<String> list = Arrays.asList(userInput.split("\\s+"));

        final Set<Name> nameSet = new HashSet<>();
        for (String name : list) {
            nameSet.add(new Name(name));
        }
        return new NameContainsKeywordsPredicate(nameSet);
    }
}
