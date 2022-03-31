package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.ContactTagContainsKeywordsPredicate;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindContactCommand object
 */
public class FindContactCommandParser implements Parser<FindContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindContactCommand
     * and returns a FindContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindContactCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG);

        if (!isAnyPrefix(argMultiMap, PREFIX_NAME, PREFIX_TAG) || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
        }

        Set<Name> nameKeywords = ParserUtil.parseNames(argMultiMap.getAllValues(PREFIX_NAME));
        Set<Tag> tagKeywords = ParserUtil.parseTags(argMultiMap.getAllValues(PREFIX_TAG));

        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(nameKeywords);
        ContactTagContainsKeywordsPredicate tagPredicate = new ContactTagContainsKeywordsPredicate(tagKeywords);

        return new FindContactCommand(namePredicate, tagPredicate);
    }

    private static boolean isAnyPrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
