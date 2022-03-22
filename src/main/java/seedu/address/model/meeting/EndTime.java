package seedu.address.model.meeting;

/**
 * Represents a Meeting end time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class EndTime extends Time {

    /**
     * Constructs an {@code EndTime}.
     *
     * @param time A valid meeting end time.
     */
    public EndTime(String time) {
        super(time);
    }
}
