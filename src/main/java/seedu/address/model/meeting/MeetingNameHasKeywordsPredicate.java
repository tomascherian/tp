package seedu.address.model.meeting;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests whether a {@code Meeting's} name matches any of the {@code MeetingNames} given.
 * If no {@code MeetingName} is given, the test returns true.
 */
public class MeetingNameHasKeywordsPredicate implements Predicate<Meeting> {
    private final Set<MeetingName> nameKeywords;

    public MeetingNameHasKeywordsPredicate(Set<MeetingName> nameKeywords) {
        this.nameKeywords = nameKeywords;
    }

    @Override
    public boolean test(Meeting meeting) {
        if (nameKeywords.isEmpty()) {
            return true;
        }
        return nameKeywords.stream()
                .anyMatch(nameKeyword ->
                        StringUtil.containsPhraseIgnoreCase(meeting.getName().meetingName, nameKeyword.meetingName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingNameHasKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((MeetingNameHasKeywordsPredicate) other).nameKeywords)); // state check
    }

}
