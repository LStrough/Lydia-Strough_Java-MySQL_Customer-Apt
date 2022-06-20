package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.*;

import static DAO.JDBC.connection;

public class AppointmentDaoImpl implements AppointmentDao {
    ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    public boolean apptFound;

    @Override
    public ObservableList<Appointment> getAllAppointments() {
        try {
            String sql = "SELECT * FROM appointments";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int appointmentId = result.getInt("Appointment_ID");
                int customerId = result.getInt("Customer_ID");
                ;
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
                allAppointments.add(appointment);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return allAppointments;
    }

    @Override
    public Appointment getAppointment(int appointmentId) {
        try {
            String sql = "SELECT * FROM appointments WHERE Appointment_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentId);

            ResultSet result = ps.executeQuery();
            Appointment apptResult = null;
            if (result.next()) {
                appointmentId = result.getInt("Appointment_ID");
                int customerId = result.getInt("Customer_ID");
                ;
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
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<Appointment> getApptByCustomer(int customerId) {
        ObservableList<Appointment> customerAppts = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments WHERE Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int appointmentId = result.getInt("Appointment_ID");
                customerId = result.getInt("Customer_ID");
                ;
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
                customerAppts.add(appointment);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return customerAppts;
    }

    @Override
    public int updateAppointment(int appointmentId, int customerId, int userId, int contactId, String title, String description,
                                 String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        int rowsAffected = 0;
        try {
            String sql = "UPDATE appointments SET Customer_ID=?, User_ID=?, Contact_ID=?, Title=?, Description=?, " +
                    "Location=?, Type=?, Start=?, End=? WHERE Appointment_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setInt(2, userId);
            ps.setInt(3, contactId);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, type);
            ps.setString(7, location);
            ps.setTimestamp(8, Timestamp.valueOf(startDateTime));
            ps.setTimestamp(9, Timestamp.valueOf(endDateTime));
            ps.setInt(10, appointmentId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Appointment UPDATE was successful!");
            } else {
                System.out.println("Appointment UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int addAppointment(int customerId, int userId, int contactId, String title, String description, String location,
                              String type, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        int rowsAffected = 0;
        try {
            String sql = "INSERT INTO appointments (Customer_ID, User_ID, Contact_ID, Title, Description, Location, Type, " +
                    "Start, End) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setInt(2, userId);
            ps.setInt(3, contactId);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, location);
            ps.setString(7, type);
            ps.setTimestamp(8, Timestamp.valueOf(startDateTime));
            ps.setTimestamp(9, Timestamp.valueOf(endDateTime));
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Appointment INSERT was successful!");
            } else {
                System.out.println("Appointment INSERT failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public ObservableList<Appointment> lookUpAppointment(LocalDate selDate) {
        ObservableList<Appointment> filteredAppts = FXCollections.observableArrayList();
        apptFound = false;

        for (Appointment appointment : allAppointments) {
            if (appointment.getStartDate().equals(selDate)) {
                filteredAppts.add(appointment);
            }
        }
        if (filteredAppts.isEmpty()) {
            return allAppointments;
        }
        apptFound = true;
        return filteredAppts;
    }

    @Override
    public ObservableList<Appointment> orderApptsByMonth() {
        return null;
    }

    @Override
    public ObservableList<Appointment> orderApptsByWeek() {
        return null;
    }

    @Override
    public boolean checkApptStartTime(LocalDateTime apptStartTime) {
        ZonedDateTime apptZone = apptStartTime.atZone(ZoneId.systemDefault());
        apptZone = apptZone.withZoneSameInstant(ZoneId.of("US/Eastern"));
        apptStartTime = apptZone.toLocalDateTime();

        LocalTime businessOpen = LocalTime.of(8, 0);
        LocalTime businessClose = LocalTime.of(22, 0);
        return ((apptStartTime.toLocalTime().isAfter(businessOpen) || apptStartTime.toLocalTime().equals(businessOpen)) &&
                (apptStartTime.toLocalTime().isBefore(businessClose)));
    }

    @Override
    public boolean checkApptEndTime(LocalDateTime apptEndTime) {
        ZonedDateTime apptZone = apptEndTime.atZone(ZoneId.systemDefault());
        apptZone = apptZone.withZoneSameInstant(ZoneId.of("US/Eastern"));
        apptEndTime = apptZone.toLocalDateTime();

        LocalTime businessOpen = LocalTime.of(8, 0);
        LocalTime businessClose = LocalTime.of(22, 0);
        return ((apptEndTime.toLocalTime().isAfter(businessOpen)) &&
                (apptEndTime.toLocalTime().isBefore(businessClose) || apptEndTime.toLocalTime().equals(businessClose)));    //can end at close!
    }

    @Override
    public boolean checkForOverlap(int customerId, LocalDate selStartDate, LocalDate selEndDate, LocalTime selStartTime, LocalTime selEndTime) {
        AppointmentDao apptDao = new AppointmentDaoImpl();
        ObservableList<Appointment> customerAppts = apptDao.getApptByCustomer(customerId);
        boolean overlap = false;

        for (Appointment appt : customerAppts) {
            //start or end on same day
            if ((appt.getStartDate() == selStartDate) || (appt.getStartDate() == selEndDate) || (appt.getEndDate() == selStartDate)
                    || (appt.getEndDate() == selEndDate)) {
                //start or end at the same time
                if ((appt.getStartTime() == selStartTime) || (appt.getEndTime() == selEndTime)) {
                    overlap = true;
                    break;
                 //old appt starts & ends during new appt
                }else if(appt.getStartTime().isAfter(selStartTime) && appt.getEndTime().isBefore(selEndTime)) {
                    overlap = true;
                    break;
                 //new appt starts & ends during old appt
                }else if(selStartTime.isAfter(appt.getStartTime()) && (selEndTime.isBefore(appt.getEndTime()))) {
                    overlap = true;
                    break;
                 //old appt starts before new & ends in new
                }else if((appt.getStartTime().isBefore(selStartTime)) && (appt.getEndTime().isBefore(selEndTime))) {
                    overlap = true;
                    break;
                 //old appt starts in new and ends after new
                } else if ((appt.getStartTime().isAfter(selStartTime)) && (appt.getEndTime().isAfter(selEndTime))) {
                    overlap = true;
                    break;
                 //new appt starts before old & ends in old
                }else if ((selStartTime.isBefore(appt.getStartTime())) && (selEndTime.isBefore(appt.getEndTime()))) {
                    overlap = true;
                    break;
                //new appt starts in old and ends after old
                }else if((selStartTime.isAfter(appt.getStartTime())) && (selEndTime.isAfter(appt.getEndTime()))) {
                    overlap = true;
                    break;
                }
            }
        }
        return overlap;
    }
}
