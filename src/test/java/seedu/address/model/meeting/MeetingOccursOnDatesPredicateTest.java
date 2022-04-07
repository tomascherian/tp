package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

class MeetingOccursOnDatesPredicateTest {
    private final String firstSearchDate = "01/04/2022";
    private final String secondSearchDate = "22/05/2022";
    private final String thirdSearchDate = "18/06/2022";

    private final String differentFormatFirstSearchDate = "01-04-2022";

    private final MeetingDate firstMeetingDate = new MeetingDate(firstSearchDate);
    private final MeetingDate secondMeetingDate = new MeetingDate(secondSearchDate);
    private final MeetingDate thirdMeetingDate = new MeetingDate(thirdSearchDate);

    @Test
    public void equals() {
        Set<MeetingDate> firstPredicateDateSet = Collections.singleton(firstMeetingDate);
        Set<MeetingDate> secondPredicateDateSet = Set.of(firstMeetingDate, secondMeetingDate);

        MeetingOccursOnDatesPredicate firstPredicate = new MeetingOccursOnDatesPredicate(firstPredicateDateSet);
        MeetingOccursOnDatesPredicate secondPredicate = new MeetingOccursOnDatesPredicate(secondPredicateDateSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MeetingOccursOnDatesPredicate firstPredicateCopy =
                new MeetingOccursOnDatesPredicate(firstPredicateDateSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emptyKeywords_returnsTrue() {
        MeetingOccursOnDatesPredicate predicate = new MeetingOccursOnDatesPredicate(Collections.emptySet());
        assertTrue(predicate.test(new MeetingBuilder().build()));
    }

    @Test
    public void test_meetingOccursOnDates_returnsTrue() {
        // One date
        MeetingOccursOnDatesPredicate predicate =
                new MeetingOccursOnDatesPredicate(Collections.singleton(firstMeetingDate));
        assertTrue(predicate.test(new MeetingBuilder().withDate(firstSearchDate).build()));

        // Search for multiple dates
        predicate = new MeetingOccursOnDatesPredicate(Set.of(firstMeetingDate, secondMeetingDate));
        assertTrue(predicate.test(new MeetingBuilder().withDate(firstSearchDate).build()));
        assertTrue(predicate.test(new MeetingBuilder().withDate(secondSearchDate).build()));

        // Matching date, different format
        predicate = new MeetingOccursOnDatesPredicate(Set.of(firstMeetingDate));
        assertTrue(predicate.test(new MeetingBuilder().withDate(differentFormatFirstSearchDate).build()));
    }

    @Test
    public void test_meetingDoesNotOccurOnDate_returnsFalse() {
        // Non-matching date
        MeetingOccursOnDatesPredicate predicate =
                new MeetingOccursOnDatesPredicate(Set.of(secondMeetingDate, thirdMeetingDate));
        assertFalse(predicate.test(new MeetingBuilder().withDate(firstSearchDate).build()));
    }
}
