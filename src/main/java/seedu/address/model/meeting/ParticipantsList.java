package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Meeting participants in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidParticipantsList(String)}
 */
public class ParticipantsList {
    public static final String MESSAGE_CONSTRAINTS = "Participants list should not be blank";

    public static final String VALIDATION_REGEX = "[\\d+ ]+";

    public final String value;

    /**
     * Constructs an {@code Pa}.
     *
     * @param list A valid list.
     */
    public ParticipantsList(String list) {
        requireNonNull(list);
        checkArgument(isValidParticipantsList(list), MESSAGE_CONSTRAINTS);
        value = list;
    }

    /**
     * Returns true if a given string is a valid list.
     */
    public static boolean isValidParticipantsList(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParticipantsList // instanceof handles nulls
                && value.equals(((ParticipantsList) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
