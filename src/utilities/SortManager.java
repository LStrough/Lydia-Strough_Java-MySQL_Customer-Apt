package utilities;

import model.Appointment;

import java.util.Comparator;

public class SortManager implements Comparator<Appointment> {
    @Override
    public int compare(Appointment o1, Appointment o2) {
        return o1.getStartDate().compareTo(o2.getStartDate());
    }

    //sort by type
    //sort by contact
    //sort by country
    //sort by month
}
