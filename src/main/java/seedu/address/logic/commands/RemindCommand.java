package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.model.Model;
import seedu.address.model.meeting.ReminderDatePredicate;

/**
 * Finds and lists all meetings whose date is within X days of the current date.
 *
 */

public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "reminder";

    public static final String MESSAGE_REMINDER = "REMINDER \n"
            + "-----------\n"
            + "As of today, "
            + DateTimeFormatter.ofPattern(" dd MMM yyyy").format(LocalDate.now())
            + " :- \n"
            + " %1$d meeting/s are upcoming \n"
            + " in %2$d day/s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all meetings which occur "
            + "within X days from the today, as a list.\n"
            + "Parameters: DAYS (Integer more than or equal to 0) \n"
            + "Example: " + COMMAND_WORD + " 3";

    private final ReminderDatePredicate predicate;


    /**
     * @param predicate of the number of days from the current date, to return true for meetings
     * which dates fall within the date range.
     */
    public RemindCommand(ReminderDatePredicate predicate) {
        assert predicate != null;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMeetingList(predicate);
        return new CommandResult(
                String.format(MESSAGE_REMINDER, model.getFilteredMeetingList().size(),
                        predicate.getDays()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemindCommand // instanceof handles nulls
                && predicate.equals(((RemindCommand) other).predicate)); // state check
    }
}
