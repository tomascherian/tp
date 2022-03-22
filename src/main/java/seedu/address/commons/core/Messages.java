package seedu.address.commons.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_MEETING_DISPLAYED_INDEX = "The meeting index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_REMINDER = "REMINDER \n"
                                                   + "-----------\n"
                                                   + "As of today, "
                                                   + DateTimeFormatter.ofPattern(" dd MMM yyyy").format(LocalDate.now())
                                                   + " :- \n"
                                                   + " %1$d meeting/s are upcoming \n"
                                                    + " in %2$d day/s";

}
