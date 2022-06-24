package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *This class is for Managing the list of "Business Hour" Times.
 *
 * @author Lydia Strough
 */
public class TimeManager {
    /**
     * This is the dynamic Business Hours Initialized method.
     * This method is used to create a dynamic list of times (hours) that will convert with the operating systems' local time.
     *
     * @param osZId operating system zone ID (system Default)
     * @param businessZId specific zone for business hours (UTC)
     * @param businessHourStart business hour start time (08:00)
     * @param workHours number of work hours
     * @return timeList List of dynamic business hours
     */
    public static ObservableList<LocalTime> dynamicBusinessHoursInit(ZoneId osZId, ZoneId businessZId, LocalTime businessHourStart, int workHours) {
        ObservableList<LocalTime> timeList = FXCollections.observableArrayList();
        ZonedDateTime businessZDT = ZonedDateTime.of(LocalDate.now(), businessHourStart, businessZId);
        ZonedDateTime localZDT = ZonedDateTime.ofInstant(businessZDT.toInstant(), osZId);
        int localStartingHour = localZDT.getHour();
        int totalHours = localStartingHour + workHours;
        int midnightOrGreater = 0;

        for(int i = localStartingHour; i <= totalHours; i++) {
            if(i<24) {
                timeList.add(LocalTime.of(i,0));
            }
            if(i>23) {
                timeList.add(LocalTime.of(midnightOrGreater,0));
                midnightOrGreater += 1;
            }
        }
        return timeList;
    }
}
