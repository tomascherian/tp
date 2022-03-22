package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


/**
 * Represents a Meeting date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class MeetingDate {

    public static final String MESSAGE_CONSTRAINTS = "Dates should be of the format DD/MM/YYYY or DD-MM-YYYY"
                                                     + " and the date should have a valid day, month and year. \n";


    private static final DateTimeFormatter formats = DateTimeFormatter.ofPattern("[dd/MM/uuuu][dd-MM-uuuu]");

    public final LocalDate value;

    /**
     * Constructs an {@code MeetingDate}.
     *
     * @param date A valid meeting date.
     */
    public MeetingDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(date, formats);
    }

    /**
     * checks whether the given date is valid
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, formats.withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public LocalDate getValue() {

        return this.value;
    }


    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
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
