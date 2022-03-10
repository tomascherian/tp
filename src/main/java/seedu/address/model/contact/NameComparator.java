package seedu.address.model.contact;

import java.util.Comparator;

public class NameComparator implements Comparator<Contact> {

    /** override the compare() method
     *
     * @param s1
     * @param s2
     * @return
     */
    public int compare(Contact s1, Contact s2) {

        return s1.getName().compareTo(s2.getName());
    }
}
