package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.MeetingBuilder;

public class MeetingTagHasKeywordsPredicateTest {
    private final String firstTagName = "Academic";
    private final String secondTagName = "Internship";
    private final String thirdTagName = "Leisure";

    private final String mixedCaseFirstTagName = "AcaDeMiC";
    private final String mixedCaseSecondTagName = "iNTernShIp";

    private final Tag firstTag = new Tag(firstTagName);
    private final Tag secondTag = new Tag(secondTagName);
    private final Tag thirdTag = new Tag(thirdTagName);

    @Test
    public void equals() {
        Set<Tag> firstPredicateKeywordSet = Collections.singleton(firstTag);
        Set<Tag> secondPredicateKeywordSet = Set.of(firstTag, secondTag);

        MeetingTagHasKeywordsPredicate firstPredicate = new MeetingTagHasKeywordsPredicate(firstPredicateKeywordSet);
        MeetingTagHasKeywordsPredicate secondPredicate = new MeetingTagHasKeywordsPredicate(secondPredicateKeywordSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MeetingTagHasKeywordsPredicate firstPredicateCopy =
                new MeetingTagHasKeywordsPredicate(firstPredicateKeywordSet);
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
        MeetingTagHasKeywordsPredicate predicate = new MeetingTagHasKeywordsPredicate(Collections.emptySet());
        assertTrue(predicate.test(new MeetingBuilder().withTags(firstTagName).build()));
    }

    @Test
    public void test_tagHasKeywords_returnsTrue() {
        // One keyword, one tag
        MeetingTagHasKeywordsPredicate predicate = new MeetingTagHasKeywordsPredicate(Collections.singleton(firstTag));
        assertTrue(predicate.test(new MeetingBuilder().withTags(firstTagName).build()));

        // One keyword, multiple tags
        predicate = new MeetingTagHasKeywordsPredicate(Collections.singleton(firstTag));
        assertTrue(predicate.test(new MeetingBuilder().withTags(firstTagName, secondTagName).build()));

        // Multiple matching keywords
        predicate = new MeetingTagHasKeywordsPredicate(Set.of(firstTag, secondTag));
        assertTrue(predicate.test(new MeetingBuilder().withTags(firstTagName + " " + secondTagName).build()));

        // Only one matching keyword
        predicate = new MeetingTagHasKeywordsPredicate(Set.of(firstTag, secondTag));
        assertTrue(predicate.test(new MeetingBuilder().withTags(firstTagName).build()));

        // Mixed-case meeting tag
        predicate = new MeetingTagHasKeywordsPredicate(Set.of(firstTag, secondTag));
        assertTrue(predicate.test(
                new MeetingBuilder().withTags(mixedCaseFirstTagName, mixedCaseSecondTagName).build()));
    }

    @Test
    public void test_tagDoesNotHaveKeywords_returnsFalse() {

        // Non-matching keyword
        MeetingTagHasKeywordsPredicate predicate = new MeetingTagHasKeywordsPredicate(Set.of(thirdTag));
        assertFalse(predicate.test(new MeetingBuilder().withTags(firstTagName, secondTagName).build()));

        // Non-matching keyword due to non-full match
        predicate = new MeetingTagHasKeywordsPredicate(Set.of(new Tag(firstTagName + " " + secondTagName)));
        assertFalse(predicate.test(new MeetingBuilder().withTags(firstTagName, secondTagName).build()));
    }
}
