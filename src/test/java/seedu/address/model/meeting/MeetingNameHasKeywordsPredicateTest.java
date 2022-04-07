package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

class MeetingNameHasKeywordsPredicateTest {
    private final String firstKeyword = "CS2103T";
    private final String secondKeyword = "NUSSU";
    private final String thirdKeyword = "CS2102";

    private final String mixedCaseFirstKeyword = "cS2103t";

    private final MeetingName firstMeetingName = new MeetingName(firstKeyword);
    private final MeetingName secondMeetingName = new MeetingName(secondKeyword);
    private final MeetingName thirdMeetingName = new MeetingName(thirdKeyword);


    @Test
    void equals() {
        Set<MeetingName> firstPredicateKeywordSet = Collections.singleton(firstMeetingName);
        Set<MeetingName> secondPredicateKeywordSet = Set.of(firstMeetingName, secondMeetingName);

        MeetingNameHasKeywordsPredicate firstPredicate = new MeetingNameHasKeywordsPredicate(firstPredicateKeywordSet);
        MeetingNameHasKeywordsPredicate secondPredicate =
                new MeetingNameHasKeywordsPredicate(secondPredicateKeywordSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MeetingNameHasKeywordsPredicate firstPredicateCopy =
                new MeetingNameHasKeywordsPredicate(firstPredicateKeywordSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    void test_emptyKeywords_returnsTrue() {
        MeetingNameHasKeywordsPredicate predicate = new MeetingNameHasKeywordsPredicate(Collections.emptySet());
        assertTrue(predicate.test(new MeetingBuilder().build()));
    }

    @Test
    void test_meetingNameHasKeywords_returnsTrue() {
        // One keyword
        MeetingNameHasKeywordsPredicate predicate =
                new MeetingNameHasKeywordsPredicate(Collections.singleton(firstMeetingName));
        assertTrue(predicate.test(new MeetingBuilder().withName(firstKeyword).build()));

        // Multiple keywords, Multiple matching keywords
        predicate = new MeetingNameHasKeywordsPredicate(Set.of(firstMeetingName, secondMeetingName));
        assertTrue(predicate.test(new MeetingBuilder().withName(firstKeyword + " " + secondKeyword).build()));

        // Multiple keywords, Only one matching keyword
        predicate = new MeetingNameHasKeywordsPredicate(Set.of(firstMeetingName, secondMeetingName));
        assertTrue(predicate.test(new MeetingBuilder().withName(firstKeyword).build()));

        // Mixed-case meeting name
        predicate = new MeetingNameHasKeywordsPredicate(Set.of(firstMeetingName));
        assertTrue(predicate.test(new MeetingBuilder().withName(mixedCaseFirstKeyword).build()));
    }

    @Test
    void test_meetingNameDoesNotHaveKeywords_returnsFalse() {
        // Non-matching keywords
        MeetingNameHasKeywordsPredicate predicate =
                new MeetingNameHasKeywordsPredicate(Set.of(secondMeetingName, thirdMeetingName));
        assertFalse(predicate.test(new MeetingBuilder().withName(firstKeyword).build()));

        // Non-matching keyword due to non-full match
        predicate = new MeetingNameHasKeywordsPredicate(Set.of(new MeetingName(firstKeyword + " " + secondKeyword)));
        assertFalse(predicate.test(new MeetingBuilder().withName(firstKeyword).build()));
    }
}
