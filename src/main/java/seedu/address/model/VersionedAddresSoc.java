package seedu.address.model;

import java.util.ArrayList;

public class VersionedAddresSoc {
    private final ArrayList<ReadOnlyAddressBook> addressBookStateList;
    private int currentStatePointer;
    AddressBook initialAddressBook;


    public VersionedAddresSoc(AddressBook initialAddressBook) {
        this.initialAddressBook = initialAddressBook;
        ReadOnlyAddressBook initialState = new AddressBook(initialAddressBook);
        addressBookStateList = new ArrayList<ReadOnlyAddressBook>();
        addressBookStateList.add(initialState);
        currentStatePointer = 0;
    }

    public void commit(ReadOnlyAddressBook currentAddressBook) {
        ReadOnlyAddressBook currentState = new AddressBook(currentAddressBook);
        if (addressBookStateList.size() > currentStatePointer + 1) {
            addressBookStateList.subList(currentStatePointer + 1, addressBookStateList.size()).clear();
        }
        currentStatePointer++;
        addressBookStateList.add(currentState);
        System.out.println(currentStatePointer);
        System.out.println(addressBookStateList.size());
    }

    public void undo() {
        currentStatePointer--;
        ReadOnlyAddressBook newState = addressBookStateList.get(currentStatePointer);
        initialAddressBook.resetData(newState);
        System.out.println(currentStatePointer);
        System.out.println(addressBookStateList.size());
    }

    public void redo() {
        currentStatePointer++;
        ReadOnlyAddressBook newState = addressBookStateList.get(currentStatePointer);
        initialAddressBook.resetData(newState);
        System.out.println(currentStatePointer);
        System.out.println(addressBookStateList.size());
    }

    public boolean canUndo() {
        return (currentStatePointer > 0);
    }

    public boolean canRedo() {
        return (currentStatePointer < addressBookStateList.size() - 1);
    }
}
