package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;

public class BusinessHour {
    private int hour, min;
    private ZonedDateTime zonedDateTime;
    public static ObservableList<BusinessHour> businessHours = FXCollections.observableArrayList();

    public BusinessHour(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }

    public BusinessHour(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
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

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public static ObservableList<BusinessHour> getBusinessHrs(){
        return businessHours;
    }

    public static void addBusinessHour(BusinessHour newBHr) {
        businessHours.add(newBHr);
    }

    @Override
    public String toString() {
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
