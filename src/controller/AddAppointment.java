package controller;

import DAO.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Contact;
import model.Customer;
import model.User;
import utilities.TimeManager;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {
    Stage stage;
    Parent scene;

    public TextField titleTxt, descriptionTxt, locationTxt, typeTxt;
    public ComboBox<Contact> contactComboBx;
    public ComboBox<Customer> customerComboBx;
    public ComboBox<User> userComboBx;
    public DatePicker startDatePicker, endDatePicker;
    public ComboBox<LocalTime> startTimeComboBx, endTimeComboBx;
    public Label titleE, descriptionE, locationE, typeE, contactE, customerE, userE;

    public int customerId, userId, contactId;
    public String title, description, location, type;
    public LocalDate startDate, endDate;
    public LocalTime startTime, endTime;
    public LocalDateTime startDateTime, endDateTime;


    public void onActionSaveAppt(ActionEvent actionEvent) {
        System.out.println("Save Button clicked!");

        try{
            AppointmentDao appointmentDao = new AppointmentDaoImpl();

            title = typeE.getText();
            description = descriptionTxt.getText();
            location = locationTxt.getText();
            type = typeTxt.getText();
            contactId = contactComboBx.getSelectionModel().getSelectedItem().getContactId();
            customerId = customerComboBx.getSelectionModel().getSelectedItem().getCustomerId();
            userId = userComboBx.getSelectionModel().getSelectedItem().getUserId();
            startDate = startDatePicker.getValue();
            endDate = endDatePicker.getValue();
            startTime = startTimeComboBx.getSelectionModel().getSelectedItem();
            endTime = endTimeComboBx.getSelectionModel().getSelectedItem();
            /*
            if() {  //check that appointment times aren't overlapping
                startDateTime = LocalDateTime.of(startDate, startTime);
                endDateTime = LocalDateTime.of(endDate, endTime);
            }
             */
            startDateTime = LocalDateTime.of(startDate, startTime);
            endDateTime = LocalDateTime.of(endDate, endTime);
            appointmentDao.addAppointment(customerId, userId, contactId, title, description, location, type,
                    startDateTime, endDateTime);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            System.out.println("Start: " + startDateTime + " End: " + endDateTime);
        }
    }

    public void onActionReturnToMain(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button clicked!");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel \"Add Appointment\"");
        alert.setContentText("All changes will be forgotten, do you wish to continue?");
        alert.showAndWait();

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainAppointments.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            JDBC.closeConnection();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Appointment: I am initialized!");

        ZoneId osZId = ZoneId.systemDefault();
        ZoneId businessZId =  ZoneId.of("America/New_York");
        LocalTime startTime = LocalTime.of(8,0);
        int workHours = 13;

        JDBC.openConnection();
        ContactDao contactDao = new ContactDaoImpl();
        CustomerDao customerDao = new CustomerDaoImpl();
        UserDao userDao = new UserDaoImpl();

        contactComboBx.setItems(contactDao.getAllContacts());
        customerComboBx.setItems(customerDao.getAllCustomers());
        userComboBx.setItems(userDao.getAllUsers());
        startTimeComboBx.setItems(TimeManager.dynamicBusinessHoursInit(osZId, businessZId, startTime, workHours));
        endTimeComboBx.setItems(TimeManager.dynamicBusinessHoursInit(osZId, businessZId, LocalTime.of(9,0), workHours));
    }
}
