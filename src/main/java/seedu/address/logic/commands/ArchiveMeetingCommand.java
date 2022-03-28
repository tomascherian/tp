package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.ArchiveStatus;

/**
 * Snoozes a meeting using the meeting index
 */

public class ArchiveMeetingCommand extends Command {

    public static final String COMMAND_WORD = "archive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
                                             + ": Archives the meeting identified by the index number in the displayed meeting list.\n"
                                             + "Parameters: INDEX (must be a positive integer)\n"
                                             + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ARCHIVE_MEETING_SUCCESS = "Archived Meeting: %1$s";

    public static final String MESSAGE_ALREADY_ARCHIVED = "This meeting has already been archived"
                                                         + "/n To view all archive meetings, use command 'archivelist'. ";
    public static final String MESSAGE_INVALID_INDEX = "The given index is invalid";

    private final Index targetIndex;

    /**
     * Constructs an SnoozeMeetingCommand.
     *
     * @param targetIndex the index number shown in the displayed meeting list.
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
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }

        Meeting meetingToArchive = lastShownList.get(targetIndex.getZeroBased());
        ArchiveStatus currentStateOfPerson = meetingToArchive.getArchiveStatus();

        if (currentStateOfPerson.archiveStatus) {
            throw new CommandException(String.format(MESSAGE_ALREADY_ARCHIVED,
                    meetingToArchive));
        }

        Meeting snoozedMeeting = meetingToArchive.archive();
        model.setMeeting(meetingToArchive, snoozedMeeting);
        model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_ACTIVE_MEETINGS);
        return new CommandResult(String.format(MESSAGE_ARCHIVE_MEETING_SUCCESS, meetingToArchive.getName()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchiveMeetingCommand // instanceof handles nulls
                && targetIndex.equals(((ArchiveMeetingCommand) other).targetIndex)); // state check
    }
}
