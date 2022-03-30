package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Reverts the previous Undo.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_INVALID_REDO = "There is no Undo to be reverted.";
    public static final String MESSAGE_SUCCESS = "Previous Undo has been reverted.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canRedoAddressBook()) {
            throw new CommandException(MESSAGE_INVALID_REDO);
        }
        model.redoAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

