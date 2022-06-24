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
     * @return the allAppointments list, if there are no matching appointments with the specified (local) start date
     * @return the filteredAppts list of appointments associated with the (local) start date
     */
    public ObservableList<Appointment> lookUpAppointment(LocalDate date);

    /**
     *
     * @param loginLDT
     */
    public void upcomingApptAlert(LocalDateTime loginLDT);

    /**
     *
     * @param loginLD
     * @return
     */
    public ObservableList<Appointment> upcomingApptsWeek(LocalDate loginLD);

    /**
     *
     * @param loginLD
     * @return
     */
    public ObservableList<Appointment> upcomingApptsMonth(LocalDate loginLD);

    /**
     *
     * @param apptStartTime
     * @return
     */
    public boolean checkApptStartTime(LocalDateTime apptStartTime);

    /**
     *
     * @param apptEndTime
     * @return
     */
    public boolean checkApptEndTime(LocalDateTime apptEndTime);

    /**
     *
     * @param customerId
     * @param selStartDate
     * @param selEndDate
     * @param selStartTime
     * @param selEndTime
     * @return
     */
    public boolean checkNewApptForOverlap(int customerId, LocalDate selStartDate, LocalDate selEndDate, LocalTime selStartTime,
                                          LocalTime selEndTime);

    /**
     *
     * @param customerId
     * @param selStartDate
     * @param selEndDate
     * @param selStartTime
     * @param selEndTime
     * @param apptId
     * @return
     */
    public boolean checkUpdatedApptForOverlap(int customerId, LocalDate selStartDate, LocalDate selEndDate, LocalTime selStartTime,
                                              LocalTime selEndTime, int apptId);

}
