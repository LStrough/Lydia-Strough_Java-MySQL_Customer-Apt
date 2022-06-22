package DAO;

import javafx.collections.ObservableList;
import model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface AppointmentDao {
    public ObservableList<Appointment> getAllAppointments();
    public Appointment getAppointment(int appointmentId);
    public ObservableList<Appointment> getApptByCustomer(int customerId);
    //public ObservableList<Appointment> getApptByContact(int contactId);
    //public ObservableList<Appointment> getApptByCountry(int countryId);
    public int updateAppointment(int appointmentId, int customerId, int userId, int contactId, String title, String description,
                                 String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime);
    public int deleteAppointment(int appointmentId, int customerId);
    public int addAppointment(int customerId, int userId, int contactId, String title, String description,
                              String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime);
    public ObservableList<Appointment> lookUpAppointment(LocalDate date);
    public ObservableList<Appointment> orderApptsByMonth();
    //public ObservableList<Appointment> upcomingApptsMonth(LocalDateTime startOfToday)
    public ObservableList<Appointment> orderApptsByWeek();
    //public ObservableList<Appointment> upcomingApptsWeek(LocalDateTime startOfToday)
    public boolean checkApptStartTime(LocalDateTime apptStartTime);
    public boolean checkApptEndTime(LocalDateTime apptEndTime);
    public boolean checkNewApptForOverlap(int customerId, LocalDate selStartDate, LocalDate selEndDate, LocalTime selStartTime,
                                          LocalTime selEndTime);
    public boolean checkUpdatedApptForOverlap(int customerId, LocalDate selStartDate, LocalDate selEndDate, LocalTime selStartTime,
                                              LocalTime selEndTime, int apptId);

}
