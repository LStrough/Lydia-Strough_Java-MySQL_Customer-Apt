package DAO;

import javafx.collections.ObservableList;
import model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This is the "Appointment DAO" class.
 * This class acts as an Interface for the "Appointment DAO Implementation" class.
 *
 * @author Lydia Strough
 */
public interface AppointmentDao {
    /**
     * This is the "get All Appointments" method.
     * This method accesses the database and returns all appointments. Each appointment is then added to an observable list, "allAppointments".
     *
     * @return allAppointments list
     */
    public ObservableList<Appointment> getAllAppointments();

    /**
     * This is the "get appointment" method.
     * This method searches the database for a specific appointment based on its unique appointment ID.
     *
     * @param appointmentId the appointment in questions' specific ID
     * @return the appointment in questions' information
     * @return no result (null), if no appointment with the specific ID exists
     */
    public Appointment getAppointment(int appointmentId);

    /**
     * This is the "get appointment(s) by customer" method.
     * <p>This method accesses the database and filters a list of appointments, based on their related customer ID.
     * If an appointment is associated with the specific customer ID, it is added to a "customer appointments" list.</p>
     *
     * @param customerId the customer in questions' specific ID
     * @return customerAppts list
     */
    public ObservableList<Appointment> getApptByCustomer(int customerId);

    /**
     * This is the "get appointment(s) by contact" method.
     * <p>This method accesses the database and filters a list of appointments, based on their related contact ID.
     * If an appointment is associated with the specific contact ID, it is added to a "appointments by contact" list.</p>
     *
     * @param contactId the contact in questions' specific ID
     * @return apptsByContact list
     */
    public ObservableList<Appointment> getApptByContact(int contactId);

    /**
     * This is the "update appointment" method.
     * This method updates all values of a selected appointment based on its unique appointment ID.
     *
     * @param appointmentId the appointment in questions' unique appointment ID
     * @param customerId the appointment in questions' desired associated customer ID
     * @param userId the appointment in questions' desired associated user ID
     * @param contactId the appointment in questions' desired associated contact ID
     * @param title the appointment in questions' desired appointment title
     * @param description the appointment in questions' desired appointment description
     * @param location the appointment in questions' desired appointment location
     * @param type the appointment in questions' desired appointment type
     * @param startDateTime the appointment in questions' desired appointment start date and time
     * @param endDateTime the appointment in questions' desired appointment end date and time
     * @return the number of affected database rows
     */
    public int updateAppointment(int appointmentId, int customerId, int userId, int contactId, String title, String description,
                                 String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime);

    /**
     * This is the "delete appointment" method.
     * <p>This method accesses the database and deletes an with a specific appointment ID, customer ID, and appointment type.
     * If an appointment was successfully (or unsuccessfully) deleted, an alert message populates with the result of the query. </p>
     *
     * @param appointmentId the appointment in questions' unique appointment ID
     * @param customerId the appointment in questions' associated customer ID
     * @param type the appointment in questions appointment type
     * @return the number of affected database rows
     */
    public int deleteAppointment(int appointmentId, int customerId, String type);

    /**
     * This is the "add appointment" method.
     * <p>This method accesses the database and adds an appointment to the system with the desired credentials (associated customer ID,
     * associated user ID, associated contact ID, appointment title, appointment description, appointment location, appointment type,
     * appointment desired start date and time, and desired appointment end date and time). </p>
     *
     * @param customerId associated customer ID
     * @param userId associated user ID
     * @param contactId associated contact ID
     * @param title appointment title
     * @param description appointment description
     * @param location appointment location
     * @param type appointment type
     * @param startDateTime appointment desired start date and time
     * @param endDateTime desired appointment end date and time
     * @return the number of affected database rows
     */
    public int addAppointment(int customerId, int userId, int contactId, String title, String description,
                              String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime);

    /**
     * This is the "look up appointments" method.
     * <p>This method searches the allAppointments list for a filtered list of appointments that are associated with a
     * specific (local) start date.</p>
     *
     * @param date (local) appointment start date in question
     * @return <p>the allAppointments list, if there are no matching appointments with the specified (local) start date;
     * Or the filteredAppts list of appointments associated with the (local) start date</p>
     */
    public ObservableList<Appointment> lookUpAppointment(LocalDate date);

    /**
     * This is the "upcoming appointment alert" method.
     * <p>This method populates an alert message after the user initially logs in with a either: a list of upcoming appointments,
     * or a message stating the fact that there are no appointments scheduled to take place in the next 15 minutes. </p>
     *
     * <p>The method filters through all appointments stored in the database using the (local) date and time at login.
     * If an appointment meets the credentials it is then added to the "upcoming appointments" list, which is displayed in the alert box.
     * If the "upcoming appointments" list is empty, the other message populates (no upcoming appointments) instead.</p>
     *
     * @param loginLDT the operating systems local date and time at the users initial log in
     */
    public void upcomingApptAlert(LocalDateTime loginLDT);

