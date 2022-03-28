package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

public class MeetingOccursOnDatesPredicate implements Predicate<Meeting> {
    private final List<MeetingDate> dates;

    public MeetingOccursOnDatesPredicate(List<MeetingDate> dates) {
        this.dates = dates;
    }

    @Override
    public boolean test(Meeting meeting) {
        return dates.stream().anyMatch(date -> date.equals(meeting.getDate()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingOccursOnDatesPredicate // instanceof handles nulls
                && dates.equals(((MeetingOccursOnDatesPredicate) other).dates)); // state check
    }

}
