package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Telegram {


    public static final String MESSAGE_CONSTRAINTS =
            "Telegram id should contain alphanumeric characters and underscore, and it should not be empty";

    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9_]*$";
    public final String telegramId;

    /**
     * Constructs a {@code Telegram id}.
     *
     * @param id A valid telegram handle.
     */
    public Telegram(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        telegramId = id;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX) && !test.isEmpty();
    }

    @Override
    public String toString() {
        return this.telegramId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && telegramId.equals(((Telegram) other).telegramId)); // state check
    }

    @Override
    public int hashCode() {
        return telegramId.hashCode();
    }

}
