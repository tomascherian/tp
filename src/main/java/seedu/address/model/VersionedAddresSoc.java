package seedu.address.model;

import java.util.ArrayList;

public class VersionedAddresSoc extends AddressBook {
    private ArrayList<ReadOnlyAddressBook> addressBookStateList;
    private int currentStatePointer;


    public VersionedAddresSoc(ReadOnlyAddressBook initialAddressBook) {
        super(initialAddressBook);
        ReadOnlyAddressBook initialState = new AddressBook(initialAddressBook);
        addressBookStateList = new ArrayList<ReadOnlyAddressBook>();
        addressBookStateList.add(initialState);
        currentStatePointer = 0;
    }

    public void commit(ReadOnlyAddressBook currentAddressBook) {
        ReadOnlyAddressBook currentState = new AddressBook(currentAddressBook);
        addressBookStateList.add(currentState);
        currentStatePointer++;
        if (addressBookStateList.size() > currentStatePointer + 1) {
            addressBookStateList.subList(currentStatePointer + 1, addressBookStateList.size()).clear();
        }
    }

    public void undo() {
        currentStatePointer--;
        ReadOnlyAddressBook newState = addressBookStateList.get(currentStatePointer);
        resetData(newState);
    }

    public void redo() {
        currentStatePointer++;
        ReadOnlyAddressBook newState = addressBookStateList.get(currentStatePointer);
        resetData(newState);
    }

    public boolean canUndo() {
        return (currentStatePointer > 0);
    }

    public boolean canRedo() {
        return (currentStatePointer <= addressBookStateList.size());
    }
}
