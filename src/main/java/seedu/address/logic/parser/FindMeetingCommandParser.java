package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPANTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindMeetingCommand object
 */
public class FindMeetingCommandParser implements Parser<FindMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindMeetingCommand
     * and returns a FindMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEETING_NAME, PREFIX_DATE, PREFIX_PARTICIPANTS, PREFIX_TAG);

        if (!isAnyPrefixPresent(argMultimap, PREFIX_MEETING_NAME, PREFIX_DATE, PREFIX_PARTICIPANTS, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMeetingCommand.MESSAGE_USAGE));
        }

        Set<MeetingName> nameKeywords = ParserUtil.parseMeetingNames(argMultimap.getAllValues(PREFIX_MEETING_NAME));
        Set<MeetingDate> dates = ParserUtil.parseMeetingDates(argMultimap.getAllValues(PREFIX_DATE));
        Set<Index> participantsIndex = ParserUtil.parseParticipants(argMultimap.getAllValues(PREFIX_PARTICIPANTS));
        Set<Tag> tagKeywords = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return new FindMeetingCommand(nameKeywords, dates, participantsIndex, tagKeywords);
    }

    /**
     * Returns true if at least one of the prefixes contains a non-empty {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isAnyPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
