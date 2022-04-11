package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPANTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingArchiveStatus;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Participant;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.tag.Tag;


/**
 * Edits the details of an existing meeting in the address book.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "editm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MEETING_NAME + "MEETING NAME] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_START_TIME + "START TIME] "
            + "[" + PREFIX_END_TIME + "END TIME] "
            + "[" + PREFIX_PARTICIPANTS + "PARTICIPANTS]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "12-03-2022 "
            + PREFIX_START_TIME + "1800";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_EDIT_SUCCESS_WITH_CLASH = "Edited Meeting: %1$s. Clashes with:\n%2$s.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book.";
    public static final String MESSAGE_INVALID_TIME = "Meeting end time should be later meeting start time";

    private final Index index;
    private final EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor;

    /**
     * @param index                of the person in the filtered meeting list to edit
     * @param editMeetingDescriptor details to edit the meeting with
     */
    public EditMeetingCommand(Index index, EditMeetingDescriptor editMeetingDescriptor) {
        requireNonNull(index);
        requireNonNull(editMeetingDescriptor);

        this.index = index;
        this.editMeetingDescriptor = new EditMeetingCommand.EditMeetingDescriptor(editMeetingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();
        List<Contact> contactList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownList.get(index.getZeroBased());
        assert !meetingToEdit.getEditStatus();
        meetingToEdit.setEditStatus();
        Meeting editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor, contactList);

        if (model.hasMeeting(editedMeeting)) {
            meetingToEdit.setEditStatus();
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        ArrayList<Meeting> clashingMeetings = model.checkMeetingClash(editedMeeting);
        meetingToEdit.setEditStatus();

        model.setMeeting(meetingToEdit, editedMeeting);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        model.commitAddressBook();

        if (clashingMeetings.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting));
        } else {
            String clashMessage = clashingMeetings.stream().map(Meeting::toString)
                    .collect(Collectors.joining(",\n"));
            return new CommandResult(String.format(MESSAGE_EDIT_SUCCESS_WITH_CLASH, editedMeeting, clashMessage),
                    false, true, false);
        }
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code editMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor,
                                               List<Contact> contactList) throws CommandException {
        assert meetingToEdit != null;

        MeetingName updatedMeetingName = editMeetingDescriptor.getMeetingName().orElse(meetingToEdit.getName());
        MeetingDate updatedMeetingDate = editMeetingDescriptor.getMeetingDate().orElse(meetingToEdit.getDate());
        StartTime updatedStartTime = editMeetingDescriptor.getStartTime().orElse(meetingToEdit.getStartTime());
        EndTime updatedEndTime = editMeetingDescriptor.getEndTime().orElse(meetingToEdit.getEndTime());

        if (updatedStartTime.isAfter(updatedEndTime) || updatedStartTime.equals(updatedEndTime)) {
            throw new CommandException(MESSAGE_INVALID_TIME);
        }

        MeetingArchiveStatus archiveStatus = meetingToEdit.getArchiveStatus();
        Set<Tag> updatedTags = editMeetingDescriptor.getTags().orElse(meetingToEdit.getTags());
        final Set<Participant> updatedParticipants = new HashSet<>();

        if (editMeetingDescriptor.getParticipantsIndex().isPresent()) {
            updatedParticipants.addAll(getParticipantsFromIndexes(editMeetingDescriptor, contactList));
        } else {
            updatedParticipants.addAll(meetingToEdit.getParticipants());
        }

        return new Meeting(updatedMeetingName, updatedMeetingDate, updatedStartTime,
                updatedEndTime, updatedParticipants, archiveStatus, updatedTags);
    }

    public static Set<Participant> getParticipantsFromIndexes(EditMeetingDescriptor editMeetingDescriptor,
                                                       List<Contact> contactList) throws CommandException {
        final Set<Participant> participantsSet = new HashSet<>();

        for (Index targetIndex : editMeetingDescriptor.participantsIndex) {
            if (targetIndex.getZeroBased() >= contactList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Contact participatingContact = contactList.get(targetIndex.getZeroBased());
            participantsSet.add(new Participant(participatingContact));
        }

        return participantsSet;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMeetingCommand)) {
            return false;
        }

        // state check
        EditMeetingCommand e = (EditMeetingCommand) other;
        return index.equals(e.index)
                && editMeetingDescriptor.equals(e.editMeetingDescriptor);
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the
     * corresponding field value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private MeetingName meetingName;
        private MeetingDate meetingDate;
        private StartTime startTime;
        private EndTime endTime;
        private Set<Tag> tags;
        private Set<Index> participantsIndex;

        public EditMeetingDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} and {@code participantsIndex} is used internally.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setMeetingName(toCopy.meetingName);
            setMeetingDate(toCopy.meetingDate);
            setStartTime(toCopy.startTime);
            setEndTime(toCopy.endTime);
            setTags(toCopy.tags);
            setParticipantsIndex(toCopy.participantsIndex);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(meetingName, meetingDate, startTime, endTime, tags, participantsIndex);
        }

        public void setMeetingName(MeetingName meetingName) {
            this.meetingName = meetingName;
        }

        public Optional<MeetingName> getMeetingName() {
            return Optional.ofNullable(meetingName);
        }

        public void setMeetingDate(MeetingDate meetingDate) {
            this.meetingDate = meetingDate;
        }

        public Optional<MeetingDate> getMeetingDate() {
            return Optional.ofNullable(meetingDate);
        }

        public void setStartTime(StartTime startTime) {
            this.startTime = startTime;
        }

        public Optional<StartTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndTime(EndTime endTime) {
            this.endTime = endTime;
        }

        public Optional<EndTime> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code participantsIndex} to this object's {@code participantsIndex}.
         * A defensive copy of {@code participantsIndex} is used internally.
         */
        public void setParticipantsIndex(Set<Index> participantsIndex) {
            this.participantsIndex = (participantsIndex != null) ? new HashSet<>(participantsIndex) : null;
        }

        /**
         * Returns an unmodifiable index set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code participantsIndex} is null.
         */
        public Optional<Set<Index>> getParticipantsIndex() {
            return (participantsIndex != null)
                    ? Optional.of(Collections.unmodifiableSet(participantsIndex))
                    : Optional.empty();
        }

        @Override
            public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMeetingDescriptor)) {
                return false;
            }

            // state check
            EditMeetingDescriptor e = (EditMeetingDescriptor) other;

            return getMeetingName().equals(e.getMeetingName())
                    && getMeetingDate().equals(e.getMeetingDate())
                    && getStartTime().equals(e.getStartTime())
                    && getEndTime().equals(e.getEndTime())
                    && getTags().equals(e.getTags())
                    && getParticipantsIndex().equals(e.getParticipantsIndex());
        }
    }
}
