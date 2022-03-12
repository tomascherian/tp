package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

import seedu.address.model.person.Person;

/**
 * Represents the Meeting participants in the address book.
 */
public class Participant {

    public final Person person;

    /**
     * Constructs an {@code ParticipantsList}.
     *
     * @param person A valid person.
     */
    public Participant(Person person) {
        requireNonNull(person);
        this.person = person;
    }

    @Override
    public String toString() {
        return person.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Participant // instanceof handles nulls
                && person.equals(((Participant) other).person)); // state check
    }

    @Override
    public int hashCode() {
        return person.hashCode();
    }
}
