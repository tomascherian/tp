package seedu.address.model;

import java.util.ArrayList;

public class VersionedAddresSoc {
    ArrayList<ReadOnlyAddressBook> addressBookStateList;
    AddressBook currentAddressBook;
    int currentStatePointer;


    public VersionedAddresSoc(ReadOnlyAddressBook initialState) {
        addressBookStateList = new ArrayList<ReadOnlyAddressBook>();
        addressBookStateList.add(initialState);
        //this.currentAddressBook = initialState;
        currentStatePointer = 0;
    }

    public void commit(ReadOnlyAddressBook currentState) {
        addressBookStateList.add(currentState);
        currentStatePointer++;
    }

    public void undo() {
        currentStatePointer--;
        ReadOnlyAddressBook newState = addressBookStateList.get(currentStatePointer);
        currentAddressBook.resetData(newState);
    }

    public void redo() {
        currentStatePointer++;
        ReadOnlyAddressBook newState = addressBookStateList.get(currentStatePointer);
        currentAddressBook.resetData(newState);
    }
}
