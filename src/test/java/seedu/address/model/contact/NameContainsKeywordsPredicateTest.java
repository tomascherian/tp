package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameContainsKeywordsPredicateTest {
    private final String firstKeyword = "Alice";
    private final String secondKeyword = "Benson";
    private final String thirdKeyword = "Carl";
    private final String mixedCaseFirstKeyword = "aLiCE";

    private final Name firstContactName = new Name(firstKeyword);
    private final Name secondContactName = new Name(secondKeyword);
    private final Name thirdContactName = new Name(thirdKeyword);

    @Test
    public void equals() {
        Set<Name> firstPredicateKeywordList = Collections.singleton(firstContactName);
        Set<Name> secondPredicateKeywordList = Set.of(firstContactName, secondContactName);

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy =
                new NameContainsKeywordsPredicate(firstPredicateKeywordList);
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
        // One keyword
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singleton(firstContactName));
        assertTrue(predicate.test(new PersonBuilder().withName(firstKeyword).build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Set.of(firstContactName, secondContactName));
        assertTrue(predicate.test(new PersonBuilder().withName(firstKeyword + " " + secondKeyword).build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Set.of(secondContactName, thirdContactName));
        assertTrue(predicate.test(new PersonBuilder().withName(firstKeyword + " " + thirdKeyword).build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Set.of(firstContactName));
        assertTrue(predicate.test(new PersonBuilder().withName(mixedCaseFirstKeyword).build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Set.of(new Name("aL")));
        assertFalse(predicate.test(new PersonBuilder().withName(firstKeyword).build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Set.of(thirdContactName));
        assertFalse(predicate.test(new PersonBuilder().withName(firstKeyword + " " + secondKeyword).build()));

        // Keywords match phone, email and telegram, but does not match name
        predicate = new NameContainsKeywordsPredicate(Set.of(new Name("Alicia"),
                new Name("kry02")));
        assertFalse(predicate.test(new PersonBuilder().withName(firstKeyword).withPhone("12345")
                .withEmail("alice@email.com").withTelegram("kry02").build()));
    }
}
