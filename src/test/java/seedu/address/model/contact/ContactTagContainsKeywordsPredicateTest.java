package seedu.address.model.contact;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactTagContainsKeywordsPredicateTest {
    private final String firstTagName = "Family";
    private final String secondTagName = "Friends";
    private final String thirdTagName = "Colleagues";

    private final String mixedCaseFirstTagName = "fAmiLY";
    private final String mixedCaseSecondTagName = "friENDS";

    private final Tag firstTag = new Tag(firstTagName);
    private final Tag secondTag = new Tag(secondTagName);
    private final Tag thirdTag = new Tag(thirdTagName);

    @Test
    public void equals() {
        Set<Tag> firstPredicateKeywordSet = Collections.singleton(firstTag);
        Set<Tag> secondPredicateKeywordSet = Set.of(firstTag, secondTag);

        ContactTagContainsKeywordsPredicate firstPredicate =
                new ContactTagContainsKeywordsPredicate(firstPredicateKeywordSet);
        ContactTagContainsKeywordsPredicate secondPredicate =
                new ContactTagContainsKeywordsPredicate(secondPredicateKeywordSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContactTagContainsKeywordsPredicate firstPredicateCopy =
                new ContactTagContainsKeywordsPredicate(firstPredicateKeywordSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tag -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emptyKeywords_returnsTrue() {
        ContactTagContainsKeywordsPredicate predicate = new ContactTagContainsKeywordsPredicate(Collections.emptySet());
        assertTrue(predicate.test(new PersonBuilder().withTags(firstTagName).build()));
    }

    @Test
    public void test_tagHasKeywords_returnsTrue() {
        // One keyword, one tag
        ContactTagContainsKeywordsPredicate predicate = new ContactTagContainsKeywordsPredicate(Collections.singleton(firstTag));
        assertTrue(predicate.test(new PersonBuilder().withTags(firstTagName).build()));

        // One keyword, multiple tags
        predicate = new ContactTagContainsKeywordsPredicate(Collections.singleton(firstTag));
        assertTrue(predicate.test(new PersonBuilder().withTags(firstTagName, secondTagName).build()));

        // Multiple matching keywords
        predicate = new ContactTagContainsKeywordsPredicate(Set.of(firstTag, secondTag));
        assertTrue(predicate.test(new PersonBuilder().withTags(firstTagName + " " + secondTagName).build()));

        // Only one matching keyword
        predicate = new ContactTagContainsKeywordsPredicate(Set.of(firstTag, secondTag));
        assertTrue(predicate.test(new PersonBuilder().withTags(firstTagName).build()));

        // Mixed-case contact tag
        predicate = new ContactTagContainsKeywordsPredicate(Set.of(firstTag, secondTag));
        assertTrue(predicate.test(
                new PersonBuilder().withTags(mixedCaseFirstTagName, mixedCaseSecondTagName).build()));
    }

    @Test
    public void test_tagDoesNotHaveKeywords_returnsFalse() {
        // Non-matching keyword
        ContactTagContainsKeywordsPredicate predicate = new ContactTagContainsKeywordsPredicate(Set.of(thirdTag));
        assertFalse(predicate.test(new PersonBuilder().withTags(firstTagName, secondTagName).build()));

        // Non-matching keyword due to non-full match
        predicate = new ContactTagContainsKeywordsPredicate(Set.of(new Tag(firstTagName + " " + secondTagName)));
        assertFalse(predicate.test(new PersonBuilder().withTags(firstTagName, secondTagName).build()));
    }
}
