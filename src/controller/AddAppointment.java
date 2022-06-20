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
            boolean formatError = false;
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

            if(title.isEmpty()) {
                errorMessage(1);
                formatError = true;
            } else if(description.isEmpty()) {
                errorMessage(10);
                errorMessage(2);
                formatError = true;
            } else if(location.isEmpty()) {
                errorMessage(11);
                errorMessage(3);
                formatError = true;
            } else if(type.isEmpty()) {
                errorMessage(12);
                errorMessage(4);
                formatError = true;
            } else if(contactComboBx.getSelectionModel() == null) {
                errorMessage(13);
                errorMessage(5);
                formatError = true;
            } else if(customerComboBx.getSelectionModel() == null) {
                errorMessage(14);
                errorMessage(6);
                formatError = true;
            } else if(userComboBx.getSelectionModel() == null) {
                errorMessage(15);
                errorMessage(7);
                formatError = true;
            } else if((startDate == null) || (endDate == null)) {
                errorMessage(8);
                formatError = true;
            } else if((startTime == null) || (endTime == null)) {
                errorMessage(9);
                formatError = true;
            } else {
                errorMessage(16);
            }

            if(!formatError) {
                AppointmentDao appointmentDao = new AppointmentDaoImpl();
                appointmentDao.addAppointment(customerId, userId, contactId, title, description, location, type,
                        startDateTime, endDateTime);

                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainAppointments.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
                JDBC.closeConnection();
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
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
            case 10 -> {
                titleE.setText("");
            }
            case 11 -> {
                descriptionE.setText("");
            }
            case 12 -> {
                locationE.setText("");
            }
            case 13 -> {
                typeE.setText("");
            }
            case 14 -> {
                contactE.setText("");
            }
            case 15 -> {
                customerE.setText("");
            }
            case 16 -> {
                userE.setText("");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Appointment: I am initialized!");
        try {
            ZoneId osZId = ZoneId.systemDefault();
            ZoneId businessZId = ZoneId.of("America/New_York");
            LocalTime startTime = LocalTime.of(8, 0);
            int workHours = 13;

            JDBC.openConnection();
            ContactDao contactDao = new ContactDaoImpl();
            CustomerDao customerDao = new CustomerDaoImpl();
            UserDao userDao = new UserDaoImpl();

            contactComboBx.setItems(contactDao.getAllContacts());
            contactComboBx.getSelectionModel().selectFirst();
            customerComboBx.setItems(customerDao.getAllCustomers());
            customerComboBx.getSelectionModel().selectFirst();
            userComboBx.setItems(userDao.getAllUsers());
            userComboBx.getSelectionModel().selectFirst();
            startDatePicker.setValue(LocalDate.now());
            endDatePicker.setValue(LocalDate.now());
            startTimeComboBx.setItems(TimeManager.dynamicBusinessHoursInit(osZId, businessZId, startTime, workHours));
            startTimeComboBx.getSelectionModel().selectFirst();
            endTimeComboBx.setItems(TimeManager.dynamicBusinessHoursInit(osZId, businessZId, LocalTime.of(9, 0), workHours));
            endTimeComboBx.getSelectionModel().selectFirst();
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
