package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalTime;
import java.time.ZoneId;

public class TimeManager {
    public static ObservableList<LocalTime> businessHourInit(int start) {
        ObservableList<LocalTime> timeList = FXCollections.observableArrayList();

        for(int i = start; i < 24; i++) {
            timeList.add(LocalTime.of(i, 0));
        }
        return timeList;
    }

    public static ObservableList<LocalTime> dynamicBusinessHoursInit(ZoneId osZId, ZoneId estZId, LocalTime start, int hours) {


    }
}
