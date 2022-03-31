package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.MeetingNameHasKeywordsPredicate;
import seedu.address.model.meeting.MeetingOccursOnDatesPredicate;
import seedu.address.model.meeting.MeetingTagHasKeywordsPredicate;

/**
 * Finds and lists all meetings that satisfy the search criteria.
 */
public class FindMeetingCommand extends Command {

    public static final String COMMAND_WORD = "findm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meetings that matches the given parameters."
            + "Parameters (please specify at least one): "
            + "[" + PREFIX_MEETING_NAME + "MEETING NAME]... "
            + "[" + PREFIX_DATE + "DATE]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "05-07-2022 ";

    private final MeetingNameHasKeywordsPredicate namePredicate;
    private final MeetingOccursOnDatesPredicate datePredicate;
    private final MeetingTagHasKeywordsPredicate tagPredicate;

    /**
     * Creates a {@code FindMeetingCommand} to find Meetings that match at least one item
     * from each non-empty {@code Set} provided as argument.
     */
    public FindMeetingCommand(MeetingNameHasKeywordsPredicate namePredicate,
                              MeetingOccursOnDatesPredicate datePredicate,
                              MeetingTagHasKeywordsPredicate tagPredicate) {
        this.namePredicate = namePredicate;
        this.datePredicate = datePredicate;
        this.tagPredicate = tagPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredMeetingList(namePredicate.and(datePredicate).and(tagPredicate));

        return new CommandResult(
                String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, model.getFilteredMeetingList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindMeetingCommand // instanceof handles nulls
                && namePredicate.equals(((FindMeetingCommand) other).namePredicate)
                && datePredicate.equals(((FindMeetingCommand) other).datePredicate)
                && tagPredicate.equals(((FindMeetingCommand) other).tagPredicate)); // state check
    }
}
