package DAO;

import javafx.collections.ObservableList;
import model.Appointment;

public interface AppointmentDao {
    public ObservableList<Appointment> getAllAppointments();
    public Appointment getCustomer(int customerId);
    public int updateAppointment();
    public int deleteAppointment(int customerId, String customerName);
    public int addAppointment(String customerName, String address, String postalCode, String phone, int divisionId);
}
