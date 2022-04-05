package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.RedoCommand.MESSAGE_INVALID_REDO;
import static seedu.address.logic.commands.RedoCommand.MESSAGE_SUCCESS;

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

public class RedoCommandTest {

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
    public void execute_redo_failure() {
        assertCommandFailure(new RedoCommand(), testModel, MESSAGE_INVALID_REDO);
    }

    @Test
    public void execute_redo_success() throws CommandException, ParseException {
        logic.execute(AddContactCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + TELEGRAM_DESC_AMY);
        Model newModel = new ModelManager(testModel.getAddressBook(), testModel.getUserPrefs());
        logic.execute(UndoCommand.COMMAND_WORD);
        assertCommandSuccess(new RedoCommand(), testModel, MESSAGE_SUCCESS, newModel);
    }

    @Test
    public void execute_redo_successThenFailure() throws CommandException, ParseException {
        logic.execute(AddContactCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + TELEGRAM_DESC_AMY);
        Model newModel = new ModelManager(testModel.getAddressBook(), testModel.getUserPrefs());
        logic.execute(UndoCommand.COMMAND_WORD);
        assertCommandSuccess(new RedoCommand(), testModel, MESSAGE_SUCCESS, newModel);
        assertCommandFailure(new RedoCommand(), testModel, MESSAGE_INVALID_REDO);
    }
}
