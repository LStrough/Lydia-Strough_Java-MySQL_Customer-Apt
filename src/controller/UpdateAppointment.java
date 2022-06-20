package controller;

import DAO.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import utilities.TimeManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateAppointment implements Initializable {
    Stage stage;
    Parent scene;

    public TextField titleTxt, descriptionTxt, locationTxt, typeTxt;
    public ComboBox<Contact> contactComboBx;
    public ComboBox<Customer> customerComboBx;
    public ComboBox<User> userComboBx;
    public DatePicker startDatePicker, endDatePicker;
    public ComboBox<LocalTime> startTimeComboBx, endTimeComboBx;
    public Label titleE, descriptionE, locationE, typeE, contactE, customerE, userE;

    Appointment selAppt = null;
    public int customerId, userId, contactId;
    public String title, description, location, type;
    public LocalDate startDate, endDate;
    public LocalTime startTime, endTime;
    public LocalDateTime startDateTime, endDateTime;

    public void updateAppointment(Appointment selectedAppt) {
        JDBC.openConnection();
        ContactDao contactDao = new ContactDaoImpl();
        CustomerDao customerDao = new CustomerDaoImpl();
        UserDao userDao = new UserDaoImpl();

        selAppt = selectedAppt;
        titleTxt.setText(String.valueOf(selAppt.getTitle()));
        descriptionTxt.setText(String.valueOf(selAppt.getDescription()));
        locationTxt.setText(String.valueOf(selAppt.getLocation()));
        typeTxt.setText(String.valueOf(selAppt.getType()));

        contactComboBx.setItems(contactDao.getAllContacts());
        //contactComboBx.getSelectionModel().select(selAppt.getContactId() - 1);
        Contact selContact = null;
        for(Contact contact : contactDao.getAllContacts()) {
            if(contact.getContactId() == selAppt.getContactId()) {
                selContact = contact;
                break;
            }
        }
        contactComboBx.getSelectionModel().select(selContact);

        customerComboBx.setItems(customerDao.getAllCustomers());
        //customerComboBx.getSelectionModel().select(selAppt.getCustomerId() - 1);
        Customer selCustomer = null;
        for(Customer customer : customerDao.getAllCustomers()) {
            if(customer.getCustomerId() == selAppt.getCustomerId()){
                selCustomer = customer;
                break;
            }
        }
        customerComboBx.getSelectionModel().select(selCustomer);

        userComboBx.setItems(userDao.getAllUsers());
        //userComboBx.getSelectionModel().select(selAppt.getUserId() - 1);
        User selUser = null;
        for(User user : userDao.getAllUsers()) {
            if(user.getUserId() == selAppt.getUserId()) {
                selUser = user;
                break;
            }
        }
        userComboBx.getSelectionModel().select(selUser);

        startDatePicker.setValue(selAppt.getStartDate());
        endDatePicker.setValue(selAppt.getEndDate());
        startTimeComboBx.getSelectionModel().select(selAppt.getStartTime());
        endTimeComboBx.getSelectionModel().select(selAppt.getEndTime());
    }

    public void onActionUpdateAppt(ActionEvent actionEvent) {
        System.out.println("Save Button clicked!");

        try {
            boolean formatError = false;
            int appointmentId = selAppt.getAppointmentId();
            title = titleTxt.getText();
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
            startDateTime = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(),
                    startTime.getHour(), startTime.getMinute());
            endDateTime = LocalDateTime.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(),
                    endTime.getHour(), endTime.getMinute());

            if(title.isBlank()) {
                errorMessage(1);
                formatError = true;
            } else if(description.isBlank()) {
                errorMessage(2);
                formatError = true;
            } else if(location.isBlank()) {
                errorMessage(3);
                formatError = true;
            } else if(type.isBlank()) {
                errorMessage(4);
                formatError = true;
            } else if(contactComboBx.getSelectionModel() == null) {
                errorMessage(5);
                formatError = true;
            } else if(customerComboBx.getSelectionModel() == null) {
                errorMessage(6);
                formatError = true;
            } else if(userComboBx.getSelectionModel() == null) {
                errorMessage(7);
                formatError = true;
            } else if((startDate == null) || (endDate == null)) {
                errorMessage(8);
                formatError = true;
            } else if((startTime == null) || (endTime == null)) {
                errorMessage(9);
                formatError = true;
            }

            if(!formatError) {
                AppointmentDao appointmentDao = new AppointmentDaoImpl();
                appointmentDao.updateAppointment(appointmentId, customerId, userId, contactId, title, description,
                        location, type, startDateTime, endDateTime);

                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainAppointments.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void onActionReturnToMain(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button clicked!");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel \"Update Appointment\"");
        alert.setContentText("All changes will be forgotten, do you wish to continue?");
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainAppointments.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    public void errorMessage(int errorNum) {
        switch (errorNum) {
            case 1 -> {
                titleE.setText("\"Title\" cannot be empty!");
            }
            case 2 -> {
                descriptionE.setText("\"Description\" cannot be empty!");
            }
            case 3 -> {
                locationE.setText("\"Location\" cannot be empty!");
            }
            case 4 -> {
                typeE.setText("\"Type\" cannot be empty!");
            }
            case 5 -> {
                contactE.setText("You must choose a \"Contact\"!");
            }
            case 6 -> {
                customerE.setText("You must choose a \"Customer\"!");
            }
            case 7 -> {
                userE.setText("You must choose a \"User\"!");
            }
            case 8 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("\"Add Appointment\" ERROR");
                alert.setContentText("You must choose a \"Start/End Date\"!");
                alert.showAndWait();
            }
            case 9 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("\"Add Appointment\" ERROR");
                alert.setContentText("You must choose a \"Start/End Time\"!");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Update Appointment: I am initialized!");
        try {
            ZoneId osZId = ZoneId.systemDefault();
            ZoneId businessZId = ZoneId.of("America/New_York");
            LocalTime startTime = LocalTime.of(8, 0);
            int workHours = 13;

            startTimeComboBx.setItems(TimeManager.dynamicBusinessHoursInit(osZId, businessZId, startTime, workHours));
            endTimeComboBx.setItems(TimeManager.dynamicBusinessHoursInit(osZId, businessZId, LocalTime.of(9, 0), workHours));
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
