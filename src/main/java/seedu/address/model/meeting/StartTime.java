package seedu.address.model.meeting;

/**
 * Represents a Meeting start time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class StartTime extends Time {

    /**
     * Constructs an {@code StartTime}.
     *
     * @param time A valid meeting start time.
     */
    public StartTime(String time) {
        super(time);
    }
}
