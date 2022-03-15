package seedu.address.model.contact;

import java.util.Comparator;


public class NameComparator implements Comparator<Contact> {

    /** override the compare() method
     *
     * @param s1
     * @param s2
     * @return
     */

    @Override
    public int compare(Contact s1, Contact s2) {

        Long big = Long.parseLong(s1.getName().fullName, 35);
        Long next = Long.parseLong(s2.getName().fullName, 35);
        return big.compareTo(next);

    }
}
