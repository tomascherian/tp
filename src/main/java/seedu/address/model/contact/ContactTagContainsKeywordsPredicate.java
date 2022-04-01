package seedu.address.model.contact;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

public class ContactTagContainsKeywordsPredicate implements Predicate<Contact> {
    private final Set<Tag> tagKeywords;

    public ContactTagContainsKeywordsPredicate(Set<Tag> tagKeywords) {
        this.tagKeywords = tagKeywords;
    }

    @Override
    public boolean test(Contact person) {
        if (tagKeywords.isEmpty()) {
            return true;
        }

        return tagKeywords.stream()
                .anyMatch(tagKeyword ->
                        person.getTags().stream()
                .anyMatch(tag -> StringUtil.containsPhraseIgnoreCase(tag.tagName, tagKeyword.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactTagContainsKeywordsPredicate // instanceof handles nulls
                && tagKeywords.equals(((ContactTagContainsKeywordsPredicate) other).tagKeywords)); // state check
    }
}
