package seedu.address.model;

import java.util.ArrayList;

/**
 * Keeps track of changes to AddresSoc and implements
 * the Undo and Redo functions.
 */
public class VersionedAddresSoc {
    private final ArrayList<ReadOnlyAddressBook> addressBookStateList;
    private final AddressBook initialAddressBook;
    private int currentStatePointer;


    /**
     * Initializes VersionedAddresSoc with the initial AddressBook.
     */
    public VersionedAddresSoc(AddressBook initialAddressBook) {
        this.initialAddressBook = initialAddressBook;
        ReadOnlyAddressBook initialState = new AddressBook(initialAddressBook);
        addressBookStateList = new ArrayList<ReadOnlyAddressBook>();
        addressBookStateList.add(initialState);
        currentStatePointer = 0;
    }

    /**
     * Commits current AddressBook to the StateList as a State.
     * @param currentAddressBook to be saved in the StateList
     */
    public void commit(ReadOnlyAddressBook currentAddressBook) {
        ReadOnlyAddressBook currentState = new AddressBook(currentAddressBook);
        if (addressBookStateList.size() > currentStatePointer + 1) {
            addressBookStateList.subList(currentStatePointer + 1, addressBookStateList.size()).clear();
        }
        currentStatePointer++;
        addressBookStateList.add(currentState);
    }

    /**
     * Decrements pointer and resets the AddressBook to its previous state.
     *
     * {@code addressBookStateList} must contain previous states to revert to.
     */
    public void undo() {
        assert(canUndo()) : "AddresSoc has no states to undo!";
        currentStatePointer--;
        ReadOnlyAddressBook newState = addressBookStateList.get(currentStatePointer);
        initialAddressBook.resetData(newState);
    }

    /**
     * Increments pointer and sets the AddressBook to its next state.
     *
     * {@code addressBookStateList} must contain undone states to revert to.
     */
    public void redo() {
        assert(canRedo()) : "AddresSoc has no states to redo!";
        currentStatePointer++;
        ReadOnlyAddressBook newState = addressBookStateList.get(currentStatePointer);
        initialAddressBook.resetData(newState);
    }

    public boolean canUndo() {
        return (currentStatePointer > 0);
    }

    public boolean canRedo() {
        return (currentStatePointer < addressBookStateList.size() - 1);
    }
}
