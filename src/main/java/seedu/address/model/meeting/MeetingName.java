package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Meeting name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class MeetingName {
    public static final String MESSAGE_CONSTRAINTS =
            "Meeting Name should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String meetingName;

    /**
     * Constructs a {@code meetingName}.
     *
     * @param name A valid name.
     */
    public MeetingName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        meetingName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return meetingName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingName // instanceof handles nulls
                && meetingName.equals(((MeetingName) other).meetingName)); // state check
    }

    @Override
    public int hashCode() {
        return meetingName.hashCode();
    }
}
