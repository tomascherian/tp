package seedu.address.model.meeting;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Tests whether a {@code Meeting's} date matches any of the {@code MeetingDates} given.
 * If no {@code MeetingDate} is given, the test returns true.
 */
public class MeetingOccursOnDatesPredicate implements Predicate<Meeting> {
    private final Set<MeetingDate> dates;

    public MeetingOccursOnDatesPredicate(Set<MeetingDate> dates) {
        this.dates = dates;
    }

    @Override
    public boolean test(Meeting meeting) {
        if (dates.isEmpty()) {
            return true;
        }
        return dates.stream().anyMatch(date -> date.equals(meeting.getDate()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingOccursOnDatesPredicate // instanceof handles nulls
                && dates.equals(((MeetingOccursOnDatesPredicate) other).dates)); // state check
    }

}
