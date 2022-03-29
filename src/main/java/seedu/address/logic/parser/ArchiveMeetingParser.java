package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ArchiveMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ArchiveMeetingCommand object
 */
public class ArchiveMeetingParser implements Parser<ArchiveMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ArchiveMeetingCommand
     * and returns a ArchiveMeetingCommand object for execution.
     * @throws ParseException if the user input does not have the expected format
     */
    public ArchiveMeetingCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ArchiveMeetingCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ArchiveMeetingCommand.MESSAGE_USAGE), pe);
        }
    }

}
