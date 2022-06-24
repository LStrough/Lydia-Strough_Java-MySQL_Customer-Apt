package model;

import java.time.*;

/**
 *This class is for Customer Appointments.
 *
 * @author Lydia Strough
 */
public class Appointment {
    /**
     * appointment ID, customer ID, database user ID, customer contact ID.
     * */
    private int appointmentId, customerId, userId, contactId;
    /**
     * appointment title, appointment description, appointment location, appointment type.
     * */
    private String title, description, location, type;
    /**
     *appointment (local) start date/time, appointment (local) end date/time.
     * */
    private LocalDateTime startDateTime, endDateTime;
    /**
     *appointment (local) start date, appointment (local) end date.
     * */
    private LocalDate startDate, endDate;
    /**
     *appointment (local) start time, appointment (local) end time.
     * */
    private LocalTime startTime, endTime;

    /**
     * This is the Appointment constructor.
     *
     * @param appointmentId appointment ID
     * @param customerId customer ID
     * @param userId database user ID
     * @param contactId customer contact ID
     * @param title appointment title
     * @param description appointment description
     * @param location appointment location
     * @param type appointment type
     * @param startDateTime appointment (local) start date and time
     * @param endDateTime appointment (local) end date and time
     * @param startDate appointment (local) start date
     * @param endDate appointment (local) end date
     * @param startTime appointment (local) start time
     * @param endTime appointment (local) end time
     * */
    public Appointment(int appointmentId, int customerId, int userId, int contactId, String title, String description,
                       String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, LocalDate startDate,
                       LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * @return the appointmentId
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * @param appointmentId the appointment ID to set
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customer ID to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the database user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @param contactId the customer contact ID to set
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the appointment title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the appointment description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the appointment location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the appointment type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the startDateTime
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * @param startDateTime the appointment (local) start date and time to set
     */
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * @return the endDateTime
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * @param endDateTime the appointment (local) end date and time to set
     */
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * @return the startDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the appointment (local) start date to set
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the appointment (local) end date to set
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the startTime
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the appointment (local) start time to set
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the appointment (local) end time to set
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * This is the Appointment toString method.
     *
     * This method provides default syntax for appointment information (converts hashcode to string, etc.)
     * */
    @Override
    public String toString() {
        return ("Appt: [" + Integer.toString(appointmentId) + "] | Customer: [" + Integer.toString(customerId) + "] " +
                "| Contact: [" + Integer.toString(contactId) + "] | Type: " + type + "| Start: " + startDateTime
                + " | End: " + endDateTime );
    }
}
