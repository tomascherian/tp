package seedu.address.model.meeting;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Tests whether a {@code Meeting} has any of the {@code Participants} given.
 * If no {@code Participant} is given, the test returns true.
 */
public class MeetingHasParticipantsPredicate implements Predicate<Meeting> {
    private final Set<Participant> participants;

    public MeetingHasParticipantsPredicate(Set<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public boolean test(Meeting meeting) {
        if (participants.isEmpty()) {
            return true;
        }
        return participants.stream().anyMatch(participant -> meeting.hasParticipant(participant));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingHasParticipantsPredicate // instanceof handles nulls
                && participants.equals(((MeetingHasParticipantsPredicate) other).participants)); // state check
    }

}
