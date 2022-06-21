package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Country;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Reports implements Initializable {
    Stage stage;
    Parent scene;

    public ToggleGroup viewByTgl;
    public RadioButton typeMonthRadioBtn, countryRadioBtn, contactRadioBtn;
    public TableView<Appointment> reportTableView;
    public TableColumn apptIdCol, titleCol, descriptionCol, locationCol, contactCol, typeCol, startDateCol, endDateCol,
            startTimeCol, endTimeCol, customerIdCol, userIdCol;
    public ComboBox typeComboBx;
    public ComboBox monthComboBx;
    public ComboBox<Country> countryComboBx;
    public ComboBox<Contact> contactComboBx;
    public Label totalCustomersLbl;

    public void onActionPopulateTypeMonth(ActionEvent actionEvent) {
    }

    public void onActionPopulateCountry(ActionEvent actionEvent) {
    }

    public void onActionPopulateContact(ActionEvent actionEvent) {
    }

    public void onActionFilterByTypeMonth(ActionEvent actionEvent) {
    }

    public void onActionFilterByCountry(ActionEvent actionEvent) {
    }

    public void onActionFilterByContact(ActionEvent actionEvent) {
    }

    public void onActionReturnToMain(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionLogout(ActionEvent actionEvent) {
        System.out.println("Logout Button Clicked!");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setContentText("Do you wish to Exit the program?");
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent() && result.get() == ButtonType.OK)) {
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Reports: I am Initialized!");

        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
}
