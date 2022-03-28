package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ARCHIVED_MEETINGS;

import seedu.address.model.Model;



/**
 * Shows all archived meetings.
 */
public class ListArchivedCommand extends Command {

    public static final String COMMAND_WORD = "archivelist";

    public static final String MESSAGE_SUCCESS = "Listed all archived meetings in meeting list.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_ARCHIVED_MEETINGS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
