package seedu.address.model.meeting;

import java.util.Comparator;

public class MeetingComparator implements Comparator<Meeting> {

    /** override the compare() method
     *
     * @param s1
     * @param s2
     * @returns a negative, zero or positive integer based on whether the first argument is
     * less than, equal to or greater than the second.
     */

    @Override
    public int compare(Meeting s1, Meeting s2) {

        final int dateCompare = s1.getDate().value.compareTo(s2.getDate().value);
        if (dateCompare != 0) {
            return dateCompare;
        }
        return s1.getStartTime().value.compareTo(s2.getStartTime().value);
    }

}
