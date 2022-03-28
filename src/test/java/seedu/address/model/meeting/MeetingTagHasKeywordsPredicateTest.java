package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

public class MeetingTagHasKeywordsPredicateTest {


    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        MeetingTagHasKeywordsPredicate firstPredicate = new MeetingTagHasKeywordsPredicate(firstPredicateKeywordList);
        MeetingTagHasKeywordsPredicate secondPredicate = new MeetingTagHasKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MeetingTagHasKeywordsPredicate firstPredicateCopy = new MeetingTagHasKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword, one tag
        MeetingTagHasKeywordsPredicate predicate =
                new MeetingTagHasKeywordsPredicate(Collections.singletonList("Academic"));
        assertTrue(predicate.test(new MeetingBuilder().withTags("Academic Stuff").build()));

        // One keyword, multiple tags
        predicate = new MeetingTagHasKeywordsPredicate(Collections.singletonList("Academic"));
        assertTrue(predicate.test(new MeetingBuilder().withTags("Internship", "Academic").build()));

        // Multiple keywords
        predicate = new MeetingTagHasKeywordsPredicate(Arrays.asList("Academic", "Internship"));
        assertTrue(predicate.test(new MeetingBuilder().withTags("Academic Internship").build()));

        // Only one matching keyword
        predicate = new MeetingTagHasKeywordsPredicate(Arrays.asList("Academic", "Internship"));
        assertTrue(predicate.test(new MeetingBuilder().withTags("Internship").build()));

        // Mixed-case keywords
        predicate = new MeetingTagHasKeywordsPredicate(Arrays.asList("aCAdeMic", "InTernShip"));
        assertTrue(predicate.test(new MeetingBuilder().withTags("Academic Internship").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        MeetingTagHasKeywordsPredicate predicate = new MeetingTagHasKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withTags("Academic").build()));

        // Non-matching keyword
        predicate = new MeetingTagHasKeywordsPredicate(Arrays.asList("Leisure"));
        assertFalse(predicate.test(new MeetingBuilder().withTags("Academic", "Internship").build()));

        // Non-matching keyword due to whitespace
        predicate = new MeetingTagHasKeywordsPredicate(Arrays.asList("Academic Internship"));
        assertFalse(predicate.test(new MeetingBuilder().withTags("Academic", "Internship").build()));

        // Non-matching keyword due to non-full match
        predicate = new MeetingTagHasKeywordsPredicate(Arrays.asList("Academic Internship"));
        assertFalse(predicate.test(new MeetingBuilder().withTags("Academic Int", "mic Internship").build()));
    }
}
