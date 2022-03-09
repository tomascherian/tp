package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Meeting end time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEndTime(String)}
 */
public class EndTime {

    public static final String MESSAGE_CONSTRAINTS = "Time should be of the format hhmm";

    public static final String VALIDATION_REGEX = "([0-1][0-9]|2[0-3])[0-5][0-9]";

    public final String value;

    /**
     * Constructs an {@code EndTime}.
     *
     * @param time A valid meeting end time.
     */
    public EndTime(String time) {
        requireNonNull(time);
        checkArgument(isValidEndTime(time), MESSAGE_CONSTRAINTS);
        value = time;
    }

    /**
     * Returns if a given string is a valid end time.
     */
    public static boolean isValidEndTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndTime // instanceof handles nulls
                && value.equals(((EndTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
