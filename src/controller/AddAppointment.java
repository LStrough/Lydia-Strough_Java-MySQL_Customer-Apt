package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {
    Stage stage;
    Parent scene;
    public TextField aptIdTxt;
    public TextField aptTitleTxt;
    public TextField aptDescriptionTxt;
    public TextField aptLocationTxt;
    public TextField aptTypeTxt;
    public ComboBox contactIdComboBx;
    public ComboBox customerIdComboBx;
    public ComboBox userIdComboBx;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public Label aptTitleE;
    public Label aptDescriptionE;
    public Label aptLocationE;
    public Label aptTypeE;
    public Label aptContactIdE;
    public Label aptCustomerIdE;
    public Label aptUserIdE;

    public void onActionSaveApt(ActionEvent actionEvent) {
        System.out.println("Save Button clicked!");
    }

    public void onActionReturnToMain(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Appointment: I am initialized!");
    }
}
