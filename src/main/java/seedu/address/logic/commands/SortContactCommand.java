package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sorts all contacts in the address book.
 */
public class SortContactCommand extends Command {

    public static final String COMMAND_WORD = "sortc";

    public static final String MESSAGE_SUCCESS = "Sorted all contacts";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortContact();
        model.commitAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
