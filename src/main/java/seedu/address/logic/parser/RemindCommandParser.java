package seedu.address.logic.parser;

import seedu.address.logic.commands.RemindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.ReminderDatePredicate;

/**
 * Parses input argument and creates a new RemindCommand object
 */
public class RemindCommandParser implements Parser<RemindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemindCommand
     * and returns a RemindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public RemindCommand parse(String args) throws ParseException {
        int days;
        try {
            days = ParserUtil.parseDays(args);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage());
        }
        return new RemindCommand(new ReminderDatePredicate(days));
    }
}
