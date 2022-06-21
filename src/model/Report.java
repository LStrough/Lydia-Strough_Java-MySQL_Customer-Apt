package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Report extends Appointment{
    private LocalDate month;

    public Report(int appointmentId, int customerId, int userId, int contactId, String title, String description,
                  String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, LocalDate startDate,
                  LocalDate endDate, LocalTime startTime, LocalTime endTime, LocalDate month) {
        super(appointmentId, customerId, userId, contactId, title, description, location, type, startDateTime, endDateTime,
                startDate, endDate, startTime, endTime);
        this.month = month;
    }

    public LocalDate getMonth() {
        return month;
    }

    public void setMonth(LocalDate month) {
        this.month = LocalDate.from(getStartDate().getMonth());
    }

    @Override
    public String toString() {
        return (month.format(DateTimeFormatter.ofPattern("MMMM")));
    }
}
