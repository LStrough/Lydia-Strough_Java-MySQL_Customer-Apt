package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BusinessHour {
    private int hour;
    private int min;
    static ObservableList<BusinessHour> businessHours = FXCollections.observableArrayList();

    public BusinessHour(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public static ObservableList<BusinessHour> getBusinessHrs(){
        return businessHours;
    }

    public static void addBusinessHour(BusinessHour newBHr) {
        businessHours.add(newBHr);
    }

    @Override
    public String toString() {
        /*
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
        String time = hour + ":" + min;
        if((hour == 8) || (hour == 9)) {
            time = "0" + time;
        }
        if(min == 0) {
            time = time + "0";
        }
        LocalTime localTimeObj = LocalTime.parse(time, df);
        return(localTimeObj.toString());
         */

        String time = hour + ":" + min;
        if((hour == 8) || (hour == 9)) {
            time = "0" + time;
        }
        if(min == 0) {
            time = time + "0";
        }
        return time;
    }
}
