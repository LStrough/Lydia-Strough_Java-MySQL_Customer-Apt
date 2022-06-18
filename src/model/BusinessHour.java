package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class BusinessHour {
    private int hour, min;
    private ZonedDateTime zonedDateTime;
   // LocalTime lt = LocalTime.of(8,0);
    LocalTime lt = LocalTime.of(hour, min);
    public static ObservableList<BusinessHour> businessHours = FXCollections.observableArrayList();

    public BusinessHour(int hour, int min) {
        this.hour = hour;
        this.min = min;
        //lt = LocalTime.of(hour, min);
    }

    public BusinessHour(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    //make conversion into a method
    LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), lt);
    ZonedDateTime zdt = ldt.atZone(ZoneId.of("America/New_York"));
    ZonedDateTime zdtConverted = zdt.withZoneSameInstant(ZoneId.systemDefault());
    LocalDateTime ldtConverted = zdtConverted.toLocalDateTime();

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

    @Override    public String toString() {
        String time = hour + ":" + min;
        if((hour == 8) || (hour == 9)) {
            time = "0" + time;
        }
        if(min == 0) {
            time = time + "0";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm");
        return (ldtConverted.format(dtf));
    }
}
