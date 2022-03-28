package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class MeetingTagHasKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> tagKeywords;

    public MeetingTagHasKeywordsPredicate(List<String> tagKeywords) {
        this.tagKeywords = tagKeywords;
    }

    @Override
    public boolean test(Meeting meeting) {
        return tagKeywords.stream()
                .anyMatch(keyword -> meeting.getTags().stream()
                        .anyMatch(tag -> StringUtil.containsPhraseIgnoreCase(tag.tagName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingTagHasKeywordsPredicate // instanceof handles nulls
                && tagKeywords.equals(((MeetingTagHasKeywordsPredicate) other).tagKeywords)); // state check
    }

}
