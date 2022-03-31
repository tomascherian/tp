package seedu.address.model.meeting;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests whether a {@code Meeting's} tags matches any of the {@code Tags} given.
 * If no {@code Tag} is given, the test returns true.
 */
public class MeetingTagHasKeywordsPredicate implements Predicate<Meeting> {
    private final Set<Tag> tagKeywords;

    public MeetingTagHasKeywordsPredicate(Set<Tag> tagKeywords) {
        this.tagKeywords = tagKeywords;
    }

    @Override
    public boolean test(Meeting meeting) {
        if (tagKeywords.isEmpty()) {
            return true;
        }

        return tagKeywords.stream()
                .anyMatch(tagKeyword ->
                        meeting.getTags()
                                .stream()
                                .anyMatch(tag -> StringUtil.containsPhraseIgnoreCase(tag.tagName, tagKeyword.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingTagHasKeywordsPredicate // instanceof handles nulls
                && tagKeywords.equals(((MeetingTagHasKeywordsPredicate) other).tagKeywords)); // state check
    }

}
