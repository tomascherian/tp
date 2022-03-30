package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingArchiveStatus;

/**
 * Archives a meeting using the meeting index
 */

public class ArchiveMeetingCommand extends Command {

    public static final String COMMAND_WORD = "archive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
                                             + ": Archives the meeting identified by the index.\n"
                                             + "Parameters: INDEX (must be a positive integer)\n"
                                             + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ARCHIVE_MEETING_SUCCESS = "Archived Meeting: %1$s";

    public static final String MESSAGE_ALREADY_ARCHIVED = "This meeting has already been archived"
                                                         + "\nTo view all archived meetings, use command 'archivelist'";

    private final Index targetIndex;

    /**
     * Constructs an ArchiveMeetingCommand.
     *
     * @param targetIndex the index number shown in the  meeting list.
     */
    public ArchiveMeetingCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();


        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToArchive = lastShownList.get(targetIndex.getZeroBased());
        MeetingArchiveStatus currentState = meetingToArchive.getArchiveStatus();

        if (currentState.archiveStatus) {
            throw new CommandException(String.format(MESSAGE_ALREADY_ARCHIVED,
                    meetingToArchive));
        }

        Meeting toarchiveMeeting = meetingToArchive.archive();
        model.setMeeting(meetingToArchive, toarchiveMeeting);
        model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_ARCHIVE_MEETING_SUCCESS, meetingToArchive.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchiveMeetingCommand // instanceof handles nulls
                && targetIndex.equals(((ArchiveMeetingCommand) other).targetIndex)); // state check
    }
}
