package seedu.address.model.contact;

import java.util.*;

public class NameComparator implements Comparator<Contact> {

    // override the compare() method
    public int compare(Contact s1, Contact s2)
    {
        return s1.getName().compareTo(s2.getName());
    }
}
