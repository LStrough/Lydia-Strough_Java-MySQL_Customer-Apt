package controller;

import DAO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.BusinessHour;
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import static model.BusinessHour.businessHours;

public class AddAppointment implements Initializable {
    Stage stage;
    Parent scene;
    private ObservableList<BusinessHour> businessHoursLocal = FXCollections.observableArrayList();
    public TextField titleTxt, descriptionTxt, locationTxt, typeTxt;
    public ComboBox<Contact> contactComboBx;
    public ComboBox<Customer> customerComboBx;
    public ComboBox<User> userComboBx;
    public DatePicker startDatePicker, endDatePicker;
    public ComboBox<BusinessHour> startTimeComboBx, endTimeComboBx;
    public Label titleE, descriptionE, locationE, typeE, contactE, customerE, userE;
    public int customerId, userId, contactId;
    public String title, description, location, type;
    LocalDate startDate, endDate;
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

            //startTimeComboBx.setItems();

            /*
            startTime = startTimeComboBx.getValue();
            endTime = endTimeComboBx.getValue();

            //if() {}           //check that values aren't overlapping

            startDateTime = LocalDateTime.of(startDate, startTime);
            endDateTime = LocalDateTime.of(endDate, endTime);

            appointmentDao.addAppointment(customerId, userId, contactId, title, description, location, type,
                    startDateTime, endDateTime);
             */
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

    public void onActionFilterStartDateTime(ActionEvent actionEvent) {
        for(BusinessHour businessHour : businessHours) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm");
            String date = String.valueOf(startDate);
            int hour = businessHour.getHour();
            int min = businessHour.getMin();
            String time = hour + ":" + min;

            if ((hour == 8) || (hour == 9)) {
                time = "0" + time;
            }
            if (min == 0) {
                time = time + "0";
            }

            String txtStartDateTime = date + " " + time;
            LocalDateTime ldt = LocalDateTime.parse(txtStartDateTime, df);

            ZoneId zid = ZoneId.systemDefault();
            ZonedDateTime zdt = ldt.atZone(zid);

            BusinessHour localBHr = new BusinessHour(zdt);

            businessHoursLocal.add(localBHr);
        }
        startTimeComboBx.setItems(businessHoursLocal);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Appointment: I am initialized!");

        JDBC.openConnection();
        ContactDao contactDao = new ContactDaoImpl();
        CustomerDao customerDao = new CustomerDaoImpl();
        UserDao userDao = new UserDaoImpl();

        contactComboBx.setItems(contactDao.getAllContacts());
        customerComboBx.setItems(customerDao.getAllCustomers());
        userComboBx.setItems(userDao.getAllUsers());
    }
}
