package seedu.address.model.meeting;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Meeting}'s {@code Date} is within X days of the current date.
 */
public class ReminderDatePredicate implements Predicate<Meeting> {

    private long days;

    /**
     * Constructor for ReminderDatePredicate
     * @param days the number of days from the current date such that the
     * predicate will return true.
     */
    public ReminderDatePredicate(long days) {

        this.days = days;
    }

    public long getDays() {

        return this.days;
    }


    @Override
    public boolean test(Meeting meeting) {

        return limit(meeting);
    }

    /**
     * Returns true if the meeting is within X days of the current date.
     */
    public boolean limit(Meeting meeting) {
        LocalDate toTest = meeting.getDate().value;
        LocalDate dateToday = LocalDate.now();
        LocalDate acceptableDate = dateToday.plusDays(days + 1);
        return toTest.isAfter(dateToday.minusDays(1L)) && toTest.isBefore(acceptableDate)
                && !(meeting.getArchiveStatus().archiveStatus);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReminderDatePredicate // instanceof handles nulls
                && days == (((ReminderDatePredicate) other).days)); // state check
    }




}
