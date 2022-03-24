package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class SortMCommand extends Command {

    public static final String COMMAND_WORD = "sortm";

    public static final String MESSAGE_SUCCESS = "Sorted all meetings based on date and time";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortMeeting();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
