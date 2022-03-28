package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class MeetingNameHasKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;

    public MeetingNameHasKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Meeting meeting) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsPhraseIgnoreCase(meeting.getName().meetingName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingNameHasKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MeetingNameHasKeywordsPredicate) other).keywords)); // state check
    }

}
