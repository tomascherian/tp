package seedu.address.model.contact;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Contact> {
    private final Set<Name> nameKeywords;

    public NameContainsKeywordsPredicate(Set<Name> nameKeywords) {
        this.nameKeywords = nameKeywords;
    }

    @Override
    public boolean test(Contact person) {
        if (nameKeywords.isEmpty()) {
            return true;
        }

        return nameKeywords.stream()
                .anyMatch(nameKeyword ->
                        StringUtil.containsPhraseIgnoreCase(person.getName().fullName, nameKeyword.fullName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((NameContainsKeywordsPredicate) other).nameKeywords)); // state check
    }

}
