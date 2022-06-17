package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static DAO.JDBC.connection;

public class AppointmentDaoImpl implements AppointmentDao{
    ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    public boolean apptFound;

    @Override
    public ObservableList<Appointment> getAllAppointments() {
       try{
            String sql = "SELECT * FROM appointments";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                int appointmentId = result.getInt("Appointment_ID");
                int customerId = result.getInt("Customer_ID");;
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");
                LocalDateTime startDateTime = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endDateTime = result.getTimestamp("End").toLocalDateTime();
                LocalDate startDate = startDateTime.toLocalDate();
                LocalDate endDate = endDateTime.toLocalDate();
                LocalTime startTime = startDateTime.toLocalTime();
                LocalTime endTime = endDateTime.toLocalTime();
                Appointment appointment = new Appointment(appointmentId, customerId, userId, contactId, title, description,
                        location, type, startDateTime, endDateTime, startDate, endDate, startTime, endTime);
                appointments.add(appointment);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return appointments;
    }

    @Override
    public Appointment getAppointment(int appointmentId) {
        try{
            String sql = "SELECT * FROM appointments WHERE Appointment_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentId);

            ResultSet result = ps.executeQuery();
            Appointment apptResult = null;
            if(result.next()) {
                appointmentId = result.getInt("Appointment_ID");
                int customerId = result.getInt("Customer_ID");;
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");
                LocalDateTime startDateTime = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endDateTime = result.getTimestamp("End").toLocalDateTime();
                LocalDate startDate = startDateTime.toLocalDate();
                LocalDate endDate = endDateTime.toLocalDate();
                LocalTime startTime = startDateTime.toLocalTime();
                LocalTime endTime = endDateTime.toLocalTime();
                apptResult = new Appointment(appointmentId, customerId, userId, contactId, title, description,
                        location, type, startDateTime, endDateTime, startDate, endDate, startTime, endTime);
            }
            return apptResult;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return null;
    }

    @Override
    public int updateAppointment(int appointmentId, int customerId, int userId, int contactId, String title, String description,
                                 String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        int rowsAffected = 0;
        try{
            String sql = "UPDATE appointments SET Customer_ID=?, User_ID=?, Contact_ID=?, Title=?, Description=?, " +
                    "Location=?, Type=?, Start=?, End=? WHERE Appointment_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setInt(2, userId);
            ps.setInt(3, contactId);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, location);
            ps.setTimestamp(7, Timestamp.valueOf(startDateTime));       //convert to UTC?
            ps.setTimestamp(8, Timestamp.valueOf(endDateTime));         //convert to UTC?
            ps.setInt(9, appointmentId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Appointment UPDATE was successful!");
            } else {
                System.out.println("Appointment UPDATE Failed!");
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int deleteAppointment(int appointmentId, int customerId) {
        int rowsAffected = 0;
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID=? AND Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ps.setInt(2, customerId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Appointment [" + appointmentId + "] was successfully deleted!");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment DELETE");
                alert.setContentText("Appointment [" + appointmentId + "] was successfully deleted!");
                alert.showAndWait();
            } else {
                System.out.println("Appointment DELETE failed!");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Appointment DELETE");
                alert.setContentText("Appointment [" + appointmentId + "] failed to deleted!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int addAppointment(int customerId, int userId, int contactId, String title, String description, String location,
                              String type, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        int rowsAffected = 0;
        try {
            String sql = "INSERT INTO appointments (Customer_ID, User_ID, Contact_ID, Title, Description, Location, Type, " +
                    "Start, End) VALUES(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setInt(2, userId);
            ps.setInt(3, contactId);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, location);
            ps.setString(7, type);
            ps.setTimestamp(8, Timestamp.valueOf(startDateTime));       //convert to UTC?
            ps.setTimestamp(9, Timestamp.valueOf(endDateTime));         //convert to UTC?
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Appointment INSERT was successful!");
            }
            else {
                System.out.println("Appointment INSERT failed!");
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public Appointment lookUpAppointment(LocalDate date) {
        apptFound = false;
        for(Appointment appointment : appointments){
            if(appointment.getStartDate() == date){
                apptFound = true;
                return appointment;
            }
        }
        return null;
    }
}
