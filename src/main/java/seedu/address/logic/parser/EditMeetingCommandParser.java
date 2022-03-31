package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPANTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditContactCommand;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

public class EditMeetingCommandParser implements Parser<EditMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditMeetingCommand
     * and returns an EditMeetingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEETING_NAME, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME,
                        PREFIX_PARTICIPANTS, PREFIX_TAG);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new
                    ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE), pe);
        }

        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();
        if (argMultiMap.getValue(PREFIX_MEETING_NAME).isPresent()) {
            editMeetingDescriptor
                    .setMeetingName(ParserUtil.parseMeetingName(argMultiMap.getValue(PREFIX_MEETING_NAME).get()));
        }
        if (argMultiMap.getValue(PREFIX_DATE).isPresent()) {
            editMeetingDescriptor
                    .setMeetingDate(ParserUtil.parseMeetingDate(argMultiMap.getValue(PREFIX_DATE).get()));
        }
        if (argMultiMap.getValue(PREFIX_START_TIME).isPresent()) {
            editMeetingDescriptor
                    .setStartTime(ParserUtil.parseStartTime(argMultiMap.getValue(PREFIX_START_TIME).get()));
        }
        if (argMultiMap.getValue(PREFIX_END_TIME).isPresent()) {
            editMeetingDescriptor
                    .setEndTime(ParserUtil.parseEndTime(argMultiMap.getValue(PREFIX_END_TIME).get()));
        }
        parseTagsForEdit(argMultiMap.getAllValues(PREFIX_TAG)).ifPresent(editMeetingDescriptor::setTags);
        parseParticipantsIndexForEdit(argMultiMap.getAllValues(PREFIX_PARTICIPANTS))
                .ifPresent((editMeetingDescriptor::setParticipantsIndex));

        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditContactCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMeetingCommand(index, editMeetingDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> participants} into a {@code Set<Index>} if {@code participants} is non-empty.
     * If {@code participants} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Index>} containing zero participants.
     */
    private Optional<Set<Index>> parseParticipantsIndexForEdit(Collection<String> participants) throws ParseException {
        assert participants != null;

        if (participants.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> indexes =
                participants.size() == 1 && participants.contains("") ? Collections.emptySet() : participants;
        return Optional.of(ParserUtil.parseParticipants(indexes));
    }
}
