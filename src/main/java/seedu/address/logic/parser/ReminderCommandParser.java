package seedu.address.logic.parser;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.ReminderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.ReminderDatePredicate;

/**
 * Parses input argument and creates a new RemindCommand object
 */
public class ReminderCommandParser implements Parser<ReminderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemindCommand
     * and returns a RemindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public ReminderCommand parse(String args) throws ParseException {
        int days;
        try {
            days = ParserUtil.parseDays(args);
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ReminderCommand.MESSAGE_USAGE));
        }
        return new ReminderCommand(new ReminderDatePredicate(days));
    }
}
