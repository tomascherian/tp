package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnarchiveMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ArchiveMeetingCommand object
 */
public class UnarchiveMeetingParser implements Parser<UnarchiveMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnarchiveMeetingCommand
     * and returns a UnarchiveMeetingCommand object for execution.
     * @throws ParseException if the user input does not have the expected format
     */
    public UnarchiveMeetingCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnarchiveMeetingCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnarchiveMeetingCommand.MESSAGE_USAGE), pe);
        }
    }

}
