package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Meeting date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class MeetingDate {

    public static final String MESSAGE_CONSTRAINTS = "Dates should be of the format DD/MM/YYYY";

    public static final String VALIDATION_REGEX = "([0-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/((19|20)\\d{2})";

    public final String value;

    /**
     * Constructs an {@code MeetingDate}.
     *
     * @param date A valid meeting date.
     */
    public MeetingDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingDate // instanceof handles nulls
                && value.equals(((MeetingDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
