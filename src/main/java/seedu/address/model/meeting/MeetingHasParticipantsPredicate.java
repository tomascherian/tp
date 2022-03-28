package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

public class MeetingHasParticipantsPredicate implements Predicate<Meeting> {
    private final List<Participant> participants;

    public MeetingHasParticipantsPredicate(List<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public boolean test(Meeting meeting) {
        return participants.stream().anyMatch(participant ->meeting.hasParticipant(participant));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingHasParticipantsPredicate // instanceof handles nulls
                && participants.equals(((MeetingHasParticipantsPredicate) other).participants)); // state check
    }

}
