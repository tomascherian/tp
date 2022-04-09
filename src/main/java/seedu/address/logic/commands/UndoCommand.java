package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Reverts the previous change to AddresSoc.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_INVALID_UNDO = "There are no more changes to be reverted.";
    public static final String MESSAGE_SUCCESS = "Previous change has been reverted.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canUndoAddressBook()) {
            throw new CommandException(MESSAGE_INVALID_UNDO);
        }
        model.undoAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
