package DAO;

import javafx.collections.ObservableList;
import model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface AppointmentDao {
    public ObservableList<Appointment> getAllAppointments();
    public Appointment getAppointment(int appointmentId);
    public int updateAppointment(int appointmentId, int customerId, int userId, int contactId, String title, String description,
                                 String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime);
    public int deleteAppointment(int appointmentId, int customerId);
    public int addAppointment(int customerId, int userId, int contactId, String title, String description,
                              String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
