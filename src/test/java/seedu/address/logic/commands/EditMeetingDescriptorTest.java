package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.DESC_NUSSU;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_NUSSU;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_NUSSU;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_NAME_NUSSU;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_NUSSU;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NUSSU;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.testutil.EditMeetingDescriptorBuilder;

public class EditMeetingDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditMeetingDescriptor descriptorWithSameValues = new EditMeetingDescriptor(DESC_CS2103);
        assertTrue(DESC_CS2103.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS2103.equals(DESC_CS2103));

        // null -> returns false
        assertFalse(DESC_CS2103.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS2103.equals(5));

        // different values -> returns false
        assertFalse(DESC_CS2103.equals(DESC_NUSSU));

        // different name -> returns false
        EditMeetingDescriptor editedCs2103 =
                new EditMeetingDescriptorBuilder(DESC_CS2103).withName(VALID_MEETING_NAME_NUSSU).build();
        assertFalse(DESC_CS2103.equals(editedCs2103));

        // different start time -> returns false
        editedCs2103 = new EditMeetingDescriptorBuilder(DESC_CS2103).withStartTime(VALID_START_TIME_NUSSU).build();
        assertFalse(DESC_CS2103.equals(editedCs2103));

        // different end time -> returns false
        editedCs2103 = new EditMeetingDescriptorBuilder(DESC_CS2103).withEndTime(VALID_END_TIME_NUSSU).build();
        assertFalse(DESC_CS2103.equals(editedCs2103));

        // different date -> returns false
        editedCs2103 = new EditMeetingDescriptorBuilder(DESC_CS2103).withDate(VALID_DATE_NUSSU).build();
        assertFalse(DESC_AMY.equals(editedCs2103));

        // different tags -> returns false
        editedCs2103 = new EditMeetingDescriptorBuilder(DESC_CS2103).withTags(VALID_TAG_NUSSU).build();
        assertFalse(DESC_AMY.equals(editedCs2103));
    }
}
