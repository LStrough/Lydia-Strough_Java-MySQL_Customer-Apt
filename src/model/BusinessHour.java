package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        return(Integer.toString(hour) + ":" + Integer.toString(min));
    }
}
