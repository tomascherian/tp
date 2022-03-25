package seedu.address.model.contact;

import java.util.Comparator;


public class NameComparator implements Comparator<Contact> {

    /** override the compare() method
     *
     * @param s1
     * @param s2
     * @returns a negative, zero or positive integer based on whether the first argument is
     * less than, equal to or greater than the second.
     */

    @Override
    public int compare(Contact s1, Contact s2) {

        return s1.getName().fullName.toLowerCase().compareTo(s2.getName().fullName.toLowerCase());

    }
}
