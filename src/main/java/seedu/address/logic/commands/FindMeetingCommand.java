package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPANTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingHasParticipantsPredicate;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.MeetingNameHasKeywordsPredicate;
import seedu.address.model.meeting.MeetingOccursOnDatesPredicate;
import seedu.address.model.meeting.MeetingTagHasKeywordsPredicate;
import seedu.address.model.meeting.Participant;
import seedu.address.model.tag.Tag;

/**
 * Finds and lists all meetings that satisfy the search criteria.
 */
public class FindMeetingCommand extends Command {

    public static final String COMMAND_WORD = "findm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meetings that matches the given parameters."
            + "Parameters (please specify at least one): "
            + "[" + PREFIX_MEETING_NAME + "MEETING NAME]... "
            + "[" + PREFIX_DATE + "DATE]... "
            + "[" + PREFIX_PARTICIPANTS + "PARTICIPANTS]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "05-07-2022 ";

    private final Set<MeetingName> nameKeywords;
    private final Set<MeetingDate> dates;
    private final Set<Index> participantsIndex;
    private final Set<Tag> tagKeywords;

    /**
     * Creates a {@code FindMeetingCommand} to find Meetings that match at least one item
     * from each non-empty {@code Set} provided as argument.
     */
    public FindMeetingCommand(Set<MeetingName> nameKeywords, Set<MeetingDate> dates,
                              Set<Index> participantsIndex, Set<Tag> tagKeywords) {
        this.nameKeywords = nameKeywords;
        this.dates = dates;
        this.participantsIndex = participantsIndex;
        this.tagKeywords = tagKeywords;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Set<Participant> participants = getParticipantsFromContactList(model.getFilteredPersonList());

        MeetingHasParticipantsPredicate participantsPredicate = new MeetingHasParticipantsPredicate(participants);
        MeetingNameHasKeywordsPredicate namePredicate = new MeetingNameHasKeywordsPredicate(nameKeywords);
        MeetingOccursOnDatesPredicate datePredicate = new MeetingOccursOnDatesPredicate(dates);
        MeetingTagHasKeywordsPredicate tagPredicate = new MeetingTagHasKeywordsPredicate(tagKeywords);

        model.updateFilteredMeetingList(participantsPredicate.and(namePredicate).and(datePredicate).and(tagPredicate));

        return new CommandResult(
                String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, model.getFilteredMeetingList().size()));
    }

    private Set<Participant> getParticipantsFromContactList(List<Contact> lastShownList) throws CommandException {
        requireNonNull(lastShownList);

        Set<Participant> participants = new HashSet<>();
        for (Index targetIndex : participantsIndex) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Contact participatingContact = lastShownList.get(targetIndex.getZeroBased());
            participants.add(new Participant(participatingContact));
        }

        return participants;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindMeetingCommand // instanceof handles nulls
                && nameKeywords.equals(((FindMeetingCommand) other).nameKeywords)
                && dates.equals(((FindMeetingCommand) other).dates)
                && participantsIndex.equals(((FindMeetingCommand) other).participantsIndex)
                && tagKeywords.equals(((FindMeetingCommand) other).tagKeywords)); // state check
    }
}
