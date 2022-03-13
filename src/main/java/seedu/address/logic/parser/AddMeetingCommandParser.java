package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPANTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddMeetingCommand object
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddMeetingCommand
     * and returns an AddMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEETING_NAME, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME,
                        PREFIX_PARTICIPANTS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_MEETING_NAME, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        // UPDATE WITH MODEL'S CLASSES
        MeetingName meetingName = ParserUtil.parseMeetingName(argMultimap.getValue(PREFIX_MEETING_NAME).get());
        MeetingDate date = ParserUtil.parseMeetingDate(argMultimap.getValue(PREFIX_DATE).get());
        StartTime startTime = ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_START_TIME).get());
        EndTime endTime = ParserUtil.parseEndTime(argMultimap.getValue(PREFIX_END_TIME).get());
        Set<Index> participantsIndex = ParserUtil.parseParticipants(argMultimap.getAllValues(PREFIX_PARTICIPANTS));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return new AddMeetingCommand(meetingName, date, startTime, endTime, participantsIndex, tagList);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
