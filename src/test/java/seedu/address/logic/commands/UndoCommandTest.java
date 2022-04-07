package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_INVALID_UNDO;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_SUCCESS;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.TypicalAddressBook;

public class UndoCommandTest {

    @TempDir
    public Path temporaryFolder;

    private AddressBook initialAddressBook = TypicalAddressBook.getTypicalAddressBook();
    private Model testModel = new ModelManager(initialAddressBook, new UserPrefs());
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(testModel, storage);
    }

    @Test
    public void execute_undo_failure() {
        assertCommandFailure(new UndoCommand(), testModel, MESSAGE_INVALID_UNDO);
    }

    @Test
    public void execute_undo_success() throws CommandException, ParseException {
        Model newModel = new ModelManager(initialAddressBook, new UserPrefs());
        logic.execute(AddContactCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + TELEGRAM_DESC_AMY);
        Model newModel1 = new ModelManager(testModel.getAddressBook(), testModel.getUserPrefs());
        logic.execute(AddContactCommand.COMMAND_WORD + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TELEGRAM_DESC_BOB);
        assertCommandSuccess(new UndoCommand(), testModel, MESSAGE_SUCCESS, newModel1);
        assertCommandSuccess(new UndoCommand(), testModel, MESSAGE_SUCCESS, newModel);
    }

    @Test
    public void execute_undo_successThenFailure() throws CommandException, ParseException {
        Model newModel = new ModelManager(initialAddressBook, new UserPrefs());
        logic.execute(AddContactCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + TELEGRAM_DESC_AMY);
        assertCommandSuccess(new UndoCommand(), testModel, MESSAGE_SUCCESS, newModel);
        assertCommandFailure(new UndoCommand(), testModel, MESSAGE_INVALID_UNDO);
    }
}
