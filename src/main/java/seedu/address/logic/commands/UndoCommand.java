package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Undo previous change to AddresSoc.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Previous change to AddresSoc has been reverted.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.undoAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
