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

public class UnarchiveMeetingCommand extends Command {

    public static final String COMMAND_WORD = "unarchive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unarchives the meeting identified by the index.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNARCHIVE_MEETING_SUCCESS = "Unarchived Meeting: %1$s";

    public static final String MESSAGE_ALREADY_UNARCHIVED = "This meeting is not in the archive list";

    private final Index targetIndex;

    /**
     * Constructs an UnarchiveMeetingCommand.
     *
     * @param targetIndex the index number shown in the  meeting list.
     */
    public UnarchiveMeetingCommand(Index targetIndex) {
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

        if (!currentState.isArchive) {
            throw new CommandException(String.format(MESSAGE_ALREADY_UNARCHIVED,
                    meetingToArchive));
        }

        Meeting tounarchiveMeeting = meetingToArchive.unarchive();
        model.setMeeting(meetingToArchive, tounarchiveMeeting);
        model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(String.format(MESSAGE_UNARCHIVE_MEETING_SUCCESS, meetingToArchive.getName()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnarchiveMeetingCommand // instanceof handles nulls
                && targetIndex.equals(((UnarchiveMeetingCommand) other).targetIndex)); // state check
    }
}
