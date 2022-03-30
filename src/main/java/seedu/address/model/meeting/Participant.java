package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.contact.Contact;

/**
 * Represents the Meeting participants in the address book.
 */
public class Participant {

    public final Contact contact;

    /**
     * Constructs an {@code ParticipantsList}.
     *
     * @param contact A valid contact.
     */
    public Participant(Contact contact) {
        requireNonNull(contact);
        this.contact = contact;
    }

    @Override
    public String toString() {
        return contact.toString();
    }

    /**
     * Returns true if this participant's {@code contact} shares the same name and at
     * least one other identity field as the {@code other} participant's {@code contact}.
     * This defines a weaker notion of equality between two participants.
     */
    public boolean isSameParticipant(Participant other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.contact.isSameContact(this.contact);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Participant // instanceof handles nulls
                && contact.equals(((Participant) other).contact)); // state check
    }

    @Override
    public int hashCode() {
        return contact.hashCode();
    }
}
