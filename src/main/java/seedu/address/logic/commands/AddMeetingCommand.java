package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPANTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.ArchiveStatus;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Participant;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.tag.Tag;

/**
 * Adds a meeting to the meeting list.
 */

public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "addm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the meeting list. "
            + "Parameters: "
            + PREFIX_MEETING_NAME + "MEETING NAME "
            + PREFIX_DATE + "DATE "
            + PREFIX_START_TIME + "START TIME "
            + PREFIX_END_TIME + "END TIME "
            + "[" + PREFIX_PARTICIPANTS + "PARTICIPANTS]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MEETING_NAME + "2103 project "
            + PREFIX_DATE + "12/03/2022 "
            + PREFIX_START_TIME + "1600 "
            + PREFIX_END_TIME + "1800 "
            + PREFIX_TAG + "discussion for v1.2";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the meeting list";
    public static final String MESSAGE_INVALID_TIME = "Meeting end time should be later meeting start time";

    private final MeetingName meetingName;
    private final MeetingDate meetingDate;
    private final StartTime startTime;
    private final EndTime endTime;
    private final ArchiveStatus archiveStatus;
    private final Set<Tag> tagList;
    private final Set<Index> participantsIndex;
    private Meeting toAdd;

    /**
     * Creates an AddMeetingCommand to add the specified {@code Meeting}
     */

    public AddMeetingCommand(MeetingName meetingName, MeetingDate meetingDate, StartTime startTime, EndTime endTime, ArchiveStatus archiveStatus,
            Set<Index> participantsIndex, Set<Tag> tagList) {
        requireNonNull(meetingName);
        requireNonNull(meetingDate);
        requireNonNull(startTime);
        requireNonNull(endTime);
        requireNonNull(archiveStatus);
        requireNonNull(tagList);
        requireNonNull(participantsIndex);

        this.meetingName = meetingName;
        this.meetingDate = meetingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.archiveStatus = archiveStatus;
        this.tagList = tagList;
        this.participantsIndex = participantsIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Contact> lastShownList = model.getFilteredPersonList();
        final Set<Participant> participants = new HashSet<>();

        if (startTime.isAfter(endTime)) {
            throw new CommandException(MESSAGE_INVALID_TIME);
        }

        for (Index targetIndex : participantsIndex) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Contact participatingContact = lastShownList.get(targetIndex.getZeroBased());
            participants.add(new Participant(participatingContact));
        }

        toAdd = new Meeting(meetingName, meetingDate, startTime, endTime, participants, archiveStatus, tagList);

        if (model.hasMeeting(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.addMeeting(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMeetingCommand // instanceof handles nulls
                && toAdd.equals(((AddMeetingCommand) other).toAdd));
    }
}
