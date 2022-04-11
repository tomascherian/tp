package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEETING_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_NAME_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_NUSSU;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_NAME_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NUSSU;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditMeetingDescriptorBuilder;

public class EditMeetingCommandParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE);
    private EditMeetingCommandParser parser = new EditMeetingCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_MEETING_NAME_CS2103, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditMeetingCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + MEETING_NAME_DESC_CS2103, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + MEETING_NAME_DESC_CS2103, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_MEETING_NAME_DESC, MeetingName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, MeetingDate.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_START_TIME_DESC, StartTime.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_END_TIME_DESC, EndTime.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_DATE_DESC + START_TIME_DESC_CS2103, MeetingDate.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DATE_DESC_CS2103 + INVALID_DATE_DESC, MeetingDate.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_CS2103 + TAG_DESC_NUSSU + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_CS2103 + TAG_EMPTY + TAG_DESC_NUSSU, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_CS2103 + TAG_DESC_NUSSU, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_MEETING_NAME_DESC + INVALID_START_TIME_DESC
                        + VALID_END_TIME_CS2103 + VALID_DATE_CS2103,
                MeetingName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_MEETING;
        String userInput = targetIndex.getOneBased() + MEETING_NAME_DESC_CS2103 + TAG_DESC_CS2103
                + DATE_DESC_CS2103 + START_TIME_DESC_CS2103 + END_TIME_DESC_CS2103 + TAG_DESC_NUSSU;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withName(VALID_MEETING_NAME_CS2103)
                .withDate(VALID_DATE_CS2103).withStartTime(VALID_START_TIME_CS2103).withEndTime(VALID_END_TIME_CS2103)
                .withTags(VALID_TAG_CS2103, VALID_TAG_NUSSU).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_MEETING;
        String userInput = targetIndex.getOneBased() + DATE_DESC_CS2103 + START_TIME_DESC_CS2103;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withDate(VALID_DATE_CS2103)
                .withStartTime(VALID_START_TIME_CS2103).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_MEETING;
        String userInput = targetIndex.getOneBased() + MEETING_NAME_DESC_CS2103;
        EditMeetingDescriptor descriptor =
                new EditMeetingDescriptorBuilder().withName(VALID_MEETING_NAME_CS2103).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_CS2103;
        descriptor = new EditMeetingDescriptorBuilder().withDate(VALID_DATE_CS2103).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start time
        userInput = targetIndex.getOneBased() + START_TIME_DESC_CS2103;
        descriptor = new EditMeetingDescriptorBuilder().withStartTime(VALID_START_TIME_CS2103).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end time
        userInput = targetIndex.getOneBased() + END_TIME_DESC_CS2103;
        descriptor = new EditMeetingDescriptorBuilder().withEndTime(VALID_END_TIME_CS2103).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_CS2103;
        descriptor = new EditMeetingDescriptorBuilder().withTags(VALID_TAG_CS2103).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_MEETING;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withTags().build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