    /**
     * This is the "upcoming appointments for the next week" method.
     * <p>LAMBDA EXPRESSION: This method searches the all appointments list for a filtered list of appointments that will be occurring over
     * the next 7 days (including the date of the initial login). If an appointment in the database matched the credentials,
     * the lambda expression added the appointment to the filtered list, "filteredAppts."</p>
     *
     * <p>WHY I CHOSE TO USE A LAMBDA IN THIS SCENARIO: Using a lambda function required less code in this scenario compared to manually appending
     * each appointment to a filtered appointments list. </p>
     *
     * @param loginLD the operating systems local date at the users initial log in
     * @return filteredAppts list
     */
    public ObservableList<Appointment> upcomingApptsWeek(LocalDate loginLD);

    /**
     * This is the "upcoming appointments for the rest of the month" method.
     * <p>LAMBDA EXPRESSION: This method searches the all appointments list for a filtered list of appointments that will be occurring
     * throughout the remaining of the current month (including the date of the initial login). If an appointment in the database matched the credentials,
     * the lambda expression added the appointment to the filtered list, "filteredAppts."</p>
     *
     * <p>WHY I CHOSE TO USE A LAMBDA IN THIS SCENARIO: Using a lambda function required less code in this scenario compared to manually appending
     * each appointment to a filtered appointments list. </p>
     *
     * @param loginLD the operating systems local date at the users initial log in
     * @return filteredAppts list
     */
    public ObservableList<Appointment> upcomingApptsMonth(LocalDate loginLD);

    /**
     * This is the "check appointment start time" method.
     * <p>This method checks a desired appointment start time to see if the selected time is within "business hours" (0800 - 2200 EST).
     * The method takes the desired start time and converts it from local time to EST and checks to see if it is within business hours.
     * The method also checks to see if the time occurs before "closing" time. If the time is within business hours, and meets the other credentials,
     * the method returns true.</p>
     * @param apptStartTime the desired appointment start time that is in question
     * @return true, if the desired time meets the credentials
     */
    public boolean checkApptStartTime(LocalDateTime apptStartTime);

    /**
     * This is the "check appointment end time" method.
     * <p>This method checks a desired appointment end time to see if the selected time is within "business hours" (0800 - 2200 EST).
     * The method takes the desired start time and converts it from local time to EST and checks to see if it is within business hours.
     * The method also checks to see if the time occurs after "opening" time. If the time is within business hours, and meets the other credentials,
     * the method returns true.</p>
     * @param apptEndTime the desired appointment end time that is in question
     * @return true, if the desired time meets the credentials
     */
    public boolean checkApptEndTime(LocalDateTime apptEndTime);

    /**
     * This is the "check new customer appointment for appointment overlap" method.
     * <p>This method calls the "getApptByCustomer" method and searches this list of customer appointments to see if there
     * will be any appointment conflict. This method filters the customer appointments list and checks a number of requirements to see if there is any overlap.</p>
     *
     * The checks include:
     * <p>-whether or not the appointments start or end on the same day</p>
     * <p>-whether or not the appointments start at the same time</p>
     * <p>-whether or not any of the current customers' appointments occur within the new appointments selected start and end times</p>
     * <p>-whether or not the new appointment starts before the old appointments starts, and simultaneously, does the new
     * appointment end after the old appointment starts.</p>
     *
     *<p>If any of these checks come back true, then an overlap DOES occur with the selected appointment date and times</p>
     *
     * @param customerId the appointment in questions' associated customer ID
     * @param selStartDate the appointment in questions' desired start date
     * @param selEndDate the appointment in questions' desired end date
     * @param selStartTime the appointment in questions' desired start time
     * @param selEndTime the appointment in questions' desired end time
     * @return true, if there is any appointment overlap
     * @return false, if there is no appointment overlap
     */
    public boolean checkNewApptForOverlap(int customerId, LocalDate selStartDate, LocalDate selEndDate, LocalTime selStartTime,
                                          LocalTime selEndTime);

    /**
     * This is the "check (modified) customer appointment for appointment overlap" method.
     * <p>This method calls the "getApptByCustomer" method and searches this list of customer appointments to see if there is an
     * appointment in the database with a matching appointment ID, appointment start and end date, as well as appointment start and end time.
     * If there IS an appointment with these matching credentials, then there will be no appointment overlap, because the times/dates have not changed.
     * </p>
     * <p>However, if there is no match, then the "checkNewApptForOverlap" method is called and the updated appointment date and times will be checked for
     * appointment overlap. If there is no overlap, the method returns false. If there is overlap, the method returns true.</p>
     *
     * @param customerId the appointment in questions' associated customer ID
     * @param selStartDate the appointment in questions' desired start date
     * @param selEndDate the appointment in questions' desired end date
     * @param selStartTime the appointment in questions' desired start time
     * @param selEndTime the appointment in questions' desired end time
     * @param apptId the appointment in questions' unique appointment ID
     * @return true, if there is any appointment overlap
     * @return false, if there is no appointment overlap
     */
    public boolean checkUpdatedApptForOverlap(int customerId, LocalDate selStartDate, LocalDate selEndDate, LocalTime selStartTime,
                                              LocalTime selEndTime, int apptId);

}
