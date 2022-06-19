package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateAppointment implements Initializable {
    Stage stage;
    Parent scene;
    Appointment selAppt = null;
    public TextField titleTxt, descriptionTxt, locationTxt, typeTxt;
    public ComboBox<Contact> contactComboBx;
    public ComboBox<Customer> customerComboBx;
    public ComboBox<User> userComboBx;
    public DatePicker startDatePicker, endDatePicker;
    public ComboBox startTimeComboBx, endTimeComboBx;
    public Label titleE, descriptionE, locationE, typeE, contactE, customerE, userE;

    public void onActionUpdateAppt(ActionEvent actionEvent) {
        System.out.println("Save Button clicked!");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Update Appointment: I am initialized!");
    }
}
